package com.batarang.api.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JWTAuthenticationFilter() {
        super("/api**");
    }

        @Override
        public Authentication attemptAuthentication (HttpServletRequest httpServletRequest, HttpServletResponse
        httpServletResponse) throws AuthenticationException, IOException, ServletException {

            String header = httpServletRequest.getHeader("Authorization");


            if (header == null || !header.startsWith("Bearer ")) {
                throw new RuntimeException("Token missing or invalid");
            }

            String authenticationToken = header.substring(7);

            JWTAuthToken token = new JWTAuthToken(authenticationToken);
            return getAuthenticationManager().authenticate(token);
        }
    }