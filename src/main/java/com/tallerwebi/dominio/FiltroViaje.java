package com.tallerwebi.dominio;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class FiltroViaje {
    private Ciudad origen;
    private Ciudad destino;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    public FiltroViaje() {
        this.origen = null;
        this.destino = null;
        this.fecha = null;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
