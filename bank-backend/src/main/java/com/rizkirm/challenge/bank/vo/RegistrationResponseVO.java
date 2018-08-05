package com.rizkirm.challenge.bank.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Data
public class RegistrationResponseVO {
    public String accountNumber;
    public BigDecimal balance;

    public RegistrationResponseVO() { }

    public RegistrationResponseVO(String accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
