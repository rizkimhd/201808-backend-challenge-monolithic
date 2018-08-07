package com.rizkirm.challenge.bank.vo;

import com.rizkirm.challenge.bank.persistence.domain.TransactionHistory;
import com.rizkirm.challenge.bank.util.ConstantUtil;
import com.rizkirm.challenge.bank.util.DateUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Data
public class TransactionHistoryVO {
    private String transactionTime;
    private String transactionType;
    private String receiverName;
    private String senderName;
    private BigDecimal amount;

    public TransactionHistoryVO() {
    }

    public TransactionHistoryVO(Date transactionTime, String receiverName, String senderName,
                                BigDecimal amount, String transactionType) {
        this.transactionTime = DateUtil.dateToString(transactionTime, DateUtil.YYYY_MM_DD_HHMMSS);
        this.transactionType = reformatType(transactionType);
        this.receiverName = receiverName;
        this.amount = amount;
    }

    public static List<TransactionHistoryVO> constructList(List<TransactionHistory> transactionHistoryList) {
        List<TransactionHistoryVO> transactionHistoryVOList = new ArrayList<>();
        for (TransactionHistory transactionHistory : transactionHistoryList) {
            transactionHistoryVOList.add(new TransactionHistoryVO(transactionHistory.getCreatedDate(),
                    transactionHistory.getReceiverName(), transactionHistory.getSender().getFullName(),
                    transactionHistory.getAmount(), transactionHistory.getTransactionType()));
        }
        return transactionHistoryVOList;
    }

    private String reformatType(String transactionType) {
        switch (transactionType) {
            case ConstantUtil.TransactionType.DEPOSIT_DB:
                return ConstantUtil.TransactionType.DEPOSIT_FE;
            case ConstantUtil.TransactionType.WITHDRAWAL_DB:
                return ConstantUtil.TransactionType.WITHDRAWAL_FE;
            case ConstantUtil.TransactionType.TRANSFER_DB:
                return ConstantUtil.TransactionType.TRANSFER_FE;
            default:
                return transactionType;
        }
    }

}
