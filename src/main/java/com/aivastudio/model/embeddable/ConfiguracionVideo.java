package com.aivastudio.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionVideo {

    @Column(nullable = false, length = 20)
    private String resolucion;

    @Column(nullable = false, length = 20)
    private String formato;

    @Column(nullable = false)
    private Integer duracion;

    @Column(nullable = false, length = 20)
    private String relacionAspecto;
}