package com.oguz.authmanagementservice.auth.model.mappers;

import com.oguz.authmanagementservice.auth.model.Token;
import com.oguz.authmanagementservice.auth.model.dto.response.TokenResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnbTokenDTOMapper {

    public TokenResponse toResponse(
            final Token enbToken
    ) {
        return TokenResponse.builder()
                .accessToken(enbToken.getAccessToken())
                .accessTokenExpiresAt(enbToken.getAccessTokenExpiresAt())
                .refreshToken(enbToken.getRefreshToken())
                .build();
    }

}
