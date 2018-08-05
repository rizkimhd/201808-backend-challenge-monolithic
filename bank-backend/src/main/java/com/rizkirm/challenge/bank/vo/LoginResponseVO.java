package com.rizkirm.challenge.bank.vo;

import lombok.Data;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Data
public class LoginResponseVO {
    private String username;
    private String fullName;
    private String accessToken;

    public LoginResponseVO() {
    }

    public LoginResponseVO(String username, String fullName, String accessToken) {
        this.username = username;
        this.fullName = fullName;
        this.accessToken = accessToken;
    }

}