package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorHome {

    private ServicioProvincia servicioProvincia;
    private ServicioViaje servicioViaje;
    private ServicioCiudad servicioCiudad;

    @Autowired
    public ControladorHome(ServicioViaje servicioViaje , ServicioProvincia servicioProvincia, ServicioCiudad servicioCiudad) {
        this.servicioViaje = servicioViaje;
        this.servicioProvincia = servicioProvincia;
        this.servicioCiudad = servicioCiudad;
    }


    @RequestMapping(path = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView irAHome(HttpServletRequest request) {

        FiltroViaje viajeBuscado = new FiltroViaje();
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Viaje> datos = new ArrayList<>();

        if(usuario != null)
            datos = servicioViaje.obtenerViajes().stream()
                .filter(elemento -> !elemento.getUsuario().getEmail().equals(usuario.getEmail()))
                .collect(Collectors.toList());
        else
            datos = servicioViaje.obtenerViajes();

        List<Provincia> provincia = servicioProvincia.obtenerProvinciasConImagenes();
        List<Ciudad> ciudades = servicioCiudad.obtenerListaDeCiudades();
        ModelMap model = new ModelMap();

        model.put("session", session);
        model.put("ultimosViajes", datos);
        model.put("provincias", provincia);
        model.put("listaCiudades", ciudades);
        model.put("viajeBuscado", viajeBuscado);

        return new ModelAndView("home", model);
    }

    @RequestMapping("/quienes-somos")
    public ModelAndView irANosotros() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("quienes-somos", modelo);
    }
}
