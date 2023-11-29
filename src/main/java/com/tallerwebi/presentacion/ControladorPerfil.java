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
public class ControladorPerfil {

    private ServicioGasto servicioGasto;
    private ServicioUsuario servicioUsuario;
    private ServicioViaje servicioViaje;
    private ServicioComentario servicioComentario;


    @Autowired
    public ControladorPerfil(ServicioViaje servicioViaje,ServicioUsuario servicioUsuario, ServicioGasto servicioGasto, ServicioComentario servicioComentario){
        this.servicioViaje = servicioViaje;
        this.servicioUsuario = servicioUsuario;
        this.servicioGasto = servicioGasto;
        this.servicioComentario = servicioComentario;
    }

    @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView irAPerfil(@RequestParam(required = false) Long idUsuario, HttpServletRequest request){
        ModelMap modelo = new ModelMap();
        HttpSession session = request.getSession();
        Usuario stalker = (Usuario) session.getAttribute("usuario");
        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(idUsuario);
        List<Comentario> comentarios = servicioComentario.obtenerComentariosPorUsuario(usuarioBuscado);

        List<Viaje> viajes = servicioViaje.obtenerViajesCreadosPorUnUsuario(usuarioBuscado);
        modelo.put("session", session);
        modelo.put("usuario",usuarioBuscado);
        modelo.put("stalker",stalker);
        modelo.put("viajes",viajes);
        modelo.put("comentarios", comentarios);
        modelo.put("comentario", new Comentario());
        modelo.put("gasto", new Gasto());
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
                model.put("gasto", new Gasto());
                model.put("comentario", new Comentario());
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
            return new ModelAndView("perfil", model);
        }/*else{
            return new ModelAndView("redirect:/login");
            model.put("mensaje", e.getMessage());
            return new ModelAndView("error/error",model);
        }*/
    }

    @RequestMapping(value="/comentario", method = RequestMethod.POST )
    public ModelAndView nuevoComentario(@ModelAttribute("comentario") Comentario comentario, @RequestParam(required = false) Long idUsuario, HttpSession session){

        ModelMap modelo = new ModelMap();

        try {
            if (session == null || session.getAttribute("isLogged") == null) {
                return new ModelAndView("redirect:/home");
            }

            Usuario usuarioOrigen = (Usuario) session.getAttribute("usuario");
            comentario.setUsuarioOrigen(usuarioOrigen);

            if (idUsuario != null) {
                Usuario usuarioDestino = servicioUsuario.obtenerUsuarioPorId(idUsuario);
                comentario.setUsuarioDestino(usuarioDestino);
            }
            Usuario usuarioDestino = servicioUsuario.obtenerUsuarioPorId(idUsuario);
            comentario.setUsuarioDestino(usuarioDestino);

            servicioComentario.crearComentario(comentario);

            return new ModelAndView("redirect:/usuario?idUsuario="+usuarioDestino.getId());
        } catch (Exception e) {
            modelo.put("mensaje", e.getMessage());
            return new ModelAndView("error/error", modelo);
        }
    }

    @RequestMapping(path="/agregar-gasto", method = RequestMethod.POST)
    public ModelAndView agregarGasto(@ModelAttribute Gasto gasto, @RequestParam Long idViaje){
        ModelMap model = new ModelMap();
        try {
            Viaje viaje = servicioViaje.obtenerViajePorId(idViaje);
            gasto.setViaje(viaje);
            this.servicioGasto.guardarGasto(gasto);
            model.put("exito", "Gasto Agregado exitosamente");
        }
        catch (Exception e){
            model.put("error", "Error al registrar el gasto");
            return new ModelAndView("perfil", model);
        }
        return new ModelAndView("redirect:mi-perfil");
    }


/*  @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView encontrarUsuarioPorId(@RequestParam(required = false) Long id){

        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(id);
        ModelMap model = new ModelMap();
        model.put("usuario", usuarioBuscado);
        return new ModelAndView("perfil", model);

    }*/
}
