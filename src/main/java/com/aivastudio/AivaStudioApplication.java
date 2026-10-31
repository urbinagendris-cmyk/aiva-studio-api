package com.aivastudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AivaStudioApplication {

    public static void main(String[] args) {
        loadDotenvProperties();
        SpringApplication.run(AivaStudioApplication.class, args);
    }

    private static void loadDotenvProperties() {
        try {
            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
            if (dotenv != null) {
                dotenv.entries().forEach(entry -> {
                    if (entry != null && entry.getKey() != null && !entry.getKey().isBlank()) {
                        System.setProperty(entry.getKey(), entry.getValue());
                    }
                });
            }
        } catch (Exception ex) {
            // Si no hay .env o falla la carga, la aplicación sigue iniciando con variables del entorno.
        }
    }
}