package com.aivastudio.model.entity;

import java.time.LocalDateTime;

import com.aivastudio.model.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "creditos")
public class Creditos extends BaseEntity {

   
    private Integer cantidad;
    private LocalDateTime fechaCreacion;

    public Creditos() {
    }

    public Creditos(Long id, Integer cantidad, LocalDateTime fechaCreacion) {
        super(id);
        this.cantidad = cantidad;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}