package com.tallerwebi.infraestructura;

import com.tallerwebi.config.GoogleMapsConfig;
import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ServicioViajeImpl implements ServicioViaje {


    private RepositorioViaje repositorioViaje;
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioViajeImpl(RepositorioViaje repositorioViaje, RepositorioUsuario repositorioUsuario) {
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
    public Set<Viaje> obtenerViajesPorFiltroMultiple(FiltroViaje filtro) {

        Set<Viaje> viajesFiltrados = new HashSet<>();

        if(filtro.getOrigen().getId() != null )
            viajesFiltrados.addAll(repositorioViaje.buscarPorOrigen(filtro.getOrigen()));
        if(filtro.getDestino().getId() != null)
            viajesFiltrados.addAll(repositorioViaje.buscarPorDestino(filtro.getDestino()));
        if(filtro.getFecha() != null )
            viajesFiltrados.addAll(repositorioViaje.buscarPorFecha(filtro.getFecha().toString()));

        //TODO: faltan custom exceptions
        /*if(filtro == null)
            Throw FiltroNuloException("por alguna razon el filtro esta nulo");*/


        return viajesFiltrados;

       /* if (filtro.getOrigen().getId() != null && filtro.getDestino().getId() != null && filtro.getFecha() != null) {
            return repositorioViaje.buscarPorOrigenDestinoYfecha(filtro.getOrigen(),filtro.getDestino(),filtro.getFecha().toString());
        }
        else if (filtro.getOrigen().getId() != null && filtro.getDestino().getId() == null && filtro.getFecha() != null) {
            return repositorioViaje.buscarPorOrigenDestinoYfecha(filtro.getOrigen(), null,filtro.getFecha().toString());

        } else if (filtro.getOrigen().getId() == null && filtro.getDestino().getId() != null && filtro.getFecha() != null) {
            return  repositorioViaje.buscarPorOrigenDestinoYfecha(null, filtro.getDestino(),filtro.getFecha().toString());
        }

        else if (filtro.getOrigen().getId() == null && filtro.getDestino() == null && filtro.getFecha() != null) {
            return repositorioViaje.buscarPorFecha(filtro.getFecha().toString());
        } else if(filtro.getOrigen().getId() != null && filtro.getDestino() == null) {
            return repositorioViaje.buscarPorOrigen(filtro.getOrigen());
        }
        else if(filtro.getOrigen().getId() == null && filtro.getDestino() != null){
            return repositorioViaje.buscarPorDestino(filtro.getDestino());
        }
        else {
            return repositorioViaje.listarViajes();
        }*/
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
    public Boolean UnirAViaje(Usuario nuevoPasajero, Long id) {

        Viaje viaje = repositorioViaje.buscarPorId(id);

        boolean result = false;

        if(viaje != null){
            System.out.println("VIAJEEEEE///////////////////////" + viaje.getDestino());

            Set<Usuario> pasajeros = viaje.getListaPasajeros();
            result = pasajeros.add(nuevoPasajero);

            if(result){
                System.out.println("Se guarda el viaje///////////////////////" + pasajeros);
                viaje.setListaPasajeros(pasajeros);
                repositorioViaje.guardar(viaje);
            }
        }

        return result;
    }

    @Override
    public Boolean UsuarioUnido(Viaje viajeBuscado, Usuario usuario) {
        Set<Usuario> pasajeros = viajeBuscado.getListaPasajeros();

        for (Usuario pasajero: pasajeros
             ) {
            System.out.println("pasajero: " + pasajero.getNombre() + " id: " + pasajero.getId());
        }
        System.out.println("usuario a consultar: " + usuario.getNombre() + " id: " + usuario.getId());
        return pasajeros.contains(usuario);
    }

    @Override
    public List<Viaje> obtenerViajesPorProvincia(String provincia) {
        return repositorioViaje.listarPorProvincia(provincia);
    }

    @Override
    public String obtenerClaveApiGoogleMaps() {
        return GoogleMapsConfig.GOOGLE_MAPS_API_KEY;
    }



}


