package com.rizkirm.challenge.bank.service;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;
import com.rizkirm.challenge.bank.exception.CustomNotFoundException;
import com.rizkirm.challenge.bank.persistence.domain.TransactionHistory;
import com.rizkirm.challenge.bank.persistence.domain.UserAccount;
import com.rizkirm.challenge.bank.persistence.repository.TransactionHistoryRepository;
import com.rizkirm.challenge.bank.validator.TransactionValidator;
import com.rizkirm.challenge.bank.vo.TransactionRequestVO;
import com.rizkirm.challenge.bank.vo.TransactionResponseVO;
import com.rizkirm.challenge.bank.vo.TransferRequestVO;
import com.rizkirm.challenge.bank.vo.TransferResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Service
public class TransactionService extends TransactionValidator {

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Transactional
    public TransactionResponseVO withdrawal(String token, TransactionRequestVO transactionRequestVO) {
        checkRequest(token, transactionRequestVO);

        UserAccount userAccount = getUser(token);
        BigDecimal oldBalance = userAccount.getBalance();

        if (oldBalance.subtract(transactionRequestVO.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        userAccount.setBalance(oldBalance.subtract(transactionRequestVO.getAmount()));
        userAccountRepository.save(userAccount);

        transactionHistoryRepository.save(new TransactionHistory(userAccount, userAccount.getAccountNumber(),
                userAccount.getFullName(), transactionRequestVO.getAmount()));

        return new TransactionResponseVO(userAccount.getBalance());
    }

    @Transactional
    public TransactionResponseVO deposit(String token, TransactionRequestVO transactionRequestVO) {
        checkRequest(token, transactionRequestVO);

        UserAccount userAccount = getUser(token);
        BigDecimal oldBalance = userAccount.getBalance();

        userAccount.setBalance(oldBalance.add(transactionRequestVO.getAmount()));
        userAccountRepository.save(userAccount);

        transactionHistoryRepository.save(new TransactionHistory(userAccount, userAccount.getAccountNumber(),
                userAccount.getFullName(), transactionRequestVO.getAmount()));

        return new TransactionResponseVO(userAccount.getBalance());
    }

    @Transactional
    public TransferResponseVO transfer(String token, TransferRequestVO transferRequestVO) {
        checkRequest(token, transferRequestVO);

        UserAccount userAccount = getUser(token);
        BigDecimal senderBalance = userAccount.getBalance();

        if (senderBalance.subtract(transferRequestVO.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomBadRequestException("Insufficient funds");
        }

        UserAccount receiver = userAccountRepository.findByAccountNumber(transferRequestVO.getReceiverAccount());
        if(receiver == null) throw new CustomNotFoundException("Account not found");
        if(receiver.getDisabled()) throw new CustomNotFoundException("Account has been suspended");
        if(userAccount.equals(receiver)) throw new CustomBadRequestException("Cannot transfer money to yourself");

        userAccount.setBalance(senderBalance.subtract(transferRequestVO.getAmount()));
        userAccountRepository.save(userAccount);

        BigDecimal receiverBalance = receiver.getBalance();
        receiver.setBalance(receiverBalance.add(transferRequestVO.getAmount()));
        userAccountRepository.save(userAccount);

        transactionHistoryRepository.save(new TransactionHistory(userAccount, receiver.getAccountNumber(),
                receiver.getFullName(), transferRequestVO.getAmount()));

        return new TransferResponseVO(receiver.getAccountNumber(), receiver.getFullName(),
                transferRequestVO.getAmount(), userAccount.getBalance());
    }

}