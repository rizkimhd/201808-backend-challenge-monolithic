package com.rizkirm.challenge.bank.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Data
public class TransactionRequestVO {
    private BigDecimal amount;
}
