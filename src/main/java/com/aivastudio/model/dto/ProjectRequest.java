package com.aivastudio.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectRequest(
        @NotNull(message = "No puedes operar sobre otra cuenta")
        Long usuarioId,
        @NotBlank(message = "El nombre del proyecto es obligatorio")
        String nombre,
        @NotBlank(message = "La descripción del proyecto es obligatoria")
        String prompt,
        String modelId,
        String modeloIa,
        Long costoCreditos,
        Integer duracionSeg,
        String formato,
        String estilo) {
}