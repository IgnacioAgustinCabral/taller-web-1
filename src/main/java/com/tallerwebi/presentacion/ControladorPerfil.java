package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/perfil")
public class ControladorPerfil {

    private ServicioUsuario servicioUsuario;
    private ServicioViaje servicioViaje;


    @Autowired
    public ControladorPerfil(ServicioViaje servicioViaje,ServicioUsuario servicioUsuario){
        this.servicioViaje = servicioViaje;
        this.servicioUsuario = servicioUsuario;}
    @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView irAPerfil(@RequestParam(required = false) Long idUsuario){
        ModelMap modelo = new ModelMap();

        //TODO: completar con try catch - manejar excepciones - revisar porque trae un mv
        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(idUsuario);

        List<Viaje> viajes = servicioViaje.obtenerViajesCreadosPorUnUsuario(usuarioBuscado);
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
            return new ModelAndView("perfil/perfil", model);
        }else{
            return new ModelAndView("redirect:/login");
        }
    }

/*  @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView encontrarUsuarioPorId(@RequestParam(required = false) Long id){

        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(id);
        ModelMap model = new ModelMap();
        model.put("usuario", usuarioBuscado);
        return new ModelAndView("perfil/perfil", model);

    }*/

}
