package com.rizkirm.challenge.bank.validator;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;
import com.rizkirm.challenge.bank.exception.CustomUnauthorizedException;
import com.rizkirm.challenge.bank.util.AuthenticationUtil;
import com.rizkirm.challenge.bank.vo.LoginRequestVO;
import com.rizkirm.challenge.bank.vo.LogoutRequestVO;
import org.springframework.util.StringUtils;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class AuthenticationValidator extends AuthenticationUtil {

    public void checkRequest(LoginRequestVO loginRequestVO) {
        if (StringUtils.isEmpty(loginRequestVO.getUsername())) {
            throw new CustomBadRequestException("Username cannot be empty");
        }

        if (StringUtils.isEmpty(loginRequestVO.getPassword())) {
            throw new CustomBadRequestException("Password cannot be empty");
        }
    }

    public void checkRequest(String token, LogoutRequestVO logoutRequestVO) {
        if (isValidToken(token)) {
            if (StringUtils.isEmpty(logoutRequestVO.getUsername())) {
                throw new CustomBadRequestException("Username cannot be empty");
            }
        } else {
            throw new CustomUnauthorizedException();
        }
    }

}
