package com.aivastudio.model.entity;

import java.time.LocalDateTime;

import com.aivastudio.model.base.BaseEntity;
import com.aivastudio.model.enums.MetodoPago;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "creditos")
public class Creditos extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne (optional = false)
    @JoinColumn(name = "ModeloIA", nullable = false)
    private ModeloIa modeloIA;

    @ManyToOne(optional = false)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @ManyToOne (optional = false)
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Enumerated (EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago pago = MetodoPago.PAYPAL;

    public Creditos() {
    }

    public Creditos(Long id, Usuario usuario, ModeloIa modeloIA, Proyecto proyecto, Video video, MetodoPago pago) {
        super(id);
        this.usuario = usuario;
        this.modeloIA = modeloIA;
        this.proyecto = proyecto;
        this.video = video;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ModeloIa getModeloIA() {
        return modeloIA;
    }

    public void setModeloIA(ModeloIa modeloIA) {
        this.modeloIA = modeloIA;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public MetodoPago getPago() {
        return pago;
    }

    public void setPago(MetodoPago pago) {
        this.pago = (pago != null) ? pago : MetodoPago.PAYPAL;
    }

    public void setPago(String pago) {
        if (pago != null) {
            try {
                this.pago = MetodoPago.valueOf(pago.toUpperCase());
            } catch (IllegalArgumentException e) {
                this.pago = MetodoPago.PAYPAL; // Valor predeterminado si no coincide
            }
        } else {
            this.pago = MetodoPago.PAYPAL; // Valor predeterminado si es nulo
        }
    }

    public Pago getPago(){
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}