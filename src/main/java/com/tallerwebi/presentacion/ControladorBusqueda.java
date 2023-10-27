package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorBusqueda{

    private ServicioViaje servicioViaje;
    private ServicioCiudad servicioCiudad;

    @Autowired
    public ControladorBusqueda(ServicioViaje servicioViaje, ServicioCiudad servicioCiudad){

        this.servicioViaje = servicioViaje;
        this.servicioCiudad = servicioCiudad;
    }

    /*@RequestMapping(path= "/buscar-viaje", method = RequestMethod.POST)
    public ModelAndView buscar(@RequestParam(required = false) Ciudad origen, @RequestParam(required = false)Ciudad destino , @RequestParam(required = false)@DateTimeFormat(pattern = "dd/MM/yyyy") Date fecha_hora){
        ModelMap model = new ModelMap();
        List<Viaje> viajesFiltrados;
        List <Ciudad> ciudades = servicioCiudad.obtenerListaDeCiudades();
        model.put("ciudades", ciudades);

        if(origen != null && destino != null && fecha_hora != null) {
            viajesFiltrados = servicioViaje.obtenerViajesPorFiltroMultiple(origen, destino, String.valueOf(fecha_hora));
            model.put("resultados", viajesFiltrados);
        }   else if (origen != null && destino == null && fecha_hora != null){
            viajesFiltrados = servicioViaje.obtenerViajesPorOrigen(origen);
            model.put("resultado", viajesFiltrados);
        }           else if (origen == null && destino != null && fecha_hora != null){
            viajesFiltrados = servicioViaje.obtenerViajesPorDestino(destino);
            model.put("resultado", viajesFiltrados);
        }               else if(origen == null && destino == null ){
            viajesFiltrados = servicioViaje.obtenerViajesPorFecha(String.valueOf(fecha_hora));
            model.put("resultados",viajesFiltrados);
        }                   else{
            viajesFiltrados = servicioViaje.obtenerViajes();
            model.put("resultados",viajesFiltrados);
        }

        return new ModelAndView("/busqueda/busqueda",model);
    }*/

    @RequestMapping(value="/buscar-viaje", method= RequestMethod.POST)
    public ModelAndView buscarViaje(@ModelAttribute("viajeBuscado") FiltroViaje viajeBuscado, ModelMap model) {

        System.out.println("ORIGEN DEL VIAJE BUSCADO:" +  viajeBuscado.getOrigen().getId()+"///////////////////////////////");
        System.out.println("DESTINO DEL VIAJE BUSCADO:" +  viajeBuscado.getDestino().getId() +"//////////////////////////////////");
        System.out.println("FECHA DEL VIAJE BUSCADO:" +  viajeBuscado.getFecha().toString() +"//////////////////////////////////");

        var viajesFiltrados = servicioViaje.obtenerViajesPorFiltroMultiple(viajeBuscado);

        model.addAttribute("filtroBuscado",viajeBuscado);
        model.addAttribute("viajesFiltrados",viajesFiltrados);


        return new ModelAndView("pruebadefiltro",model);
    }
}
