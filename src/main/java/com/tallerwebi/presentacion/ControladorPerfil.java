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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Transactional
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
        Usuario stalker = (Usuario) session.getAttribute("usuario");
        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(idUsuario);

        //Obtener viajes a los que se unió el usuario
        Set<Viaje> viajesUnidos = servicioViaje.obtenerViajesDePasajero(usuarioBuscado);
        if(viajesUnidos == null){
            viajesUnidos = new HashSet<>();
        }

        //Obtener viajes creados por el usuario
        Set<Viaje> viajesCreados = new HashSet<>(servicioViaje.obtenerViajesCreadosPorUnUsuario(usuarioBuscado));
        if (viajesCreados == null) {
            viajesCreados = new HashSet<>();
        }
        int cantidadViajesCreados = viajesCreados.size();
        int cantidadViajesUnidos = viajesUnidos.size();

        modelo.put("session", session);
        modelo.put("usuario",usuarioBuscado);
        modelo.put("stalker",stalker);
        modelo.put("viajesCreados", viajesCreados);
        modelo.put("gasto", new Gasto());
        modelo.put("cantidadViajesCreados", cantidadViajesCreados);
        modelo.put("cantidadViajesUnidos", cantidadViajesUnidos);
        return new ModelAndView("perfil",modelo);
    }

    @RequestMapping(path = "/mi-perfil", method = RequestMethod.GET )
    public ModelAndView verMiPerfil(HttpSession session) {
        try{
            if(session.getAttribute("usuario") != null){
                ModelMap model = new ModelMap();
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                //Obtener viajes a los que se unió el usuario
                Set<Viaje> viajesUnidos = servicioViaje.obtenerViajesDePasajero(usuario);
                if(viajesUnidos == null){
                    viajesUnidos = new HashSet<>();
                }

                //Obtener viajes creados por el usuario
                Set<Viaje> viajesCreados = new HashSet<>(servicioViaje.obtenerViajesCreadosPorUnUsuario(usuario));
                if (viajesCreados == null) {
                    viajesCreados = new HashSet<>();
                }
                int cantidadViajesCreados = viajesCreados.size();
                int cantidadViajesUnidos = viajesUnidos.size();

                model.put("usuario", usuario);
                model.put("viajesUnidos", viajesUnidos);
                model.put("viajesCreados", viajesCreados);
                model.put("gasto", new Gasto());
                model.put("cantidadViajesCreados", cantidadViajesCreados);
                model.put("cantidadViajesUnidos", cantidadViajesUnidos);
                return new ModelAndView("perfil", model);
            }else{
                return new ModelAndView("redirect:/login");
            }
        }catch(Exception e){
            ModelMap model = new ModelMap();
            model.put("mensaje", e.getMessage());
            return new ModelAndView("error/error",model);
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
