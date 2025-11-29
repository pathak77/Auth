package com.prod.auth.JwtConfig;

import com.prod.auth.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.security.KeyFactory;
import java.util.Date;
import java.util.Map;

@Builder
public class TokenHelper {


    private String appName;

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    private String buildToken(
            Map<String, Object> extraClaims,
            User user,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        return KeyFactory();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private String extractClaim(String token, Object getSubject) {
        return null;
    }

    public String generateToken(String userName){
        return generateToken(userName, generateExpirationDate(), getSigningKey());
    }

    private long generateExpirationDate() {
        return jwtExpiration;
    }
}