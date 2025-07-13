package com.cognizant.jwt;

import java.util.Base64;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    private static final String SECRET_KEY = "mysecret";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String[] extractCredentials(String header) {
        // Header = "Basic base64(username:password)"
        String base64 = header.substring("Basic ".length());
        String decoded = new String(Base64.getDecoder().decode(base64));
        return decoded.split(":", 2);
    }
}
