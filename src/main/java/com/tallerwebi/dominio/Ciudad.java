package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Ciudad {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String nombre;

    @Lob
    private byte[] imagen;

    @ManyToOne
    private  Provincia provincia;

    public Ciudad() {
    }

    public Ciudad(String nombre, Provincia provincia, byte[] imagen) {
        this.nombre = nombre;
        this.provincia = provincia;
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
