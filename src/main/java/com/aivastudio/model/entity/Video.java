package com.aivastudio.model.entity;

import com.aivastudio.model.base.BaseEntity;
import com.aivastudio.model.embeddable.ConfiguracionVideo;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(length = 500)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoVideo estado;

    @Embedded
    private ConfiguracionVideo configuracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @OneToOne(mappedBy = "video")
    private GeneracionVideo generacionVideo;
}

enum EstadoVideo {
    PENDIENTE,
    GENERANDO,
    COMPLETADO,
    ERROR
}