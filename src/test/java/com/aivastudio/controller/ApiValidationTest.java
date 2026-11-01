package com.aivastudio.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.aivastudio.exception.GlobalExceptionHandler;
import com.aivastudio.service.AuthService;
import com.aivastudio.service.CreditService;
import com.aivastudio.service.ProjectService;

class ApiValidationTest {

    private static ValidatorFactory validatorFactory;

    private AuthService authService;
    private ProjectService projectService;
    private CreditService creditService;
    private MockMvc mockMvc;

    @BeforeAll
    static void createValidatorFactory() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    @AfterAll
    static void closeValidatorFactory() {
        validatorFactory.close();
    }

    @BeforeEach
    void setUp() {
        authService = mock(AuthService.class);
        projectService = mock(ProjectService.class);
        creditService = mock(CreditService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(
                        new AuthController(authService),
                        new AivaProjectController(projectService),
                        new AivaUserController(creditService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .setValidator(new SpringValidatorAdapter(validatorFactory.getValidator()))
                .build();
    }

    @Test
    void registrationRejectsBlankNameBeforeCallingService() throws Exception {
        mockMvc.perform(post("/api/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nombre": " ",
                                  "email": "ana@example.com",
                                  "password": "secret123"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message")
                        .value("El nombre es obligatorio y no puede superar 80 caracteres"));

        verifyNoInteractions(authService);
    }

    @Test
    void projectCreationRejectsBlankNameBeforeCallingService() throws Exception {
        mockMvc.perform(post("/api/aiva/proyectos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "usuarioId": 1,
                                  "nombre": " ",
                                  "prompt": "Un prompt válido",
                                  "modelId": "lumina-v2",
                                  "duracionSeg": 5
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("El nombre del proyecto es obligatorio"));

        verifyNoInteractions(projectService);
    }

    @Test
    void rechargeRejectsAmountsBelowMinimumBeforeCallingService() throws Exception {
        mockMvc.perform(post("/api/aiva/usuarios/recargar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "usuarioId": 1,
                                  "cantidad": 10
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("La recarga mínima es de 50 créditos"));

        verifyNoInteractions(creditService);
    }
}
