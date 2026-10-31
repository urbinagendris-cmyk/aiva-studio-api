package com.aivastudio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aivastudio.model.dto.ProjectRequest;
import com.aivastudio.model.dto.ProjectResponse;
import com.aivastudio.service.ProjectService;

@RestController
@RequestMapping("/api/aiva/proyectos")
public class AivaProjectController {

    private final ProjectService projectService;

    public AivaProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/crear")
    public ResponseEntity<ProjectResponse> create(@RequestBody ProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(request));
    }

    @GetMapping("/usuario/{userId}")
    public List<ProjectResponse> listByUser(@PathVariable Long userId) {
        return projectService.listForUser(userId);
    }
}