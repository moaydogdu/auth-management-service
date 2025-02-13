package com.oguz.authmanagementservice.auth.service;

import java.util.Set;
import java.util.UUID;

public interface InvalidTokenService {

    void invalidateTokens(
            final Set<UUID> tokenIds
    );

    void checkForInvalidityOfToken(
            final UUID tokenId
    );

}
