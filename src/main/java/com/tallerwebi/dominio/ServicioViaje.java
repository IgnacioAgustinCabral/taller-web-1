package com.tallerwebi.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ServicioViaje {
    List<Viaje> obtenerViajes();

    List<Viaje> obtenerViajesPorDestino(Ciudad destino);

   // List<Viaje> obtenerViajesPorOrigen(Ciudad origen);

    List<Viaje> obtenerViajesPorOrigen(Ciudad origen);

    List<Viaje> obtenerViajesPorFecha(String fecha);

    //List<Viaje> obtenerViajesPorFiltroMultiple(FiltroViaje filtro);

    List<Viaje> obtenerViajesPorFiltroMultiple(Ciudad origen, Ciudad destino, String fecha);

    void crearViaje(Viaje viaje);

    Viaje obtenerViajePorId(Long id);

    List<Viaje> obtenerViajesPorProvincia(String provincia);

    List<Viaje> obtenerViajesCreadosPorUnUsuario(Usuario usuario);

    List<Viaje> obtenerViajesPorFiltroMultiple(Long origen, Long destino, String fecha);

    List<Viaje> obtenerViajesPorOrigen(Long origen);

    List<Viaje> obtenerViajesPorDestino(Long destino);
}
