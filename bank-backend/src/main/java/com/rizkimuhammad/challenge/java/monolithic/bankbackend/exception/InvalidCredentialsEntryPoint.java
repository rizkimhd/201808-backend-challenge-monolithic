package com.rizkimuhammad.challenge.java.monolithic.bankbackend.exception;

import com.google.gson.Gson;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
public class InvalidCredentialsEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.getWriter().write(new Gson().toJson(new ResponseVO(HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(), "Invalid username and password")));


    }

//    public InvalidCredentialsEntryPoint

}
