package com.tallerwebi.dominio;
import javax.persistence.*;

@Entity
public class Viaje {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String descripcion;
    private  Integer cantidad;
    private  String fecha_hora;

    @ManyToOne
    private  Ciudad destino;

    @ManyToOne
    private Ciudad origen;
    @ManyToOne
    private Usuario usuario;



    public Viaje() {}
    public Viaje(Ciudad origen, Ciudad destino, String fecha_hora, Integer cantidad, String descripcion, Usuario creador) {

        this.origen = origen;
        this.destino = destino;
        this.fecha_hora = fecha_hora;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.usuario =  creador;
    }

    public Viaje(Long id) {
        this.id = id;
    }



    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_hora() {
        return this.fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Ciudad getDestino() {
        return this.destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public Long getId() {
        return this.id;
    }

    public Ciudad getOrigen() {
        return this.origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
