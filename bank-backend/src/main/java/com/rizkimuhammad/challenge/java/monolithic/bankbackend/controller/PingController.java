package com.rizkimuhammad.challenge.java.monolithic.bankbackend.controller;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.ResponseVO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@RestController
@RequestMapping("/api/ping")
public class PingController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseVO ping() {
        return new ResponseVO(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(),
                DateFormatUtils.format(new Date(), DateFormatUtils.ISO_DATE_FORMAT.getPattern()));

    }

}
