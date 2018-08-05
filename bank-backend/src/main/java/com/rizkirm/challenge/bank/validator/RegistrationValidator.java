package com.rizkirm.challenge.bank.validator;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;
import com.rizkirm.challenge.bank.vo.RegistrationRequestVO;
import org.springframework.util.StringUtils;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class RegistrationValidator {

    public void checkRequest(RegistrationRequestVO registrationRequestVO) {
        if(StringUtils.isEmpty(registrationRequestVO.getEmail())) {
            throw new CustomBadRequestException("Email cannot be empty");
        }

        if(StringUtils.isEmpty(registrationRequestVO.getFullName())) {
            throw new CustomBadRequestException("Full name cannot be empty");
        }

        if(StringUtils.isEmpty(registrationRequestVO.getPassword())) {
            throw new CustomBadRequestException("Password cannot be empty");
        }

        if(StringUtils.isEmpty(registrationRequestVO.getUsername())) {
            throw new CustomBadRequestException("Username cannot be empty");
        }

        if(registrationRequestVO.getPassword().equalsIgnoreCase(registrationRequestVO.getUsername())) {
            throw new CustomBadRequestException("Cannot use username as password");
        }

        if(registrationRequestVO.getPassword().equalsIgnoreCase(registrationRequestVO.getFullName().trim())) {
            throw new CustomBadRequestException("Cannot use full name as password");
        }

    }


}
