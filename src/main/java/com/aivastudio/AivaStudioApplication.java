package com.aivastudio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AivaStudioApplication {

	public static void main(String[] args) {
		loadEnv();
		SpringApplication.run(AivaStudioApplication.class, args);
	}

	// Lee el archivo .env de la raíz del proyecto y carga sus variables
	// como System properties, para que Spring pueda resolver ${DB_URL}, etc.
	private static void loadEnv() {
		Path envFile = Path.of(".env");
		if (!Files.exists(envFile)) {
			System.out.println("Aviso: no se encontró el archivo .env en la raíz del proyecto.");
			return;
		}
		try {
			List<String> lines = Files.readAllLines(envFile);
			for (String line : lines) {
				line = line.trim();
				if (line.isEmpty() || line.startsWith("#")) {
					continue;
				}
				int separatorIndex = line.indexOf('=');
				if (separatorIndex == -1) {
					continue;
				}
				String key = line.substring(0, separatorIndex).trim();
				String value = line.substring(separatorIndex + 1).trim();
				System.setProperty(key, value);
			}
		} catch (IOException e) {
			System.err.println("No se pudo leer el archivo .env: " + e.getMessage());
		}
	}

}