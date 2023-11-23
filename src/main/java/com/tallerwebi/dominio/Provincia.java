package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Provincia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO: declarar nombre como unique
    private String nombre;

    @Lob
    private byte[] imagen;

    public Provincia() {}

    public Provincia(String nombre, byte[] imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
