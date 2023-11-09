package com.tallerwebi.dominio;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ServicioViaje {
    List<Viaje> obtenerViajes();

    List<Viaje> obtenerViajesPorDestino(Ciudad destino);

    List<Viaje> obtenerViajesPorOrigen(Ciudad origen);

    List<Viaje> obtenerViajesPorFecha(String fecha);

    Set<Viaje> obtenerViajesPorFiltroMultiple(FiltroViaje filtro);

    void crearViaje(Viaje viaje);

    Viaje obtenerViajePorId(Long id);

    List<Viaje> obtenerViajesPorProvincia(String provincia);

    List<Viaje> obtenerViajesCreadosPorUnUsuario(Usuario usuario);

    Boolean UnirAViaje(Usuario usuario, Long viaje);

    Boolean UsuarioUnido(Viaje viajeBuscado, Usuario usuario);
}
