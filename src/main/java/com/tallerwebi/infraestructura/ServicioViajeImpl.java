package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Ciudad;
import com.tallerwebi.dominio.RepositorioViaje;
import com.tallerwebi.dominio.ServicioViaje;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ServicioViajeImpl implements ServicioViaje {


    private RepositorioViaje repositorioViaje;

    @Autowired
    public ServicioViajeImpl(RepositorioViaje repositorioViaje) {
        this.repositorioViaje = repositorioViaje;
    }

    @Override
    public List<Viaje> obtenerViajes() {
        return repositorioViaje.listarViajes();
    }

    @Override
    public List<Viaje> obtenerViajesPorDestino(Ciudad destino) {
        return repositorioViaje.buscarPorDestino(destino);
    }

    @Override
    public List<Viaje> obtenerViajesPorOrigen(Ciudad origen) {
        return repositorioViaje.buscarPorOrigen(origen);
    }

    @Override
    public List<Viaje> obtenerViajesPorFecha(String fecha) {
        return repositorioViaje.buscarPorFecha(String.valueOf(fecha));
    }

    @Override
    public List<Viaje> obtenerViajesPorFiltroMultiple(Ciudad origen, Ciudad destino, String fecha) {
        return repositorioViaje.buscarPorOrigenDestinoYfecha(origen,destino,fecha);
    }

    @Override
    public void crearViaje(Viaje viaje) {
        this.repositorioViaje.guardar(viaje);
    }

    @Override
    public Viaje obtenerViajePorId(Long id) {
        return repositorioViaje.buscarPorId(id);
    }

    @Override
    public List<Viaje> obtenerViajesCreadosPorUnUsuario(Usuario usuario) {
        return repositorioViaje.buscarPorUsuario(usuario);
      
    @Override
    public List<Viaje> obtenerViajesPorProvincia(String provincia) {
        return repositorioViaje.listarPorProvincia(provincia);
    }

}
