package com.rizkimuhammad.challenge.java.monolithic.bankbackend.validator;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.repository.UserAccountRepository;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.RegistrationRequestVO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
public class RegistrationValidator {

    @Autowired
    protected UserAccountRepository customerRepository;

    public String getError(RegistrationRequestVO registrationRequestVO) {
        if (StringUtils.isEmpty(registrationRequestVO.getUsername())) {
            return "Username cannot be empty";
        } else {
            if (registrationRequestVO.getUsername().length() > 20) return "Username cannot exceed 20 characters";
            if (customerRepository.existsByUsername(registrationRequestVO.getUsername()))
                return "Username already exists";
        }

        if (StringUtils.isEmpty(registrationRequestVO.getEmail())) {
            return "Email cannot be empty";
        } else {
            if (registrationRequestVO.getEmail().length() > 50) return "Email cannot exceed 50 characters";
            if (!EmailValidator.getInstance().isValid(registrationRequestVO.getEmail())) return "Invalid email format";
            if (customerRepository.existsByEmail(registrationRequestVO.getEmail())) return "Email already exists";
        }

        if (StringUtils.isEmpty(registrationRequestVO.getPassword())) {
            return "Password cannot be empty";
        } else {
            if (registrationRequestVO.getPassword().length() < 8) return "Username must exceed 8 characters";
        }

        if (StringUtils.isEmpty(registrationRequestVO.getFullName())) {
            return "Full name cannot be empty";
        } else {
            if (registrationRequestVO.getFullName().length() > 30) return "Full name cannot exceed 30 characters";
        }

        return "";
    }

}
