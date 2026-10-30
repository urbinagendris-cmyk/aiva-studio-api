package com.aivastudio.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class ConfiguracionVideo {

    @Column(nullable = false, length = 20)
    private String resolucion;

    @Column(nullable = false, length = 20)
    private String formato;

    @Column(nullable = false)
    private Integer duracion;

    public ConfiguracionVideo() {
    }

    public ConfiguracionVideo(String resolucion, String formato, Integer duracion) {
        this.resolucion = resolucion;
        this.formato = formato;
        this.duracion = duracion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

}