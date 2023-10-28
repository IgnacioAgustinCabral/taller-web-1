package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

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
    public ModelAndView buscarViaje(@RequestParam (required = false) Long origen,
                                    @RequestParam (required = false) Long destino,
                                    @RequestParam (required = false) String fecha) {

        ModelMap model = new ModelMap();


        System.out.println("ORIGEN DEL VIAJE BUSCADO:" +  origen+"///////////////////////////////");
        System.out.println("DESTINO DEL VIAJE BUSCADO:" +  destino+"//////////////////////////////////");
        System.out.println("FECHA DEL VIAJE BUSCADO:" +  fecha +"//////////////////////////////////");
        List<Viaje> viajesFiltrados = new ArrayList<>();


        if(!isEmpty(origen) && !isEmpty(destino) && !isEmpty(fecha))
            viajesFiltrados = servicioViaje.obtenerViajesPorFiltroMultiple(origen,destino,fecha);
        if(!isEmpty(origen) && isEmpty(destino)&& isEmpty(fecha))
            viajesFiltrados = servicioViaje.obtenerViajesPorOrigen(origen);
      if(isEmpty(origen) && !isEmpty(destino) && isEmpty(fecha))
            viajesFiltrados = servicioViaje.obtenerViajesPorDestino(destino);
        if(isEmpty(origen)&& isEmpty(destino) && !isEmpty(fecha))
            viajesFiltrados = servicioViaje.obtenerViajesPorFecha(fecha);

        model.put("resultado",viajesFiltrados);


        return new ModelAndView("busqueda/busqueda",model);
    }
}
