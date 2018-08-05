package com.rizkirm.challenge.bank.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Data
public class TransferResponseVO {
    private String receiverAccount;
    private String receiverName;
    private BigDecimal amount;
    private BigDecimal balance;

    public TransferResponseVO() { }

    public TransferResponseVO(String receiverAccount, String receiverName, BigDecimal amount, BigDecimal balance) {
        this.receiverAccount = receiverAccount;
        this.receiverName = receiverName;
        this.amount = amount;
        this.balance = balance;
    }
}