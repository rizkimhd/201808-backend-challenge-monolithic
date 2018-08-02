package com.rizkimuhammad.challenge.java.monolithic.bankbackend.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    AuthenticationUtil authenticationUtil;

    @Override
    public Optional<String> getCurrentAuditor() {
        return authenticationUtil.getFullName();
    }
}
