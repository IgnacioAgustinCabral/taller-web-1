package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Provincia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO: declarar nombre como unique
    private String nombre;
    private String imagen;

    public Provincia() {}

    public Provincia(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
