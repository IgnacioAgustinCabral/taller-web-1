package com.tallerwebi.dominio;

import java.util.List;
import java.util.Set;

public interface ServicioViaje {
    List<Viaje> obtenerViajes();

    List<Viaje> obtenerViajesPorDestino(Ciudad destino);

    List<Viaje> obtenerViajesPorOrigen(Ciudad origen);

    List<Viaje> obtenerViajesPorFecha(String fecha);

    Set<Viaje> obtenerViajesPorFiltroMultiple(FiltroViaje filtro);

    void crearViaje(Viaje viaje) throws Exception;

    Viaje obtenerViajePorId(Long id);

    List<Viaje> obtenerViajesPorProvincia(String provincia);

    List<Viaje> obtenerViajesCreadosPorUnUsuario(Usuario usuario);

    String obtenerClaveApiGoogleMaps();

    Boolean UnirAViaje(Usuario usuario, Long viaje);

    Boolean UsuarioUnido(Viaje viajeBuscado, Usuario usuario);

    Boolean ModificarViaje(Usuario usuario,Viaje viaje, Long id) throws Exception;

    Boolean ModificarViaje(Viaje viaje,Usuario usuario) throws Exception;

    Set<Viaje> obtenerViajesDePasajero(Usuario usuario);

    Boolean eliminarViaje(Long viajeId, Usuario usuario) throws Exception;
}
