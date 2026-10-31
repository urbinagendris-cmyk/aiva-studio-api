package com.aivastudio.exception;

/**
 * Excepcion para cuando un dato incumple una regla de negocio
 * (ej: precio invalido, stock negativo, proveedor inexistente, etc).
 * El GlobalExceptionHandler la traduce a HTTP 400 Bad Request.
 */
public class ReglaNegocioException extends RuntimeException {

    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}