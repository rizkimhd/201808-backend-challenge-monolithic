package com.rizkirm.challenge.bank.util;

import com.rizkirm.challenge.bank.exception.CustomUnauthorizedException;
import com.rizkirm.challenge.bank.persistence.domain.UserAccount;
import com.rizkirm.challenge.bank.persistence.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class AuthenticationUtil {

    @Autowired
    protected UserAccountRepository userAccountRepository;

    protected UserAccount getUser(String token) {
        UserAccount userAccount = userAccountRepository.findByAccessTokenAndDisabledFalse(token);
        if (userAccount == null) {
            throw new CustomUnauthorizedException();
        } else {
            return userAccount;
        }
    }

    protected Boolean isValidToken(String token) {
        return userAccountRepository.existsByAccessTokenAndDisabledFalse(token);
    }

}
