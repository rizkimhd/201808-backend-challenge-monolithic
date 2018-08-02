package com.rizkimuhammad.challenge.java.monolithic.bankbackend.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_USER;

    @Override
    public String getAuthority() {
        return null;
    }
}
