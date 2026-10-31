package com.aivastudio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aivastudio.model.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByUser_IdOrderByCreatedAtDesc(Long userId);
}