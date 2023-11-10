package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Viaje {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String descripcion;
    private  Integer cantidad;
    private String fecha;

    private Boolean noFumar;
    private Boolean noNinios;
    private Boolean noMascotas;
    @ManyToOne
    private  Ciudad destino;
    @ManyToOne
    private Ciudad origen;
    @ManyToOne
    private Usuario usuario;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Viaje_Pasajero",
            joinColumns = @JoinColumn(name="viaje_id"),
            inverseJoinColumns = @JoinColumn(name="usuario_id"))
    private Set<Usuario> listaPasajeros = new HashSet<Usuario>();



    public Viaje() {}
    public Viaje(Ciudad origen, Ciudad destino, String fecha, Integer cantidad, String descripcion, Usuario creador) {

        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
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

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public Set<Usuario> getListaPasajeros() {
        return listaPasajeros;
    }
    public void setListaPasajeros(Set<Usuario> listaPasajeros) {
        this.listaPasajeros = listaPasajeros;
    }

    public Boolean getNoFumar() {
        return noFumar;
    }

    public void setNoFumar(Boolean noFumar) {
        this.noFumar = noFumar;
    }

    public Boolean getNoNinios() {
        return noNinios;
    }

    public void setNoNinios(Boolean noNinios) {
        this.noNinios = noNinios;
    }

    public Boolean getNoMascotas() {
        return noMascotas;
    }

    public void setNoMascotas(Boolean noMascotas) {
        this.noMascotas = noMascotas;
    }

    public Boolean agregarPasajero(Usuario pasajero) {
        return this.listaPasajeros.add(pasajero);
    }

    public void setId(String id) {
    }
}
