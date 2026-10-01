package com.aivastudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aivastudio.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}