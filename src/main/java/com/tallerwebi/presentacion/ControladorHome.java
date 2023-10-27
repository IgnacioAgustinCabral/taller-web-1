package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorHome {

    private ServicioProvincia servicioProvincia;
    private ServicioViaje servicioViaje;
    private ServicioUsuario servicioUsuario;
    private ServicioCiudad servicioCiudad;

    @Autowired
    public ControladorHome(ServicioViaje servicioViaje , ServicioProvincia servicioProvincia, ServicioUsuario servicioUsuario, ServicioCiudad servicioCiudad) {
        this.servicioViaje = servicioViaje;
        this.servicioProvincia = servicioProvincia;
        this.servicioUsuario = servicioUsuario;
        this.servicioCiudad = servicioCiudad;
    }


    @Transactional
    @RequestMapping(path = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView irAHome(HttpServletRequest request) {

        FiltroViaje viajeBuscado = new FiltroViaje();
        HttpSession session = request.getSession();
        List<Viaje> datos = servicioViaje.obtenerViajes();
        List<Provincia> provincia = servicioProvincia.obtenerProvinciasConImagenes();
        List<Ciudad> ciudades = servicioCiudad.obtenerListaDeCiudades();

        ModelMap model = new ModelMap();

        model.put("session", session);
        model.put("ultimosViajes", datos);
        model.put("provincias", provincia);
        model.put("ciudades", ciudades);
        model.put("viajeBuscado", viajeBuscado);

        return new ModelAndView("home", model);
    }
}
