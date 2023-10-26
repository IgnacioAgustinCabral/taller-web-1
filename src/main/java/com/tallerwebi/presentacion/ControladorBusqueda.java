package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
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




    }

