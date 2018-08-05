package com.rizkirm.challenge.bank.vo;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Data
public class ResponseVO {

    @Temporal(TemporalType.TIMESTAMP)
    protected Date timestamp;

    protected String message;

    protected Object result;

    public ResponseVO() {
    }

    public ResponseVO(Date timestamp, String message, Object result) {
        this.timestamp = timestamp;
        this.message = message;
        this.result = result;
    }
}