package com.oguz.authmanagementservice.auth.service;

import com.oguz.authmanagementservice.auth.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface TokenService {
    Token generateToken(
            final Map<String, Object> claims
    );

    Token generateToken(
            final Map<String, Object> claims,
            final String refreshToken
    );

    UsernamePasswordAuthenticationToken getAuthentication(
            final String token
    );

    void verifyAndValidate(
            final String jwt
    );

    void verifyAndValidate(
            final Set<String> jwts
    );

    Jws<Claims> getClaims(
            final String jwt
    );

    Claims getPayload(
            final String jwt
    );

    UUID getId(
            final String jwt
    );
}
