package com.tallerwebi.dominio;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioViaje {
    void guardar(Viaje viaje);

    Viaje buscarPorId(Long id);

    List<Viaje> buscarPorDestino(Ciudad destino);

    void actualizar(Viaje viaje);

    List<Viaje> listarViajes();

    List<Viaje> buscarPorOrigen(Ciudad origen);

    List<Viaje> buscarPorFecha(LocalDate fecha);

    void eliminar(Viaje viaje);

    List<Viaje> buscarPorUsuario(Usuario usuario);

    List<Viaje> buscarPorOrigenDestinoYfecha(Ciudad origen, Ciudad destino, LocalDate fechaHora);

    List<Viaje> listarPorProvincia(String provincia);
}
