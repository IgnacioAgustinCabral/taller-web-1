package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_origen_id")
    private Usuario usuarioOrigen;

    @ManyToOne
    @JoinColumn(name = "usuario_destino_id")
    private Usuario usuarioDestino;

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
}
