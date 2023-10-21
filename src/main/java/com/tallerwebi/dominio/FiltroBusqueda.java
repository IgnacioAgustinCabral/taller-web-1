package com.tallerwebi.dominio;

import java.time.LocalDate;
import java.util.Date;

public class FiltroBusqueda {

    Ciudad origen;
    Ciudad destino;
    LocalDate fecha;

    public FiltroBusqueda() {}

    public FiltroBusqueda(Ciudad origen, Ciudad destino, LocalDate fecha){
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
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
