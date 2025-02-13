package com.oguz.authmanagementservice.auth.service.impl;

import com.oguz.authmanagementservice.auth.exception.TokenAlreadyInvalidatedException;
import com.oguz.authmanagementservice.auth.model.entity.InvalidTokenEntity;
import com.oguz.authmanagementservice.auth.repository.InvalidTokenRepository;
import com.oguz.authmanagementservice.auth.service.InvalidTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvalidTokenServiceImpl implements InvalidTokenService {

    private final InvalidTokenRepository invalidTokenRepository;


    @Override
    public void invalidateTokens(
            final Set<UUID> tokenIds
    ) {
        final Set<InvalidTokenEntity> invalidTokenEntities = tokenIds.stream()
                .map(tokenId -> InvalidTokenEntity.builder().tokenId(tokenId).build())
                .collect(Collectors.toSet());

        invalidTokenRepository.saveAll(invalidTokenEntities);
    }

    @Override
    public void checkForInvalidityOfToken(
            final UUID tokenId
    ) {
        final boolean isTokenInvalid = invalidTokenRepository.findByTokenId(tokenId).isPresent();

        if (isTokenInvalid)
        {
            throw new TokenAlreadyInvalidatedException(tokenId);
        }
    }

}
