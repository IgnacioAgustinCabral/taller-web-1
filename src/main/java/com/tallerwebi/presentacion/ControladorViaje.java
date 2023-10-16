package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorViaje {

    private  ServicioViaje servicioViaje;
    private ServicioCiudad servicioCiudad;

    @Autowired
    public ControladorViaje(ServicioViaje servicioViaje, ServicioCiudad servicioCiudad){

        this.servicioViaje = servicioViaje;
        this.servicioCiudad = servicioCiudad;
    }

    @RequestMapping(value = "/crear-viaje", method = RequestMethod.GET)
    public ModelAndView mostrarVistaCrearViaje() {
        List<Ciudad> ciudades = servicioCiudad.obtenerListaDeCiudades();
        ModelMap modelo = new ModelMap();
        modelo.put("viaje", new Viaje());
        modelo.put("ciudades", ciudades);
        return new ModelAndView("crear-viaje",modelo);
    }

    @RequestMapping(path = "/creacion", method = RequestMethod.POST )
    public ModelAndView crearViaje(@ModelAttribute("viaje") Viaje viaje, HttpSession session) {
        ModelMap model = new ModelMap();
       try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            viaje.setUsuario(usuario);

            this.servicioViaje.crearViaje(viaje);
       }
       catch (Exception e){
           model.put("error", "Error al registrar el viaje");
           return new ModelAndView("crear-viaje", model);
       }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/ver-viaje", method = RequestMethod.GET )
    public ModelAndView masInfo(@RequestParam(required = false) String id, ModelAndView mv) {

        Viaje viajeBuscado = servicioViaje.obtenerViajePorId(Long.valueOf(id));
        mv.addObject("viaje", viajeBuscado);
        mv.setViewName("viaje/viaje");
        return mv;
    }

    @RequestMapping(path = "/mis-viajes", method = RequestMethod.GET )
    public ModelAndView verMisViajes(HttpSession session) {
        if(session.getAttribute("usuario") != null){
            ModelMap model = new ModelMap();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            List<Viaje> viajes = servicioViaje.obtenerViajesCreadosPorUnUsuario(usuario);
            model.put("viajesCreados", viajes);
            return new ModelAndView("perfil/mis-viajes", model);
        }else{
            return new ModelAndView("redirect:/login");
        }
    }

}
