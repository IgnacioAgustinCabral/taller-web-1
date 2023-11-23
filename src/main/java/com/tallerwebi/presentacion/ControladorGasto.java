/*
package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Gasto;
import com.tallerwebi.dominio.ServicioGasto;
import com.tallerwebi.dominio.ServicioViaje;
import com.tallerwebi.dominio.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorGasto {
    private final ServicioGasto servicioGasto;
    private final ServicioViaje servicioViaje;


    @Autowired
    public ControladorGasto(ServicioGasto servicioGasto, ServicioViaje servicioViaje){

        this.servicioGasto = servicioGasto;
        this.servicioViaje = servicioViaje;
    }

    @RequestMapping(path = "/agregar-gasto", method = RequestMethod.GET)
    public ModelAndView mostrarModalCrearViaje(HttpServletRequest request){

        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("isLogged") != null) {
            ModelMap modelo = new ModelMap();
            modelo.put("gasto", new Gasto());
            return new ModelAndView("/perfil/perfil", modelo);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(path="/agregar-gasto", method = RequestMethod.POST)
    public ModelAndView agregarGasto(@ModelAttribute Gasto gasto, Long idViaje){
        ModelMap model = new ModelMap();
        try {
            //Usuario usuario = (Usuario) session.getAttribute("usuario");
            //viaje.setUsuario(usuario);
            Viaje viaje = servicioViaje.obtenerViajePorId(idViaje);
            gasto.setViaje(viaje);
            this.servicioGasto.guardarGasto(gasto);
            model.put("exito", true);
        }
        catch (Exception e){
            model.put("error", "Error al registrar el viaje");
            return new ModelAndView("/perfil/perfil", model);
        }
        return new ModelAndView("redirect:/perfil/perfil");
    }



}




*/
