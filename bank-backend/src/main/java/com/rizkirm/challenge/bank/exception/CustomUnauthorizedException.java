package com.rizkirm.challenge.bank.exception;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class CustomUnauthorizedException extends RuntimeException {

    public CustomUnauthorizedException() {
        super("Invalid credentials");
    }

}
