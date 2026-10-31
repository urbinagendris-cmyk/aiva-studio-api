package com.aivastudio.model.entity;

import java.math.BigDecimal;
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
@Table(name = "modelos_ia")
@AttributeOverride(name = "id", column = @Column(name = "id_modelo"))
public class ModeloIa extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String proveedor;

    @Column(nullable = false, length = 30)
    private String version;

    @Column(name = "costo_por_segundo", nullable = false, precision = 10, scale = 4)
    private BigDecimal costoPorSegundo;

    @Column(nullable = false)
    private Boolean activo = true;

    public ModeloIa() {}

    public ModeloIa(Long id, String nombre, String proveedor, String version,
                    BigDecimal costoPorSegundo, Boolean activo) {
        super(id);
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.version = version;
        this.costoPorSegundo = costoPorSegundo;
        this.activo = (activo != null) ? activo : Boolean.TRUE;
    }
    // + getters y setters
}
