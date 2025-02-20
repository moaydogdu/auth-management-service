package com.oguz.authmanagementservice.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenClaims {
    USER_ID("userId"),
    USER_STATUS("userStatus"),
    ISSUED_AT("iat"),
    EXPIRES_AT("exp"),
    ALGORITHM("alg"),
    TYP("typ");

    private final String value;
}
