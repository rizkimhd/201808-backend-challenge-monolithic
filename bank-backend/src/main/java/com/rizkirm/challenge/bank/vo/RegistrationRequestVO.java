package com.rizkirm.challenge.bank.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Data
public class RegistrationRequestVO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private BigDecimal balance;
}