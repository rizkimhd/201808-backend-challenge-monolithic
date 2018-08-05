package com.rizkirm.challenge.bank.vo;

import com.rizkirm.challenge.bank.persistence.domain.TransactionHistory;
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
    private String receiverName;
    private BigDecimal amount;

    public TransactionHistoryVO() { }

    public TransactionHistoryVO(Date transactionTime, String receiverName, BigDecimal amount) {
        this.transactionTime = DateUtil.dateToString(transactionTime, DateUtil.YYYY_MM_DD_HHMMSS);
        this.receiverName = receiverName;
        this.amount = amount;
    }

    public static List<TransactionHistoryVO> constructList(List<TransactionHistory> transactionHistoryList) {
        List<TransactionHistoryVO> transactionHistoryVOList = new ArrayList<>();
        for(TransactionHistory transactionHistory : transactionHistoryList) {
            transactionHistoryVOList.add(new TransactionHistoryVO(transactionHistory.getCreatedDate(),
                    transactionHistory.getReceiverName(), transactionHistory.getAmount()));
        }
        return transactionHistoryVOList;
    }

}
