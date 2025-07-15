package com.kadoo_academy.kadoo.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kadoo_academy.kadoo.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("kadoo-api")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withClaim("name", user.getName())
                    .withClaim("role", extractRole(user))
                    .withExpiresAt(generateExpiration())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("kadoo-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpiration() {
        return LocalDateTime.now()
                .plusHours(4)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    private String extractRole(User user) {
        if (user.getAdmin() != null) return "ROLE_ADMIN";
        if (user.getMentor() != null) return "ROLE_MENTOR";
        if (user.getStudent() != null) return "ROLE_STUDENT";
        return "ROLE_UNKNOWN";
    }
}