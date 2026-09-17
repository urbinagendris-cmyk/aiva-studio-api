package com.aivastudio.service;

import com.aivastudio.model.Proyecto;
import com.aivastudio.model.Usuario;
import com.aivastudio.repository.ProyectoRepository;
import com.aivastudio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AivaStudioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    // REGLA 1: Creación de Proyecto de Render con Deducción de Créditos
    @Transactional
    public Proyecto crearProyectoRender(Long usuarioId, String nombre, String modeloIa, Integer costoCreditos, Integer duracionSeg) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("El usuario especificado no existe."));

        if (!usuario.getSuscripcionActiva()) {
            throw new IllegalArgumentException("El usuario no cuenta con una suscripción activa.");
        }

        if (usuario.getCreditosDisponibles() < costoCreditos) {
            throw new IllegalArgumentException("Créditos insuficientes. Disponibles: " 
                    + usuario.getCreditosDisponibles() + ", Requeridos: " + costoCreditos);
        }

        // Deducción de saldo de créditos
        usuario.setCreditosDisponibles(usuario.getCreditosDisponibles() - costoCreditos);
        usuarioRepository.save(usuario);

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(nombre);
        proyecto.setModeloIa(modeloIa);
        proyecto.setCostoCreditos(costoCreditos);
        proyecto.setDuracionSeg(duracionSeg);
        proyecto.setEstado("EN_PROCESO");
        proyecto.setFechaCreacion(LocalDateTime.now());
        proyecto.setUsuario(usuario);

        return proyectoRepository.save(proyecto);
    }

    // REGLA 2: Recarga de Créditos con Bonificación (+20% si la recarga es >= 500)
    @Transactional
    public Usuario recargarCreditos(Long usuarioId, Integer cantidadRecarga) {
        if (cantidadRecarga <= 0) {
            throw new IllegalArgumentException("El monto a recargar debe ser mayor a cero.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("El usuario especificado no existe."));

        int creditosSumar = cantidadRecarga;
        if (cantidadRecarga >= 500) {
            creditosSumar += (int) (cantidadRecarga * 0.20);
        }

        usuario.setCreditosDisponibles(usuario.getCreditosDisponibles() + creditosSumar);
        return usuarioRepository.save(usuario);
    }
}
