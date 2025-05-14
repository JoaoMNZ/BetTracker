package com.bet.tracker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

    private static final String SECRET_KEY = "35c2ec3afff7e7c03e671f0c065f9305e496048ee633dbcb057d82a7b7d5a75afde9870bac40fa30830575b37fe69862c417adeba3a9a903e6fb142001378886b1f9015b6583b04f6a60753e514e5f0b9ea3d80b6cf9b9ae0810a8dfef25a3ace29a8323dd3ec42272f86a4b5c6252426516726dbf39eb38537f659012bdbc8ddfb2f3c4e792d2b23db038ab4b3f33234fd9b2f3fd0e8889e2935cbb006843f288834c78abc799b0bf24e9d43a777f3cbf7516b87d574590a56507bbb44616dc90c97266453a5d3055ca728927dcb52124ff666743a489cb141428a309838f124c4ef7a6d7d8de3ad307bff280ea483c0d68d2f8fd15b5fa3cb9a98a3d90ce53";
    private static final String ISSUER = "bettracker-api";

    public String generateToken(BettorDetails bettorDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(bettorDetails.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Error generating token.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Invalid or expired token.");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(24).toInstant();
    }

}