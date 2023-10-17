package com.tallerwebi.dominio;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioViaje {
    List<Viaje> obtenerViajes();

    List<Viaje> obtenerViajesPorDestino(Ciudad destino);

    List<Viaje> obtenerViajesPorOrigen(Ciudad origen);

    List<Viaje> obtenerViajesPorFecha(String fecha);

    List<Viaje> obtenerViajesPorFiltroMultiple(Ciudad origen, Ciudad destino, String fecha_hora);

    void crearViaje(Viaje viaje);

    Viaje obtenerViajePorId(Long id);

    List<Viaje> obtenerViajesCreadosPorUnUsuario(Usuario usuario);

    List<Viaje> obtenerViajesPorProvincia(String provincia);
}
