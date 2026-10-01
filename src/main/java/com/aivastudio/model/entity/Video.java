package com.aivastudio.model.entity;

import com.aivastudio.model.base.BaseEntity;
import com.aivastudio.model.embeddable.ConfiguracionVideo;
import com.aivastudio.model.enums.EstadoVideo;
import jakarta.persistence.*;
import lombok.*;

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