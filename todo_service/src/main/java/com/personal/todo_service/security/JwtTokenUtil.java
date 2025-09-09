package com.personal.todo_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JwtTokenUtil {

    private final PublicKey publicKey;

    public JwtTokenUtil() throws Exception {
        // Load PEM file from classpath: src/main/resources/keys/public_key.pem
        InputStream is = getClass().getClassLoader().getResourceAsStream("keys/public_key.pem");
        if (is == null) {
            throw new RuntimeException("public_key.pem not found in resources/keys");
        }
        String pubKey = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        this.publicKey = PemUtils.parseRSAPublicKeyFromPem(pubKey);
    }

    /**
     * Validate JWT signature & expiration.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("JWT validation failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Extract all claims (body).
     */
    public Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extract `sub` claim as UserId.
     */
    public String getUserId(String token) {
        return getAllClaims(token).getSubject();
    }

    /**
     * Extract authorities from JWT claim "roles".
     */
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        Claims claims = getAllClaims(token);
        Object rolesObj = claims.get("roles");
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (rolesObj instanceof String roleStr) {
            authorities.add(new SimpleGrantedAuthority(roleStr));
        } else if (rolesObj instanceof List<?> roleList) {
            roleList.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        }

        return authorities;
    }
}
