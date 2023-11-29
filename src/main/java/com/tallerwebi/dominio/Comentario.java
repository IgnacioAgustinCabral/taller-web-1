package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    private Double calificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_origen_id")
    private Usuario usuarioOrigen;

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", usuarioOrigen=" + usuarioOrigen +
                ", usuarioDestino=" + usuarioDestino +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "usuario_destino_id")
    private Usuario usuarioDestino;

    public Usuario getUsuarioOrigen() {
        return usuarioOrigen;
    }

    public void setUsuarioOrigen(Usuario usuarioOrigen) {
        this.usuarioOrigen = usuarioOrigen;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public Comentario () {

    }

    public Comentario (String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
}
