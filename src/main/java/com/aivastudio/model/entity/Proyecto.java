package com.aivastudio.model.entity;

@Entity
@Table(name = "proyectos")
@AttributeOverride(name = "id", column = @Column(name = "id_proyecto"))
public class Proyecto extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String prompt;

    @Column(nullable = false, length = 60)
    private String categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoProyecto estado = EstadoProyecto.BORRADOR;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos = new ArrayList<>();

    public Proyecto() {}

    public Proyecto(Long id, String titulo, String prompt, String categoria,
                    EstadoProyecto estado, Usuario usuario) {
        super(id);
        this.titulo = titulo;
        this.prompt = prompt;
        this.categoria = categoria;
        this.estado = (estado != null) ? estado : EstadoProyecto.BORRADOR;
        this.usuario = usuario;
    }
    // + getters y setters
}