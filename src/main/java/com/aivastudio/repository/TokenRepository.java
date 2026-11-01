package com.aivastudio.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aivastudio.model.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenHashAndExpiresAtAfter(String tokenHash, LocalDateTime now);

    void deleteByTokenHash(String tokenHash);
}