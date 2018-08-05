package com.rizkirm.challenge.bank.controller;

import com.rizkirm.challenge.bank.service.AuthenticationService;
import com.rizkirm.challenge.bank.vo.LoginRequestVO;
import com.rizkirm.challenge.bank.vo.LogoutRequestVO;
import com.rizkirm.challenge.bank.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = "/logout",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> logout(@RequestHeader("Authorization") String token,
                                             @RequestBody LogoutRequestVO logoutRequestVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return authenticationService.logout(token, logoutRequestVO);
            }
        };
        return handler.getResult();
    }

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> login(@RequestBody LoginRequestVO loginRequestVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return authenticationService.login(loginRequestVO);
            }
        };
        return handler.getResult();
    }
}