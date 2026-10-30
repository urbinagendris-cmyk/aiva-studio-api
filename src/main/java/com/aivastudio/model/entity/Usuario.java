package com.aivastudio.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.aivastudio.model.base.BaseEntity;
import com.aivastudio.model.embeddable.ConfiguracionVideo;

import yakarta.persisrence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity {

    private String nombre;

    @Embedded
    private ConfiguracionVideo configuracionVideo;

}