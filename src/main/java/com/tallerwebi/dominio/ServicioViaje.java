package com.tallerwebi.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServicioViaje {
    List<Viaje> obtenerViajes();

    List<Viaje> obtenerViajesPorDestino(Ciudad destino);

    List<Viaje> obtenerViajesPorOrigen(Ciudad origen);

    List<Viaje> obtenerViajesPorFecha(LocalDate fecha);

    List<Viaje> obtenerViajesPorFiltroMultiple(Ciudad origen, Ciudad destino, LocalDate fecha);

    void crearViaje(Viaje viaje);

    Viaje obtenerViajePorId(Long id);

    List<Viaje> obtenerViajesPorProvincia(String provincia);

    List<Viaje> obtenerViajesCreadosPorUnUsuario(Usuario usuario);

}
