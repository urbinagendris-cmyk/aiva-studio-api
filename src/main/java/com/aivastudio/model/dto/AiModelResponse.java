package com.aivastudio.model.dto;

public record AiModelResponse(
        String id,
        String nombre,
        int costoPorSegundo,
        String categoria,
        String descripcion,
        String color) {
}