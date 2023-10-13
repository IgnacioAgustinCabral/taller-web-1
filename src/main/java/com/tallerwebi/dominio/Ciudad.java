package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
public class Ciudad {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String nombre;
    @ManyToOne
    private  Provincia provincia;
    private  String background;

    public Ciudad() {

    }
    public Ciudad(String nombre, Provincia provincia, String background) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.background = background;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
