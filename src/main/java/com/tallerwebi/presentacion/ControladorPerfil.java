package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Transactional
public class ControladorPerfil {

    private ServicioUsuario servicioUsuario;
    private ServicioViaje servicioViaje;
    private ServicioComentario servicioComentario;


    @Autowired
    public ControladorPerfil(ServicioViaje servicioViaje,ServicioUsuario servicioUsuario, ServicioComentario servicioComentario){
        this.servicioViaje = servicioViaje;
        this.servicioUsuario = servicioUsuario;
        this.servicioComentario = servicioComentario;
    }

    @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView irAPerfil(@RequestParam(required = false) Long idUsuario, HttpServletRequest request){
        ModelMap modelo = new ModelMap();
        HttpSession session = request.getSession();

        //TODO: completar con try catch - manejar excepciones - revisar porque trae un mv
        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(idUsuario);
        List<Comentario> comentarios = servicioComentario.obtenerComentariosPorUsuario(usuarioBuscado);

        List<Viaje> viajes = servicioViaje.obtenerViajesCreadosPorUnUsuario(usuarioBuscado);
        modelo.put("session", session);
        modelo.put("usuario",usuarioBuscado);
        modelo.put("viajes",viajes);
        modelo.put("comentarios", comentarios);
        return new ModelAndView("perfil",modelo);
    }

    @RequestMapping(path = "/mi-perfil", method = RequestMethod.GET )
    public ModelAndView verMiPerfil(HttpSession session) {
        try{
            if(session.getAttribute("usuario") != null){
                ModelMap model = new ModelMap();
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                List<Viaje> viajes = servicioViaje.obtenerViajesCreadosPorUnUsuario(usuario);
                List<Comentario> comentarios =servicioComentario.obtenerComentariosPorUsuario(usuario);
                //Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId((Long) session.getAttribute("id"));
                model.put("usuario", usuario);
                model.put("viajes", viajes);
                model.put("comentarios", comentarios);
                return new ModelAndView("perfil", model);
            }else{
                return new ModelAndView("redirect:/login");
            }
        }catch(Exception e){
            ModelMap model = new ModelMap();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            List<Viaje> viajes = servicioViaje.obtenerViajesCreadosPorUnUsuario(usuario);
            //Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId((Long) session.getAttribute("id"));
            model.put("usuario", usuario);
            model.put("viajes", viajes);
            return new ModelAndView("perfil/perfil", model);
        }/*else{
            return new ModelAndView("redirect:/login");
            model.put("mensaje", e.getMessage());
            return new ModelAndView("error/error",model);
        }*/
    }

/*  @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView encontrarUsuarioPorId(@RequestParam(required = false) Long id){

        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(id);
        ModelMap model = new ModelMap();
        model.put("usuario", usuarioBuscado);
        return new ModelAndView("perfil", model);

    }*/

}
