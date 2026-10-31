package com.aivastudio.model.dto;

import java.time.LocalDateTime;

import com.aivastudio.model.entity.CreditMovement;

public record CreditMovementResponse(Long id, String label, long amount, LocalDateTime date) {

    public static CreditMovementResponse from(CreditMovement movement) {
        return new CreditMovementResponse(
                movement.getId(),
                movement.getDescription(),
                movement.getAmount(),
                movement.getCreatedAt());
    }
}