package com.tallerwebi.dominio;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
public class Gasto {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private  double monto;
    private  String descripcion;

    @ManyToOne
    private  Viaje viaje;
    private  boolean pago;


    public Gasto(){}
    public Gasto(String descripcion, double monto, Viaje viaje, boolean pago) {
        this.descripcion = descripcion;
        this.monto       = monto;
        this.viaje      = viaje;
        this.pago       = pago;
    }

    public Long getId() {
        return Id;
    }

    public boolean isPago() {
        return pago;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
