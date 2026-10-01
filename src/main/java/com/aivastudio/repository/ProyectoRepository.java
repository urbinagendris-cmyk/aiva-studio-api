package com.aivastudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aivastudio.model.entity.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}