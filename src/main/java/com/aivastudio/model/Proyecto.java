package com.aivastudio.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proyectos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String modeloIa;

    @Column(nullable = false)
    private Integer costoCreditos;

    @Column(nullable = false)
    private Integer duracionSeg;

    @Column(nullable = false)
    private String estado;

    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
}