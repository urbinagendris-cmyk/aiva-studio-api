package com.aivastudio.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.aivastudio.model.base.BaseEntity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
@AttributeOverride(name = "id", column = @Column(name = "id_usuario"))
public class Usuario extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private Boolean activo = true;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proyecto> proyectos = new ArrayList<>();

    public Usuario() {}

    public Usuario(Long id, String nombre, String email, Boolean activo) {
        super(id);
        this.nombre = nombre;
        this.email = email;
        this.activo = (activo != null) ? activo : Boolean.TRUE;
    }

    // + getters y setters (en setActivo puedes aplicar el mismo "si es null, true")
}