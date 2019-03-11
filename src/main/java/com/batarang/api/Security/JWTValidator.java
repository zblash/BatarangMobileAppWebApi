package com.batarang.api.Security;

import com.batarang.api.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JWTValidator {

    Logger logger = LoggerFactory.getLogger(JWTValidator.class);
    private String secret = "cokkgizli";

    public User validate(String token) {

        User jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new User();

            jwtUser.setUserName(body.getSubject());
        }
        catch (ExpiredJwtException | SignatureException e) {
            logger.error(e.getMessage());

        }

        return jwtUser;
    }
}