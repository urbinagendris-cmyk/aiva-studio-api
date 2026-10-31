package com.aivastudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aivastudio.model.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByProyectoId(Long proyectoId);
    boolean existsByProyectoId(Long proyectoId);
    boolean existsByModeloId(Long modeloId);
}