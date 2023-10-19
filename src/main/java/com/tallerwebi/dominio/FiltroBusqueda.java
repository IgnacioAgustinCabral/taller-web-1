package com.tallerwebi.dominio;

import java.util.Date;

public class FiltroBusqueda {

    Ciudad origen;
    Ciudad destino;
    Date fecha;

    public FiltroBusqueda() {}

    public FiltroBusqueda(Ciudad origen, Ciudad destino, Date fecha){
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
