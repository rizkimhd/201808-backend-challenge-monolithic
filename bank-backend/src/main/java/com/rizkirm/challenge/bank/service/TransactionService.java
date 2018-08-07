package com.rizkirm.challenge.bank.service;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;
import com.rizkirm.challenge.bank.exception.CustomNotFoundException;
import com.rizkirm.challenge.bank.exception.CustomUnauthorizedException;
import com.rizkirm.challenge.bank.persistence.domain.TransactionHistory;
import com.rizkirm.challenge.bank.persistence.domain.UserAccount;
import com.rizkirm.challenge.bank.persistence.repository.TransactionHistoryRepository;
import com.rizkirm.challenge.bank.util.ConstantUtil;
import com.rizkirm.challenge.bank.util.JsonUtil;
import com.rizkirm.challenge.bank.validator.TransactionValidator;
import com.rizkirm.challenge.bank.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Service
public class TransactionService extends TransactionValidator {

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Transactional
    public TransactionResponseVO doWithdrawal(String token, TransactionRequestVO transactionRequestVO) {
        checkRequest(token, transactionRequestVO);

        UserAccount userAccount = getUser(token);
        BigDecimal oldBalance = userAccount.getBalance();

        if (oldBalance.subtract(transactionRequestVO.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        userAccount.setBalance(oldBalance.subtract(transactionRequestVO.getAmount()));
        userAccountRepository.save(userAccount);

        transactionHistoryRepository.save(new TransactionHistory(userAccount, userAccount.getAccountNumber(),
                userAccount.getFullName(), transactionRequestVO.getAmount(), ConstantUtil.TransactionType.WITHDRAWAL_DB));

        return new TransactionResponseVO(userAccount.getBalance());
    }

    @Transactional
    public TransactionResponseVO doDeposit(String token, TransactionRequestVO transactionRequestVO) {
        checkRequest(token, transactionRequestVO);

        UserAccount userAccount = getUser(token);
        BigDecimal oldBalance = userAccount.getBalance();

        userAccount.setBalance(oldBalance.add(transactionRequestVO.getAmount()));
        userAccountRepository.save(userAccount);

        transactionHistoryRepository.save(new TransactionHistory(userAccount, userAccount.getAccountNumber(),
                userAccount.getFullName(), transactionRequestVO.getAmount(), ConstantUtil.TransactionType.DEPOSIT_DB));

        return new TransactionResponseVO(userAccount.getBalance());
    }

    @Transactional
    public TransferResponseVO doTransfer(String token, TransferRequestVO transferRequestVO) {
        checkRequest(token, transferRequestVO);

        UserAccount userAccount = getUser(token);
        BigDecimal senderBalance = userAccount.getBalance();

        if (senderBalance.subtract(transferRequestVO.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomBadRequestException("Insufficient funds");
        }

        UserAccount receiver = userAccountRepository.findByAccountNumber(transferRequestVO.getReceiverAccount());
        if (receiver == null) throw new CustomNotFoundException("Account not found");
        if (receiver.getDisabled()) throw new CustomNotFoundException("Account has been suspended");
        if (userAccount.equals(receiver)) throw new CustomBadRequestException("Cannot doTransfer money to yourself");

        userAccount.setBalance(senderBalance.subtract(transferRequestVO.getAmount()));
        userAccountRepository.save(userAccount);

        BigDecimal receiverBalance = receiver.getBalance();
        receiver.setBalance(receiverBalance.add(transferRequestVO.getAmount()));
        userAccountRepository.save(userAccount);

        transactionHistoryRepository.save(new TransactionHistory(userAccount, receiver.getAccountNumber(),
                receiver.getFullName(), transferRequestVO.getAmount(), ConstantUtil.TransactionType.TRANSFER_DB));

        return new TransferResponseVO(receiver.getAccountNumber(), receiver.getFullName(),
                transferRequestVO.getAmount(), userAccount.getBalance());
    }

    public Map<String, Object> getTransactionHistory(String token, Integer page, Integer limit,
                                                     Long startDate, Long endDate) {
        if (isValidToken(token)) {
            UserAccount userAccount = getUser(token);
            Pageable pageable = PageRequest.of(page, limit, new Sort(Sort.Direction.DESC, "createdDate"));

            Date from = startDate == null ? userAccount.getCreatedDate() : new Date(startDate);
            Date to = endDate == null ? new Date() : new Date(endDate);

            Page<TransactionHistory> transactionHistoryPage = transactionHistoryRepository
                    .findBySenderAndCreatedDateBetween(userAccount, userAccount.getAccountNumber(), from, to, pageable);

            List<TransactionHistory> transactionHistoryList = transactionHistoryPage.getContent();
            return JsonUtil.constructMapReturn(TransactionHistoryVO.constructList(transactionHistoryList),
                    transactionHistoryPage.getTotalElements(), transactionHistoryPage.getTotalPages());
        } else {
            throw new CustomUnauthorizedException();
        }

    }

}