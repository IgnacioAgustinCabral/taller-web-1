package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.infraestructura.RepositorioViajeImpl;
import com.tallerwebi.infraestructura.ViajeDisplayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @RequestMapping(path = "/home")
    public ModelAndView irAHome() {

        List<Viaje> datos = servicioViaje.obtenerViajes();
        List<Provincia> provincia = servicioProvincia.obtenerProvinciasConImagenes();

        ModelMap model = new ModelMap();

        model.put("ultimosViajes", datos);
        model.put("provincias", provincia);

        return new ModelAndView("home", model);
    }

}
