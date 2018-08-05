package com.rizkirm.challenge.bank.exception;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class CustomBadRequestException extends RuntimeException {

    public CustomBadRequestException() {
        super();
    }

    public CustomBadRequestException(String msg) {
        super(msg);
    }

}
