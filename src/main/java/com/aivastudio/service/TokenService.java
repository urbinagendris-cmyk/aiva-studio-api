package com.aivastudio.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aivastudio.model.entity.Token;
import com.aivastudio.model.entity.User;
import com.aivastudio.repository.TokenRepository;

@Service
public class TokenService {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int TOKEN_BYTES = 32;

    private final TokenRepository apiTokenRepository;

    public TokenService(TokenRepository apiTokenRepository) {
        this.apiTokenRepository = apiTokenRepository;
    }

    @Transactional
    public String issue(User user) {
        byte[] tokenBytes = new byte[TOKEN_BYTES];
        RANDOM.nextBytes(tokenBytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
        apiTokenRepository.save(new Token(
                hash(token),
                user,
                LocalDateTime.now(ZoneOffset.UTC).plusHours(12)));
        return token;
    }

    @Transactional(readOnly = true)
    public Optional<User> authenticate(String token) {
        return apiTokenRepository
                .findByTokenHashAndExpiresAtAfter(hash(token), LocalDateTime.now(ZoneOffset.UTC))
                .map(Token::getUser);
    }

    @Transactional
    public void revoke(String token) {
        apiTokenRepository.deleteByTokenHash(hash(token));
    }

    private String hash(String token) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(token.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return java.util.HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 is not available", ex);
        }
    }
}
