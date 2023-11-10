package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double calificacion;
    private String descripcion;

    @ManyToMany(mappedBy = "comentarios")
    private Set<Usuario> usuarios;

    public Comentario () {

    }

    public Comentario (Double calificacion, String descripcion) {
        this.calificacion = calificacion;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
