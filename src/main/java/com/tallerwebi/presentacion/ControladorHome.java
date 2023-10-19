package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
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

    @Autowired
    public ControladorHome(ServicioViaje servicioViaje , ServicioProvincia servicioProvincia, ServicioUsuario servicioUsuario) {
        this.servicioViaje = servicioViaje;
        this.servicioProvincia = servicioProvincia;
        this.servicioUsuario = servicioUsuario;
    }


    @Transactional
    @RequestMapping(path = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView irAHome(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<Viaje> datos = servicioViaje.obtenerViajes();
        List<Provincia> provincia = servicioProvincia.obtenerProvinciasConImagenes();

        ModelMap model = new ModelMap();

        model.put("session", session);
        model.put("ultimosViajes", datos);
        model.put("provincias", provincia);


        return new ModelAndView("home", model);
    }




}
