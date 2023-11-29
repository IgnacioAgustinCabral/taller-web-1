package com.tallerwebi.infraestructura;

import com.tallerwebi.config.GoogleMapsConfig;
import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Viaje> obtenerViajes() {
        return this.repositorioViaje.listarViajes();
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
        if(filtro.getFecha() != null)
            viajesFiltrados.addAll(repositorioViaje.buscarPorFecha(filtro.getFecha().toString()));

        if(filtro.getOrigen().getId() == null && filtro.getDestino().getId() == null && filtro.getFecha() == null)
            viajesFiltrados.addAll(repositorioViaje.listarViajes());

        return viajesFiltrados;
    }

    @Override
    public void crearViaje(Viaje viaje) throws Exception {

        String resultado = validarCampos(viaje);

        if(!resultado.isEmpty())
            throw new Exception(resultado);

        /*String token = generarTokenValidacion();
        viaje.setTokenValidacionViaje(token);*/ //IMPLEMENTAR

        this.repositorioViaje.guardar(viaje);
    }

   /* private String generarTokenValidacion() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return bytesToHex(bytes);
    }*/

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

        boolean agregadoExito = false;

        if(viaje != null){
            Integer cantidadActual = viaje.getCantidad() - viaje.getListaPasajeros().size();
            Set<Usuario> pasajeros = viaje.getListaPasajeros();
            Boolean pasajeroExistente = pasajeros.contains(nuevoPasajero);

            if(!pasajeroExistente && pasajeros.size() < viaje.getCantidad()){
                pasajeros.add(nuevoPasajero);
                repositorioViaje.guardar(viaje);
                agregadoExito = true;
            }
        }
        return agregadoExito;
    }

    @Override
    public Boolean UsuarioUnido(Viaje viajeBuscado, Usuario usuario) {
        Set<Usuario> pasajeros = viajeBuscado.getListaPasajeros();
        return pasajeros.contains(usuario);
    }

    @Override
    public Boolean eliminarViaje(Long viajeId, Usuario usuario) throws Exception {
        Viaje viaje = repositorioViaje.buscarPorId(viajeId);

        if (viaje == null) {
            throw new Exception("El viaje a eliminar no existe.");
        }

        Usuario creador = viaje.getUsuario();

        if (!creador.getId().equals(usuario.getId())) {
            throw new Exception("Solo el usuario creador puede eliminar el viaje.");
        }

        repositorioViaje.eliminar(viaje);

        return true;
    }


    @Override
    public Boolean ModificarViaje(Usuario usuario,Viaje viaje, Long id) throws Exception {

        Viaje viajeBuscado = repositorioViaje.buscarPorId(id);

        if(viajeBuscado == null)
            throw new Exception("el viaje a modificar no existe");

        Usuario creador = viajeBuscado.getUsuario();

        if(!creador.getPassword().equals(usuario.getPassword()) && !creador.getEmail().equals(usuario.getEmail()))
            throw new Exception("solo el usuario creador puede modificar el viaje");

        boolean agregadoExito = false;

        /*Integer cantidadActual = viaje.getCantidad();
        Set<Usuario> pasajeros = viaje.getListaPasajeros();
        agregadoExito = pasajeros.add(nuevoPasajero);

            if(agregadoExito && cantidadActual > 0){
                viaje.setListaPasajeros(pasajeros);
                viaje.setCantidad(cantidadActual - 1);
                repositorioViaje.guardar(viaje);
            }
        }*/

        return agregadoExito;
    }

    @Override
    public Boolean ModificarViaje(Viaje viaje, Usuario usuario) throws Exception {

        Viaje viajeAModificar = this.repositorioViaje.buscarPorId(viaje.getId());

        if(viajeAModificar == null)
            throw new Exception("el viaje a modificar no existe.");

        Usuario creador = viajeAModificar.getUsuario();

        if(!creador.getId().equals(usuario.getId())) {
            throw new Exception("solo el usuario creador puede modificar los parametros del viaje");
        }

        String resultado = validarCampos(viaje);

        if(!resultado.isEmpty())
            throw new Exception(resultado);

        viajeAModificar.setCantidad(viaje.getCantidad());
        viajeAModificar.setFecha(viaje.getFecha());
        viajeAModificar.setDescripcion(viaje.getDescripcion());
        viajeAModificar.setListaPasajeros(viaje.getListaPasajeros());
        viajeAModificar.setOrigen(viaje.getOrigen());
        viajeAModificar.setDestino(viaje.getDestino());
        viajeAModificar.setNoNinios(viaje.getNoNinios());
        viajeAModificar.setNoMascotas(viaje.getNoMascotas());
        viajeAModificar.setNoFumar(viaje.getNoFumar());

        this.repositorioViaje.guardar(viajeAModificar);

        return true;
    }

    @Override
    public Set<Viaje> obtenerViajesDePasajero(Usuario usuario) {

        Usuario buscado  = repositorioUsuario.buscarUsuarioPorId(usuario.getId());

        return buscado.getListaViajes();
    }

    @Override
    public List<Viaje> obtenerViajesPorProvincia(String provincia) {
        return repositorioViaje.listarPorProvincia(provincia);
    }

    @Override
    public String obtenerClaveApiGoogleMaps() {
        return GoogleMapsConfig.GOOGLE_MAPS_API_KEY;
    }

    public String validarCampos(Viaje viaje){

        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaViaje = null;

        try {
            fechaViaje = LocalDate.parse(viaje.getFecha().toString());

        } catch (DateTimeParseException ex) {
            return "El formato de fecha es inválido";
        }

        if (fechaViaje.isBefore(fechaHoy)) {
            return "La fecha del viaje no es válida o es una fecha pasada";
        }

        if (viaje.getNoFumar() == null) {
            return "Debe especificar si se puede fumar";
        }
        if (viaje.getNoNinios() == null) {
            return "Debe especificar si se puede viajar con niños";
        }
        if (viaje.getNoMascotas() == null) {
            return "Debe especificar si se puede viajar con mascotas";
        }
        if (viaje.getCantidad() == null || viaje.getCantidad() <= 0) {
            return "La cantidad de pasajeros debe ser al menos 1";
        }
        if (viaje.getDescripcion() == null || viaje.getDescripcion().isEmpty()) {
            return "La descripción no puede estar vacía";
        }
        return "";
    }



}


