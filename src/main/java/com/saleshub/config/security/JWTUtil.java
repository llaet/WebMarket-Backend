package com.saleshub.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String tokenSecret;

    @Value("${jwt.expiration}")
    private Long tokenExpiration;

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, getTokenSecretBytes())
                .compact();
    }

    public boolean IsValidToken(String token) {

        Claims claims = getClaims(token);

        if (claims != null) {

            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date actualDate = new Date(System.currentTimeMillis());

            if (username != null && expirationDate != null && actualDate.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(getTokenSecretBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUsername(String token) {

        Claims claims = getClaims(token);

        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    private byte[] getTokenSecretBytes(){
        return this.tokenSecret.getBytes();
    }
}
