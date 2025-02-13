package com.oguz.authmanagementservice.auth.service.impl;

import com.oguz.authmanagementservice.auth.model.Token;
import com.oguz.authmanagementservice.auth.model.config.AuthConfigurationParameter;
import com.oguz.authmanagementservice.auth.model.enums.TokenClaims;
import com.oguz.authmanagementservice.auth.model.enums.TokenType;
import com.oguz.authmanagementservice.auth.service.InvalidTokenService;
import com.oguz.authmanagementservice.auth.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final AuthConfigurationParameter authConfigurationParameter;
    private final InvalidTokenService invalidTokenService;

    public Token generateToken(
            final Map<String, Object> claims
    ) {
        final long currentTimeMillis = System.currentTimeMillis();

        final Date tokenIssuedAt = new Date(currentTimeMillis);

        final Date accessTokenExpiresAt = DateUtils.addMinutes(
                new Date(currentTimeMillis),
                authConfigurationParameter.getAccessTokenExpireMinute()
        );

        final String accessToken = Jwts.builder()
                .header()
                .type(TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(authConfigurationParameter.getIssuer())
                .issuedAt(tokenIssuedAt)
                .expiration(accessTokenExpiresAt)
                .signWith(authConfigurationParameter.getPrivateKey())
                .claims(claims)
                .compact();

        final Date refreshTokenExpiresAt = DateUtils.addDays(
                new Date(currentTimeMillis),
                authConfigurationParameter.getRefreshTokenExpireDay()
        );

        final String refreshToken = Jwts.builder()
                .header()
                .type(TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(authConfigurationParameter.getIssuer())
                .issuedAt(tokenIssuedAt)
                .expiration(refreshTokenExpiresAt)
                .signWith(authConfigurationParameter.getPrivateKey())
                .claim(TokenClaims.USER_ID.getValue(), claims.get(TokenClaims.USER_ID.getValue()))
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .accessTokenExpiresAt(accessTokenExpiresAt.toInstant().getEpochSecond())
                .refreshToken(refreshToken)
                .build();
    }

    public Token generateToken(
            final Map<String, Object> claims,
            final String refreshToken
    ) {
        final long currentTimeMillis = System.currentTimeMillis();

        final UUID refreshTokenId = this.getId(refreshToken);

        invalidTokenService.checkForInvalidityOfToken(refreshTokenId);

        final Date accessTokenIssuedAt = new Date(currentTimeMillis);

        final Date accessTokenExpiresAt = DateUtils.addMinutes(
                new Date(currentTimeMillis),
                authConfigurationParameter.getAccessTokenExpireMinute()
        );

        final String accessToken = Jwts.builder()
                .header()
                .type(TokenType.BEARER.getValue())
                .and()
                .id(UUID.randomUUID().toString())
                .issuer(authConfigurationParameter.getIssuer())
                .issuedAt(accessTokenIssuedAt)
                .expiration(accessTokenExpiresAt)
                .signWith(authConfigurationParameter.getPrivateKey())
                .claims(claims)
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .accessTokenExpiresAt(accessTokenExpiresAt.toInstant().getEpochSecond())
                .refreshToken(refreshToken)
                .build();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(
            final String token
    ) {
        final Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(authConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(token);

        final JwsHeader jwsHeader = claimsJws.getHeader();
        final Claims payload = claimsJws.getPayload();

        final Jwt jwt = new Jwt(
                token,
                payload.getIssuedAt().toInstant(),
                payload.getExpiration().toInstant(),
                Map.of(
                        TokenClaims.TYP.getValue(), jwsHeader.getType(),
                        TokenClaims.ALGORITHM.getValue(), jwsHeader.getAlgorithm()
                ),
                payload
        );

        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return UsernamePasswordAuthenticationToken
                .authenticated(jwt, null, authorities);

    }

    public void verifyAndValidate(
            final String jwt
    ) {
        Jwts.parser()
                .verifyWith(authConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(jwt);
    }

    @Override
    public void verifyAndValidate(
            final Set<String> jwts
    ) {
        jwts.forEach(this::verifyAndValidate);
    }

    public Jws<Claims> getClaims(
            final String jwt
    ) {
        return Jwts.parser()
                .verifyWith(authConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(jwt);
    }

    public Claims getPayload(
            final String jwt
    ) {
        return Jwts.parser()
                .verifyWith(authConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public UUID getId(
            final String jwt
    ) {
        return UUID.fromString(Jwts.parser()
                .verifyWith(authConfigurationParameter.getPublicKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getId());
    }

}
