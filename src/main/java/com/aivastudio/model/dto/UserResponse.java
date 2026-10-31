package com.aivastudio.model.dto;

import com.aivastudio.model.entity.User;

public record UserResponse(
        Long id,
        String nombre,
        String email,
        long creditosDisponibles,
        boolean suscripcionActiva,
        String rol) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCredits(),
                true,
                "CREATOR");
    }
}