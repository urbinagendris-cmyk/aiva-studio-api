package com.aivastudio.controller;

import com.aivastudio.model.Proyecto;
import com.aivastudio.model.Usuario;
import com.aivastudio.service.AivaStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/aiva")
public class AivaStudioController {

    @Autowired
    private AivaStudioService aivaStudioService;

    @PostMapping("/proyectos/crear")
    public ResponseEntity<?> crearProyecto(@RequestBody Map<String, Object> request) {
        try {
            Long usuarioId = Long.parseLong(request.get("usuarioId").toString());
            String nombre = request.get("nombre").toString();
            String modeloIa = request.get("modeloIa").toString();
            Integer costoCreditos = Integer.parseInt(request.get("costoCreditos").toString());
            Integer duracionSeg = Integer.parseInt(request.get("duracionSeg").toString());

            Proyecto proyecto = aivaStudioService.crearProyectoRender(usuarioId, nombre, modeloIa, costoCreditos, duracionSeg);
            return ResponseEntity.status(HttpStatus.CREATED).body(proyecto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/usuarios/recargar")
    public ResponseEntity<?> recargarCreditos(@RequestBody Map<String, Object> request) {
        try {
            Long usuarioId = Long.parseLong(request.get("usuarioId").toString());
            Integer cantidad = Integer.parseInt(request.get("cantidad").toString());

            Usuario usuario = aivaStudioService.recargarCreditos(usuarioId, cantidad);
            return ResponseEntity.ok(usuario);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }
}