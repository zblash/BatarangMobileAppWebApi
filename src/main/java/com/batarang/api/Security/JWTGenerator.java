package com.batarang.api.Security;

import com.batarang.api.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {

    public static String generate(User jwtUser) {

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("roles", jwtUser.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "cokkgizli")
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
                .compact();
    }
}
