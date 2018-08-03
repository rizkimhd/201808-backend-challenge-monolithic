package com.rizkimuhammad.challenge.java.monolithic.bankbackend.validator;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.repository.UserAccountRepository;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.LoginRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Component
public class LoginValidator {

    @Autowired
    protected UserAccountRepository customerRepository;

    public String getError(LoginRequestVO loginRequestVO) {
        if (StringUtils.isEmpty(loginRequestVO.getUsername())) {
            return "Username cannot be empty";
        } else {
            if (loginRequestVO.getUsername().length() > 20) return "Username cannot exceed 20 characters";
        }

        if (StringUtils.isEmpty(loginRequestVO.getPassword())) {
            return "Password cannot be empty";
        } else {
            if (loginRequestVO.getPassword().length() < 8) return "Username must exceed 8 characters";
        }

        return "";
    }

}
