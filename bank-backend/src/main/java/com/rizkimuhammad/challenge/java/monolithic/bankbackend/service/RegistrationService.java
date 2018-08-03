package com.rizkimuhammad.challenge.java.monolithic.bankbackend.service;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.domain.UserAccount;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.validator.RegistrationValidator;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.RegistrationRequestVO;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Slf4j
@Service
public class RegistrationService extends RegistrationValidator {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseVO register(RegistrationRequestVO registrationRequestVO) {
        String methodName = "register";
        log.info("BEGIN:{}", methodName);

        String errMsg = getError(registrationRequestVO);
        log.debug("DEBUG:{}: errMsg={}", methodName, errMsg);
        if (!StringUtils.isEmpty(errMsg)) {
            return new ResponseVO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errMsg);
        }

        String pwd = passwordEncoder.encode(registrationRequestVO.getPassword());
        UserAccount newCustomer = new UserAccount(registrationRequestVO.getUsername(),
                registrationRequestVO.getFullName(), pwd, registrationRequestVO.getEmail());

        customerRepository.save(newCustomer);
        log.debug("DEBUG:{}: newCustomer={}", methodName, newCustomer.getUsername());

        log.info("DONE:{}", methodName);
        return new ResponseVO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), newCustomer.getSecureId());
    }

}
