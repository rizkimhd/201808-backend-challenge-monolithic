package com.rizkirm.challenge.bank.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Data
public class ResponsePageVO extends ResponseVO {
    private String pages;
    private String elements;

    public ResponsePageVO() {
    }

    public ResponsePageVO(Date timestamp, String message, Object result, String pages, String elements) {
        this.timestamp = timestamp;
        this.message = message;
        this.result = result;
        this.pages = pages;
        this.elements = elements;
    }
}