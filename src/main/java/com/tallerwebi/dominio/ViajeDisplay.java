package com.tallerwebi.dominio;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class ViajeDisplay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaViaje;
    private String background;
    private String origen;
    private String destino;

    public ViajeDisplay(String nombre, String avatar, Date fechaViaje, String background, String origen, String destino) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.fechaViaje = fechaViaje;
        this.background = background;
        this.origen = origen;
        this.destino = destino;
    }

    public ViajeDisplay() {

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(Date fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViajeDisplay that = (ViajeDisplay) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(avatar, that.avatar) && Objects.equals(fechaViaje, that.fechaViaje) && Objects.equals(background, that.background) && Objects.equals(origen, that.origen) && Objects.equals(destino, that.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, avatar, fechaViaje, background, origen, destino);
    }
}
