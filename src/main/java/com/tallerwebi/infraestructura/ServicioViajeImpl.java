package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ServicioViajeImpl implements ServicioViaje {


    private RepositorioViaje repositorioViaje;
    private RepositorioCiudad repositorioCiudad;

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

        return repositorioViaje.buscarPorFecha(fecha);
    }

    @Override
    public List<Viaje> obtenerViajesPorFiltroMultiple(FiltroViaje filtro) {

        /*Set<Viaje> viajesFiltrados = new HashSet<>();

        if(filtro.getOrigen() != null )
            viajesFiltrados.addAll(repositorioViaje.buscarPorOrigen(filtro.getOrigen()));
        if(filtro.getDestino() != null)
            viajesFiltrados.addAll(repositorioViaje.buscarPorDestino(filtro.getDestino()));
        if(filtro.getFecha() != null )
            viajesFiltrados.addAll(repositorioViaje.buscarPorFecha(filtro.getFecha().toString()));

        return viajesFiltrados;*/

        if (filtro.getOrigen() != null && filtro.getDestino() != null && filtro.getFecha() != null) {
            return repositorioViaje.buscarPorOrigenDestinoYfecha(filtro.getOrigen(),filtro.getDestino(),filtro.getFecha().toString());
        }
        else if (filtro.getOrigen() != null && filtro.getDestino() == null && filtro.getFecha() != null) {
            return repositorioViaje.buscarPorOrigenDestinoYfecha(filtro.getOrigen(), null,filtro.getFecha().toString());

        } else if (filtro.getOrigen() == null && filtro.getDestino() != null && filtro.getFecha() != null) {
            return  repositorioViaje.buscarPorOrigenDestinoYfecha(null, filtro.getDestino(),filtro.getFecha().toString());
        }

        else if (filtro.getOrigen() == null && filtro.getDestino() == null && filtro.getFecha() != null) {
            return repositorioViaje.buscarPorFecha(filtro.getFecha().toString());
        } else if(filtro.getOrigen() != null && filtro.getDestino() == null) {
            return repositorioViaje.buscarPorOrigen(filtro.getOrigen());
        }
        else if(filtro.getOrigen() == null && filtro.getDestino() != null){
            return repositorioViaje.buscarPorDestino(filtro.getDestino());
        }
        else {
            return repositorioViaje.listarViajes();
        }
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
    }

    @Override
    public List<Viaje> obtenerViajesPorProvincia(String provincia) {
        return repositorioViaje.listarPorProvincia(provincia);


    }
}
