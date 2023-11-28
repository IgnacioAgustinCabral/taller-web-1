package com.tallerwebi.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Viaje {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String descripcion;
    //@NotNull(message = "La cantidad es requerida")
    //@Min(value = 1, message = "La cantidad debe ser al menos 1")
    private  Integer cantidad;
    /*@NotNull(message = "La fecha del viaje es requerida")*/
    /*@Future(message = "La fecha del viaje debe ser en el futuro")*/
    private String fecha;
/*    @NotNull(message = "Debes especificar si se permite fumar")*/
    private Boolean noFumar;
/*    @NotNull(message = "Debes especificar si se permiten niños")*/
    private Boolean noNinios;
//    @NotNull(message = "Debes especificar si se permiten mascotas")
    private Boolean noMascotas;
    @ManyToOne
//    @NotNull(message = "El destino es un campo requerido")
    private  Ciudad destino;
    @ManyToOne
    /*@NotNull(message = "El origen es un campo requerido")*/
    private Ciudad origen;
    @ManyToOne
    private Usuario usuario;
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Viaje_Pasajero",
            joinColumns = @JoinColumn(name="viaje_id"),
            inverseJoinColumns = @JoinColumn(name="usuario_id"))
    private Set<Usuario> listaPasajeros = new HashSet<Usuario>();



    public Viaje() {
    }
    public Viaje(Ciudad origen, Ciudad destino, String fecha, Boolean noFumar, Boolean noNinios, Boolean noMascotas, Integer cantidad, String descripcion, Usuario creador) {

        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.noFumar = noFumar;
        this.noNinios = noNinios;
        this.noMascotas = noMascotas;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.usuario =  creador;
    }

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
    public void setId(Long id) {
        this.id = id;
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

/*    public Boolean isOrigenValid() {
        return this.origen != null && this.origen.getNombre() != null && !this.origen.getNombre().equals("-- Selecciona una ciudad de Origen --");
    }

    public Boolean isDestinoValid() {
        return this.destino != null && this.destino.getNombre() != null && !this.destino.getNombre().equals("-- Selecciona una ciudad de Origen --");
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viaje)) return false;
        Viaje viaje = (Viaje) o;
        return Objects.equals(id, viaje.id) && Objects.equals(cantidad, viaje.cantidad) && Objects.equals(fecha, viaje.fecha) && Objects.equals(destino, viaje.destino) && Objects.equals(origen, viaje.origen) && Objects.equals(usuario, viaje.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad, fecha, destino, origen, usuario);
    }
}
