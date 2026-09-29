package com.aivastudio.model.entity;

import com.aivastudio.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proyectos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proyecto extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private com.aivastudio.model.entity.Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "proyecto_prompt",
        joinColumns = @JoinColumn(name = "proyecto_id"),
        inverseJoinColumns = @JoinColumn(name = "prompt_id")
    )
    @Builder.Default
    private List<Prompt> prompts = new ArrayList<>();

    @OneToMany(mappedBy = "proyecto")
    @Builder.Default
    private List<Video> videos = new ArrayList<>();
}
