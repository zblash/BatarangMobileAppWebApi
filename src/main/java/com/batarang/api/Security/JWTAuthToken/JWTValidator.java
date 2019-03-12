package com.batarang.api.Security.JWTAuthToken;

import com.batarang.api.Model.User;
import com.batarang.api.Service.Concrete.RoleService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTValidator {

    @Autowired
            private RoleService roleService;

    Logger logger = LoggerFactory.getLogger(JWTValidator.class);
    private String secret = "cokkgizli";

    public User validate(String token) {

        User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = new User();

            user.setUserName(body.getSubject());
            user.setRole(roleService.findByRoleName(body.get("role").toString()));
            user.setId(Long.parseLong(body.get("userId").toString()));
        }
        catch (ExpiredJwtException | SignatureException e) {
            throw e;
        }

        return user;
    }
}