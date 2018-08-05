package com.rizkirm.challenge.bank.service;

import com.rizkirm.challenge.bank.persistence.domain.UserAccount;
import com.rizkirm.challenge.bank.persistence.repository.UserAccountRepository;
import com.rizkirm.challenge.bank.validator.RegistrationValidator;
import com.rizkirm.challenge.bank.vo.RegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Service
public class RegistrationService extends RegistrationValidator {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public String register(RegistrationRequestVO registrationRequestVO) {
        checkRequest(registrationRequestVO);

        UserAccount userAccount = userAccountRepository.findByUsernameOrEmail(
                registrationRequestVO.getUsername(), registrationRequestVO.getEmail());

        if (userAccount == null) {
            userAccount = new UserAccount(registrationRequestVO.getUsername(), registrationRequestVO.getFullName(),
                    passwordEncoder.encode(registrationRequestVO.getPassword()), registrationRequestVO.getEmail());

            userAccountRepository.save(userAccount);

            return "Successfully registered";

        } else {
            throw new RuntimeException("Username/Email already used");
        }
    }
}