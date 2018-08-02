package com.rizkimuhammad.challenge.java.monolithic.bankbackend.security;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.domain.UserAccount;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final UserAccount userAccount = userAccountRepository.findByUsername(s);

        if (userAccount == null) {
            throw new UsernameNotFoundException("UserAccount '" + s + "' not found");
        }

        return org.springframework.security.core.userdetails.User.withUsername(s)
                .password(userAccount.getPassword())
                .authorities(userAccount.getRole())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(userAccount.getDisabled())
                .build();
    }

}
