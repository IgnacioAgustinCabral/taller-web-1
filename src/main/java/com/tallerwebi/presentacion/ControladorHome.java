package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorHome {

    private ServicioProvincia servicioProvincia;
    private ServicioViaje servicioViaje;
    private ServicioUsuario servicioUsuario;
    private ServicioCiudad servicioCiudad;

    @Autowired
    public ControladorHome(ServicioViaje servicioViaje , ServicioProvincia servicioProvincia, ServicioCiudad servicioCiudad) {
        this.servicioViaje = servicioViaje;
        this.servicioProvincia = servicioProvincia;
        this.servicioCiudad = servicioCiudad;
    }


    @RequestMapping(path = "/home")
    public ModelAndView irAHome() {
        List<Viaje> datos = servicioViaje.obtenerViajes();
        List<Provincia> provincia = servicioProvincia.obtenerProvinciasConImagenes();
        List<Ciudad> ciudades = servicioCiudad.obtenerListaDeCiudades();
        ModelMap model = new ModelMap();

        model.put("ultimosViajes", datos);
        model.put("provincias", provincia);
        model.put("busqueda", new FiltroBusqueda());
        model.put("listaCiudades", ciudades);
        model.put("busqueda", new FiltroBusqueda());

        return new ModelAndView("home", model);
    }

    @RequestMapping(path= "/resultado-busqueda", method = RequestMethod.POST)
    public ModelAndView buscar(@ModelAttribute ("busqueda") FiltroBusqueda filtroBusqueda) {
        ModelMap model = new ModelMap();
        List<Viaje> viajesFiltrados;

/*
        System.out.println("FILTRO = " + filtroBusqueda.getOrigen() + "!!!!!!!!!!"
                + "FILTRO=" + filtroBusqueda.getFecha()+ " DESTINO" + filtroBusqueda.getDestino());
*/

        viajesFiltrados = servicioViaje.obtenerViajesPorFiltroMultiple(filtroBusqueda.getOrigen(),filtroBusqueda.getDestino(),String.valueOf(filtroBusqueda.getFecha()));

        model.put("result",viajesFiltrados);

        return new ModelAndView("home", model);
    }
 }
