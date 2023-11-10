package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Transactional
public class ControladorViaje {

    private  ServicioViaje servicioViaje;
    private ServicioCiudad servicioCiudad;

    @Autowired
    public ControladorViaje(ServicioViaje servicioViaje, ServicioCiudad servicioCiudad){

        this.servicioViaje = servicioViaje;
        this.servicioCiudad = servicioCiudad;
    }

    @RequestMapping(value = "/crear-viaje", method = RequestMethod.GET)
    public ModelAndView mostrarVistaCrearViaje(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("isLogged") != null) {
            List<Ciudad> ciudades = servicioCiudad.obtenerListaDeCiudades();
            ModelMap modelo = new ModelMap();
            modelo.put("viaje", new Viaje());
            modelo.put("ciudades", ciudades);
            return new ModelAndView("crear-viaje", modelo);
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(path = "/creacion", method = RequestMethod.POST )
    public ModelAndView crearViaje(@ModelAttribute("viaje") Viaje viaje, HttpSession session) {
        ModelMap model = new ModelMap();
       try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            viaje.setUsuario(usuario);

            this.servicioViaje.crearViaje(viaje);
       }
       catch (Exception e){
           model.put("error", "Error al registrar el viaje");
           return new ModelAndView("crear-viaje", model);
       }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/ver-viaje", method = RequestMethod.GET )
    public ModelAndView masInfo(@RequestParam(required = false) String id,HttpSession session) {
        ModelMap model = new ModelMap();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        Viaje viajeBuscado = servicioViaje.obtenerViajePorId(Long.valueOf(id));

        String coordenadaOrigen = viajeBuscado.getOrigen().getLatitud().toString() +','+ viajeBuscado.getOrigen().getLongitud().toString();
        String coordenadaDestino = viajeBuscado.getDestino().getLatitud().toString() +','+viajeBuscado.getDestino().getLongitud().toString();
        String claveApi = servicioViaje.obtenerClaveApiGoogleMaps();

        model.put("viajes", viajeBuscado);
        model.put("coordenadaOrigen",coordenadaOrigen);
        model.put("coordenadaDestino",coordenadaDestino);
        model.put("claveApi",claveApi);


        Boolean unido = servicioViaje.UsuarioUnido(viajeBuscado,usuario);
        System.out.println("el usuario esta unido?? " + unido + "/////////////////////////////////");
        model.put("viaje", viajeBuscado);
        model.put("unido", unido);
        return new ModelAndView("viaje/viaje", model);

    }

/*    @RequestMapping(path = "/ver-viaje", method = RequestMethod.GET )
    public ModelAndView masInfo(@RequestParam(required = false) String id, ModelAndView mv) {

        Viaje viajeBuscado = servicioViaje.obtenerViajePorId(Long.valueOf(id));
        mv.addObject("viaje", viajeBuscado);
        mv.setViewName("viaje/viaje");
        return mv;
    }*/


    @RequestMapping(path = "/listar-provincia", method = GET)
    public ModelAndView listarPorProvincia(@RequestParam String provincia) {
        ModelMap modelo = new ModelMap();
        List<Viaje> listadoDeViaje = servicioViaje.obtenerViajesPorProvincia(provincia);

        if (listadoDeViaje != null) {
            try {
                modelo.put("viajes", listadoDeViaje);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return new ModelAndView("provinciaDetalle", modelo);
    }

    @RequestMapping(value = "/unir-a-viaje", method = RequestMethod.GET)
    public ModelAndView unirseAUnViaje(@RequestParam("viaje") Long viaje, HttpSession session) {
        try {
            System.out.println("VIAJEEEEE///////////////////////" + viaje);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            Boolean unido = servicioViaje.UnirAViaje(usuario, viaje);
        } catch (Exception e) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("redirect:/home");
    }
}
