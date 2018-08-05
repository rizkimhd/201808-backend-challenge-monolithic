package com.rizkirm.challenge.bank.vo;

import lombok.Data;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Data
public class RegistrationRequestVO {
    private String username;
    private String password;
    private String fullName;
    private String email;
}