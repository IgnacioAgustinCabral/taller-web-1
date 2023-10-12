package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioViaje;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorViaje {

    private  ServicioViaje servicioViaje;

    @Autowired
    public ControladorViaje(ServicioViaje servicioViaje){
        this.servicioViaje = servicioViaje;
    }

    @RequestMapping(value = "/crear-viaje", method = RequestMethod.GET)
    public ModelAndView mostrarVistaCrearViaje() {
        ModelMap modelo = new ModelMap();
        modelo.put("viaje", new Viaje());
        return new ModelAndView("crear-viaje",modelo);
    }

    @RequestMapping(path = "/creacion", method = RequestMethod.POST )
    public ModelAndView crearViaje(@ModelAttribute("viaje") Viaje viaje, HttpSession session) {
        ModelMap model = new ModelMap();
       try {
            //Usuario usuario = (Usuario) session.getAttribute("usuario");
            //viaje.setUsuario(usuario);

            this.servicioViaje.crearViaje(viaje);
       }
       catch (Exception e){
           model.put("error", "Error al registrar el viaje");
           return new ModelAndView("crear-viaje", model);
       }
        return new ModelAndView("redirect:/home");
    }
}
