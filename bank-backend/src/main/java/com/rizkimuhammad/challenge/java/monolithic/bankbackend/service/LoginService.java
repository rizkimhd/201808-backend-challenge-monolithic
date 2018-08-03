package com.rizkimuhammad.challenge.java.monolithic.bankbackend.service;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.domain.UserAccount;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.repository.UserAccountRepository;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.validator.LoginValidator;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.LoginRequestVO;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Slf4j
@Service
public class LoginService extends LoginValidator {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Transactional
    public ResponseVO login(LoginRequestVO loginRequestVO) {
        String methodName = "login";
        log.info("BEGIN:{}", methodName);

        String errMsg = getError(loginRequestVO);
        log.debug("DEBUG:{}: errMsg={}", methodName, errMsg);
        if (!StringUtils.isEmpty(errMsg)) {
            return new ResponseVO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errMsg);
        }

        UserAccount userAccount = userAccountRepository.findByUsername(loginRequestVO.getUsername());
        if(!passwordEncoder.matches(userAccount.getPassword(), loginRequestVO.getPassword())) {
            errMsg = "Invalid username and password";
            return new ResponseVO(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), errMsg);
        }

        log.info("DONE:{}", methodName);
        return new ResponseVO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Successfully logged in");
    }

}
