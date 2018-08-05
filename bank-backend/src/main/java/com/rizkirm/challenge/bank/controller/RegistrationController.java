package com.rizkirm.challenge.bank.controller;

import com.rizkirm.challenge.bank.service.RegistrationService;
import com.rizkirm.challenge.bank.vo.RegistrationRequestVO;
import com.rizkirm.challenge.bank.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseVO> registration(@RequestBody RegistrationRequestVO registrationRequestVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return registrationService.register(registrationRequestVO);
            }
        };
        return handler.getResult();
    }
}