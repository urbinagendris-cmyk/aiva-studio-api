package com.aivastudio.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer creditosDisponibles;

    @Column(nullable = false)
    private Boolean suscripcionActiva;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Proyecto> proyectos;
}