package com.aivastudio.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.aivastudio.model.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prompts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prompt extends BaseEntity {

    @Column(nullable = false, length = 500)
    private String contenido;

    @Column(length = 100)
    private String categoria;

    @ManyToMany(mappedBy = "prompts")
    @Builder.Default
    private List<Proyecto> proyectos = new ArrayList<>();

    @OneToOne(mappedBy = "prompt")
    private GeneracionVideo generacionVideo;

    @Override
    public Boolean getEstadoActivo() {
        return super.getEstadoActivo();
    }
}