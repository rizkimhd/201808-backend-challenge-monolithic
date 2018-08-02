package com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo;

import lombok.Data;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Data
public class ResponseVO {
    protected String response;
    protected String message;
    private String result;

    public ResponseVO() {

    }

    public ResponseVO(Object response, Object message, Object result) {
        this.response = String.valueOf(response);
        this.message = String.valueOf(message);
        this.result = String.valueOf(result);
    }
}
