package com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo;

import lombok.Data;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Data
public class RegistrationRequestVO {
    private String username;
    private String fullName;
    private String email;
    private String password;
}
