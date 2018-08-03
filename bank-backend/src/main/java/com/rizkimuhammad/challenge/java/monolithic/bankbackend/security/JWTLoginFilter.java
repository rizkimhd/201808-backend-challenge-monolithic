package com.rizkimuhammad.challenge.java.monolithic.bankbackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.LoginRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    TokenAuthentication tokenAuthentication;

    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException {
        LoginRequestVO credentials = new ObjectMapper().readValue(
                httpServletRequest.getInputStream(), LoginRequestVO.class);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword());
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException {
        String name = authentication.getName();
        tokenAuthentication.addAuthentication(response, name);
    }

}
