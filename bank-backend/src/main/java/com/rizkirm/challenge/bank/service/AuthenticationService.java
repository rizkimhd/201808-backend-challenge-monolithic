package com.rizkirm.challenge.bank.service;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;
import com.rizkirm.challenge.bank.persistence.domain.UserAccount;
import com.rizkirm.challenge.bank.util.DateUtil;
import com.rizkirm.challenge.bank.validator.AuthenticationValidator;
import com.rizkirm.challenge.bank.vo.LoginRequestVO;
import com.rizkirm.challenge.bank.vo.LoginResponseVO;
import com.rizkirm.challenge.bank.vo.LogoutRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Service
public class AuthenticationService extends AuthenticationValidator {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public LoginResponseVO doLogin(LoginRequestVO loginRequestVO) {

        checkRequest(loginRequestVO);

        UserAccount user = userAccountRepository.findByUsername(loginRequestVO.getUsername());
        if (user != null && passwordEncoder.matches(loginRequestVO.getPassword(), user.getPassword())) {

            String date = DateUtil.dateToString(new Date(), DateUtil.YYYYMMDDHHMMSSSSS);
            String accessToken = UUID.nameUUIDFromBytes(user.getUsername().concat(date).getBytes()).toString();

            user.setAccessToken(accessToken);
            userAccountRepository.save(user);

            return new LoginResponseVO(user.getUsername(), user.getFullName(), user.getAccessToken());

        } else {
            throw new CustomBadRequestException("Invalid username/password");
        }
    }

    public String doLogout(String token, LogoutRequestVO logoutRequestVO) {
        checkRequest(token, logoutRequestVO);

        UserAccount userAccount = userAccountRepository.findByAccessTokenAndUsernameAndDisabledFalse(
                token, logoutRequestVO.getUsername());

        userAccount.setAccessToken(null);
        userAccountRepository.save(userAccount);

        return "Successfully logged out";
    }

}