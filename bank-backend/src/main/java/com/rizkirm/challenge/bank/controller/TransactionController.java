package com.rizkirm.challenge.bank.controller;

import com.rizkirm.challenge.bank.service.TransactionService;
import com.rizkirm.challenge.bank.vo.ResponseVO;
import com.rizkirm.challenge.bank.vo.TransactionRequestVO;
import com.rizkirm.challenge.bank.vo.TransferRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/withdrawal",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseVO> withdrawal(@RequestHeader("Authorization") String token,
                                                 @RequestBody TransactionRequestVO transactionRequestVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return transactionService.withdrawal(token, transactionRequestVO);
            }
        };
        return handler.getResult();
    }

    @PostMapping(value = "/deposit",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseVO> deposit(@RequestHeader("Authorization") String token,
                                              @RequestBody TransactionRequestVO transactionRequestVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return transactionService.deposit(token, transactionRequestVO);
            }
        };
        return handler.getResult();
    }

    @PostMapping(value = "/transfer",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseVO> deposit(@RequestHeader("Authorization") String token,
                                              @RequestBody TransferRequestVO transferRequestVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return transactionService.transfer(token, transferRequestVO);
            }
        };
        return handler.getResult();
    }

}