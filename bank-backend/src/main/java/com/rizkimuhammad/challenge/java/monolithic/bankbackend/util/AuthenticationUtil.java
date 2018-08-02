package com.rizkimuhammad.challenge.java.monolithic.bankbackend.util;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.domain.UserAccount;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Slf4j
@Component
public class AuthenticationUtil {

    @Autowired
    UserAccountRepository userAccountRepository;

    public Optional<String> getFullName() {
        String methodName = "getFullName";
        log.debug("DEBUG:{}:BEGIN", methodName);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) throw new RuntimeException("User is not authenticated");
        log.trace("TRACE:{}: username={}", methodName, authentication.getPrincipal().toString());

        UserAccount userAccount = userAccountRepository.findByUsername(authentication.getPrincipal().toString());
        String fullName = userAccount == null ? "SYSTEM" : userAccount.getFullName();
        log.trace("TRACE:{}: fullName={}", methodName, fullName);

        log.debug("DEBUG:{}:DONE", methodName);
        return Optional.of(fullName);
    }

}
