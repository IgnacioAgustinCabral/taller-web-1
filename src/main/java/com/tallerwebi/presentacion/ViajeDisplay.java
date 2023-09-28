package com.tallerwebi.presentacion;

import java.util.Objects;

public class ViajeDisplay {
    private String nombre;
    private String avatar;
    private String fechaViaje; //cambiar a DATE
    private String background;
    private String origen;
    private String destino;

    public ViajeDisplay(String nombre, String avatar, String fechaViaje, String background, String origen, String destino) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.fechaViaje = fechaViaje;
        this.background = background;
        this.origen = origen;
        this.destino = destino;
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

    public String getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
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
