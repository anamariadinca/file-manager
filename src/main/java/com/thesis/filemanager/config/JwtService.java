package com.thesis.filemanager.config;

import com.thesis.filemanager.config.security.SecurityUtils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;
import java.util.Base64;

@Slf4j
@Service
public class JwtService {


    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    private final byte[] signingKey;
    private final SecurityUtils securityUtils;

    public JwtService(@Value("${jwt.secret}") String secretKey, SecurityUtils securityUtils) {
        // Decode the Base64 encoded secret key
        this.signingKey = Base64.getDecoder().decode(secretKey);
        this.securityUtils = securityUtils;
    }

    private byte[] getSigningKey() {
        return signingKey;
    }

    public boolean isTokenValid(String token, String guid) {
        final String tokenGuid = extractAndDecryptGuid(token);
        return (tokenGuid.equals(guid)) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        //todo extract username from token
        String encryptedUsername = extractClaim(token, claims -> claims.get("username", String.class));
        String decryptedUsername = securityUtils.decrypt(encryptedUsername);
        return decryptedUsername;
    }

    public String extractEncryptedGuid(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractAndDecryptGuid(String token) {
        String encryptedGuid = extractClaim(token, Claims::getSubject);
        return securityUtils.decrypt(encryptedGuid);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        JwtParser parser = Jwts.parser().setSigningKey(getSigningKey());
        Jwt jwt = parser.parse(token);
        return (Claims) jwt.getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}