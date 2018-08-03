package com.rizkimuhammad.challenge.java.monolithic.bankbackend.security;

import com.google.gson.Gson;
import com.rizkimuhammad.challenge.java.monolithic.bankbackend.vo.ResponseVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Component
public class TokenAuthentication {

    static final long EXPIRATIONTIME = 1000 * 60 * 5; // 5 mins
    static final String SECRET = "nostrabackendchallenge";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username) throws IOException {
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
//        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        res.getWriter().write(new Gson().toJson(new ResponseVO(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(), JWT)));
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
        }
        return null;
    }

}
