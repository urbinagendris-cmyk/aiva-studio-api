package com.aivastudio.model.entity;

@Entity
@Table(name = "videos")
@AttributeOverride(name = "id", column = @Column(name = "id_video"))
public class Video extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_modelo", nullable = false)
    private ModeloIa modelo;

    @Embedded
    private ConfiguracionVideo configuracion;

    @Column(name = "costo_total_creditos", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoTotalCreditos;

    @Column(name = "peso_archivo_mb", precision = 10, scale = 2)
    private BigDecimal pesoArchivoMb;

    @Column(nullable = false)
    private Integer reproducciones = 0;

    @Column(name = "me_gusta", nullable = false)
    private Integer meGusta = 0;

    @Column(name = "url_preview", length = 500)
    private String urlPreview;

    @Column(name = "url_video_final", length = 500)
    private String urlVideoFinal;

    @Column(name = "fecha_generacion")
    private LocalDateTime fechaGeneracion;

    public Video() {}

    public Video(Long id, Proyecto proyecto, ModeloIa modelo, ConfiguracionVideo configuracion,
                 BigDecimal costoTotalCreditos, BigDecimal pesoArchivoMb,
                 String urlPreview, String urlVideoFinal, LocalDateTime fechaGeneracion) {
        super(id);
        // asigna cada parámetro a su campo
    }
    // + getters y setters
}