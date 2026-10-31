package com.aivastudio.model.dto;

import java.time.LocalDateTime;

import com.aivastudio.model.entity.Project;

public record ProjectResponse(
        Long id,
        Long usuarioId,
        String nombre,
        String prompt,
        String modelId,
        String modeloIa,
        long creditosDisponibles,
        long costoCreditos,
        int duracionSeg,
        String formato,
        String estilo,
        String estado,
        LocalDateTime fechaCreacion) {

    public static ProjectResponse from(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getUser().getId(),
                project.getName(),
                project.getPrompt(),
                project.getModelId(),
                project.getModelName(),
                project.getUser().getCredits(),
                project.getCreditCost(),
                project.getDurationSeconds(),
                project.getFormat(),
                project.getStyle(),
                project.getStatus(),
                project.getCreatedAt());
    }
}