package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/perfil")
public class ControladorPerfil {

    private ServicioUsuario servicioUsuario;
    private ServicioViaje servicioViaje;
    private ServicioGasto servicioGasto;


    @Autowired
    public ControladorPerfil(ServicioViaje servicioViaje,ServicioUsuario servicioUsuario, ServicioGasto servicioGasto){
        this.servicioViaje = servicioViaje;
        this.servicioUsuario = servicioUsuario;
        this.servicioGasto = servicioGasto;
    }

    @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView irAPerfil(@RequestParam(required = false) Long idUsuario, HttpServletRequest request){
        ModelMap modelo = new ModelMap();
        HttpSession session = request.getSession();

        //TODO: completar con try catch - manejar excepciones - revisar porque trae un mv
        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(idUsuario);

        List<Viaje> viajes = servicioViaje.obtenerViajesCreadosPorUnUsuario(usuarioBuscado);
        modelo.put("session", session);
        modelo.put("usuario",usuarioBuscado);
        modelo.put("viajes",viajes);
        return new ModelAndView("perfil/perfil",modelo);
    }

    @RequestMapping(path = "/mi-perfil", method = RequestMethod.GET )
    public ModelAndView verMisViajes(HttpSession session) {
        if(session.getAttribute("usuario") != null){
            ModelMap model = new ModelMap();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            List<Viaje> viajes = servicioViaje.obtenerViajesCreadosPorUnUsuario(usuario);
            //Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId((Long) session.getAttribute("id"));
            model.put("usuario", usuario);
            model.put("viajes", viajes);
            model.put("gasto", new Gasto());
            return new ModelAndView("perfil/perfil", model);
        }else{
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value="/agregar-gasto", method = RequestMethod.POST)
    public ModelAndView agregarGasto(@ModelAttribute("gasto")Gasto gasto, @RequestParam Long idViaje){
        ModelMap model = new ModelMap();
        try {

            Viaje viaje = servicioViaje.obtenerViajePorId(idViaje);
            gasto.setViaje(viaje);
            this.servicioGasto.guardarGasto(gasto);
            model.put("exito", true);
        }
        catch (Exception e){
            model.put("error", "Error al registrar el viaje");
            return new ModelAndView("perfil/perfil", model);
        }
        return new ModelAndView("redirect:perfil/perfil");
    }


/*  @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView encontrarUsuarioPorId(@RequestParam(required = false) Long id){

        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(id);
        ModelMap model = new ModelMap();
        model.put("usuario", usuarioBuscado);
        return new ModelAndView("perfil/perfil", model);

    }*/

}
