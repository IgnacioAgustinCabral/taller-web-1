package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ModelAndView buscarViaje(@ModelAttribute("viajeBuscado") FiltroViaje viajeBuscado, ModelMap model, HttpServletRequest request) {

        //TODO: crear customExceptions para los casos poner try catch en los controladores
        /*try{
            var viajesFiltrados = servicioViaje.obtenerViajesPorFiltroMultiple(viajeBuscado);
        }catch(CustomExcetionNuestras e){
            devolver vista error
        }catch(Exception e){
            devolver vista error inatajable
        }*/

        Set<Viaje> viajesFiltrados = servicioViaje.obtenerViajesPorFiltroMultiple(viajeBuscado);

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario != null)
            viajesFiltrados = viajesFiltrados.stream()
                    .filter(elemento -> !elemento.getUsuario().getEmail().equals(usuario.getEmail()))
                    .collect(Collectors.toSet());

        model.addAttribute("filtroBuscado",viajeBuscado);
        model.addAttribute("viajesFiltrados",viajesFiltrados);
        model.put("session", session);

        return new ModelAndView("resultadoBusqueda",model);
    }
}
