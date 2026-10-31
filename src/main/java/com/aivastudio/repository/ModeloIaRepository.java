package com.aivastudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aivastudio.model.entity.ModeloIa;

@Repository
public interface ModeloIaRepository extends JpaRepository<ModeloIa, Long> {
    List<ModeloIa> findByActivoTrue();
    boolean existsByNombreIgnoreCaseAndVersionIgnoreCase(String nombre, String version);
    boolean existsByNombreIgnoreCaseAndVersionIgnoreCaseAndIdNot(String nombre, String version, Long id);
}