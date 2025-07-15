package com.kadoo_academy.kadoo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "sHlM0rQrlP";
    private static final long EXPIRATION_MS = 86400000; // 1 dia

    public String generate(String username) {
        return JWT.create()
                .withIssuer("auth-api")
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
}
