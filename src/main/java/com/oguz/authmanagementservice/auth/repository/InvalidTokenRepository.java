package com.oguz.authmanagementservice.auth.repository;

import com.oguz.authmanagementservice.auth.model.entity.InvalidTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InvalidTokenRepository extends JpaRepository<InvalidTokenEntity, UUID> {

    Optional<InvalidTokenEntity> findByTokenId(final UUID tokenId);

}
