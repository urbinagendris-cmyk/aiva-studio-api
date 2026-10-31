package com.aivastudio.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aivastudio.model.entity.ApiToken;

public interface ApiTokenRepository extends JpaRepository<ApiToken, Long> {

    Optional<ApiToken> findByTokenHashAndExpiresAtAfter(String tokenHash, LocalDateTime now);

    void deleteByTokenHash(String tokenHash);
}