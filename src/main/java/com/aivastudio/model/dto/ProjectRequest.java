package com.aivastudio.model.dto;

public record ProjectRequest(
        Long usuarioId,
        String nombre,
        String prompt,
        String modelId,
        String modeloIa,
        Long costoCreditos,
        Integer duracionSeg,
        String formato,
        String estilo) {
}