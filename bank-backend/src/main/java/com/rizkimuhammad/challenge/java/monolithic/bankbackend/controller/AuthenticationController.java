package com.rizkimuhammad.challenge.java.monolithic.bankbackend.controller;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.service.LoginService;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.LoginRequestVO;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO login(@RequestBody LoginRequestVO loginRequestVO) {
        return loginService.login(loginRequestVO);
    }

}
