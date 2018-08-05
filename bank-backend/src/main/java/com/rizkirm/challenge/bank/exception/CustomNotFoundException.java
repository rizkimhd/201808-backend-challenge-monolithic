package com.rizkirm.challenge.bank.exception;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException() {
        super();
    }

    public CustomNotFoundException(String msg) {
        super(msg);
    }

}
