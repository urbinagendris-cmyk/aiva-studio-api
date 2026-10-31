package com.aivastudio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aivastudio.model.dto.AiModelResponse;
import com.aivastudio.service.AiModelService;

@RestController
@RequestMapping("/api/aiva/modelos")
public class AiModelController {

    private final AiModelService aiModelService;

    public AiModelController(AiModelService aiModelService) {
        this.aiModelService = aiModelService;
    }

    @GetMapping
    public List<AiModelResponse> list() {
        return aiModelService.list();
    }
}