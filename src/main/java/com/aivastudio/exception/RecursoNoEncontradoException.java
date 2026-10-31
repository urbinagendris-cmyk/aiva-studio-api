package com.aivastudio.exception;

/**
 * Excepcion para cuando se busca por id un registro que no existe
 * (ej: proveedor_id que no esta en la base de datos).
 * El GlobalExceptionHandler la traduce a HTTP 404 Not Found.
 */
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}