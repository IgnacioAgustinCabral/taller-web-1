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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ControladorViaje {

    private ServicioViaje servicioViaje;
    private ServicioCiudad servicioCiudad;
    private ServicioGasto servicioGasto;

    @Autowired
    public ControladorViaje(ServicioViaje servicioViaje, ServicioCiudad servicioCiudad, ServicioGasto servicioGasto) {

        this.servicioViaje = servicioViaje;
        this.servicioCiudad = servicioCiudad;
        this.servicioGasto = servicioGasto;
    }

    @RequestMapping(value = "/crear-viaje", method = RequestMethod.GET)
    public ModelAndView mostrarVistaCrearViaje(
            @RequestParam(value = "viaje", required = false) Long viajeId,
            HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("isLogged") != null) {
            ModelMap modelo = cargarOrigenYDestinoAlModel();
            modelo.put("gasto", new Gasto());
            return new ModelAndView("crear-viaje", modelo);
        } else {
            return new ModelAndView("redirect:/login");

        // Caso 1: Usuario no registrado
        if (session == null || session.getAttribute("isLogged") == null) {
            return new ModelAndView("redirect:/home");
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Caso 2: Usuario logueado pero email no verificado
        if (!usuario.isEmailValidado()) {
            ModelMap model = new ModelMap();
            model.put("errorCrearViaje", "¡Debes validar tu correo electrónico para crear un viaje!");
            return new ModelAndView("notificacion", model);
        }

        // Caso 3: Usuario logueado y email verificado
        ModelMap modelo = new ModelMap();

        if (viajeId != null) {
            modelo.put("edito", true);
            Viaje viajeAModificar = servicioViaje.obtenerViajePorId(viajeId);
            List<Ciudad> ciudades = servicioCiudad.obtenerListaDeCiudades();
            modelo.put("viaje", viajeAModificar);
            modelo.put("ciudades", ciudades);
        } else {
            modelo = cargarOrigenYDestinoAlModel();
            modelo.put("edito", false);
        }

        return new ModelAndView("crear-viaje", modelo);
    }


    /*@RequestMapping(path = "/creacion", method = RequestMethod.POST)
    public ModelAndView crearViaje(@ModelAttribute("viaje") Viaje viaje, HttpSession session) {
        ModelMap model = new ModelMap();
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaViaje = null;

        try {
            fechaViaje = LocalDate.parse(viaje.getFecha().toString());

        } catch (DateTimeParseException ex) {
            model = cargarOrigenYDestinoAlModel();
            model.put("error", "El formato de fecha es inválido");
            ex.printStackTrace();
            return new ModelAndView("crear-viaje", model);
        }

        if (fechaViaje.isBefore(fechaHoy)) {
            model = cargarOrigenYDestinoAlModel();
            model.put("error", "La fecha del viaje no es válida o es una fecha pasada");
            return new ModelAndView("crear-viaje", model);
        }
        if (viaje.getNoFumar() == null) {
            model = cargarOrigenYDestinoAlModel();
            model.put("error", "Debe especificar si se puede fumar");
            return new ModelAndView("crear-viaje", model);
        }
        if (viaje.getNoNinios() == null) {
            model = cargarOrigenYDestinoAlModel();
            model.put("error", "Debe especificar si se puede viajar con niños");
            return new ModelAndView("crear-viaje", model);
        }
        if (viaje.getNoMascotas() == null) {
            model = cargarOrigenYDestinoAlModel();
            model.put("error", "Debe especificar si se puede viajar con mascotas");
            return new ModelAndView("crear-viaje", model);
        }
        if (viaje.getCantidad() == null || viaje.getCantidad() <= 0) {
            model = cargarOrigenYDestinoAlModel();
            model.put("error", "La cantidad de pasajeros debe ser al menos 1");
            return new ModelAndView("crear-viaje", model);
        }
        if (viaje.getDescripcion() == null || viaje.getDescripcion().isEmpty()) {
            model = cargarOrigenYDestinoAlModel();
            model.put("error", "La descripción no puede estar vacía");
            return new ModelAndView("crear-viaje", model);
        }

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            viaje.setUsuario(usuario);
            this.servicioViaje.crearViaje(viaje);
            model.put("idViaje", viaje.getId());
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", "Error al registrar el viaje, revise los campos");
            return new ModelAndView("crear-viaje", model);
        }
        catch (Exception e) {
            model = cargarOrigenYDestinoAlModel();
            model.put("edito", false);
            model.put("error", e.getMessage());
            return new ModelAndView("crear-viaje", model);
        }
        return new ModelAndView("redirect:/home");
    }*/

    @RequestMapping(path = "/editar", method = RequestMethod.POST)
    public ModelAndView editarViaje(@ModelAttribute("viaje") Viaje viaje, HttpSession session) {
        ModelMap model = new ModelMap();

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            this.servicioViaje.ModificarViaje(viaje,usuario);
        } catch (Exception e) {
            model = cargarOrigenYDestinoAlModel();
            model.put("viaje", viaje);
            model.put("edito", true);
            model.put("error", e.getMessage());
            return new ModelAndView("crear-viaje", model);
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/creacion", method = RequestMethod.POST)
    public ModelAndView crearViaje(@ModelAttribute("viaje") Viaje viaje, HttpSession session) {
        ModelMap model = new ModelMap();

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            viaje.setUsuario(usuario);
            this.servicioViaje.crearViaje(viaje);
        } catch(NullPointerException e){
            model = cargarOrigenYDestinoAlModel();
            model.put("edito", false);
            model.put("error", "Error al registrar el viaje, revise los campos");
            return new ModelAndView("crear-viaje", model);
        }
        catch (Exception e) {
            model = cargarOrigenYDestinoAlModel();
            model.put("edito", false);
            model.put("error", e.getMessage());
            return new ModelAndView("crear-viaje", model);
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/ver-viaje", method = RequestMethod.GET)
    public ModelAndView masInfo(@RequestParam(required = false) Long id, HttpSession session) {
        try {
            ModelMap model = new ModelMap();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null)
                throw new Exception();

            Viaje viajeBuscado = servicioViaje.obtenerViajePorId(id);

            String coordenadaOrigen = viajeBuscado.getOrigen().getLatitud().toString() + ',' + viajeBuscado.getOrigen().getLongitud().toString();
            String coordenadaDestino = viajeBuscado.getDestino().getLatitud().toString() + ',' + viajeBuscado.getDestino().getLongitud().toString();

            model.put("viajes", viajeBuscado);
            model.put("coordenadaOrigen", coordenadaOrigen);
            model.put("coordenadaDestino", coordenadaDestino);

            Boolean unido = servicioViaje.UsuarioUnido(viajeBuscado, usuario);
            model.put("viaje", viajeBuscado);
            model.put("unido", unido);
            return new ModelAndView("viaje/viaje", model);

        } catch (Exception e) {
            ModelMap model = new ModelMap();
            model.put("mensaje","Debes estar registrado para poder acceder.");
            return new ModelAndView("error/error",model);
        }
    }

    @RequestMapping(path = "/listar-provincia", method = GET)
    public ModelAndView listarPorProvincia(@RequestParam String provincia) {
        ModelMap modelo = new ModelMap();
        List<Viaje> listadoDeViaje = servicioViaje.obtenerViajesPorProvincia(provincia);

        if (listadoDeViaje != null) {
            modelo.put("viajes", listadoDeViaje);
        }else {
            modelo.put("mensaje", "No hay viajes disponibles para la provincia seleccionada");
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
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            Boolean unido = servicioViaje.UnirAViaje(usuario, viaje);
        } catch (Exception e) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path="/agregar", method = RequestMethod.POST)
    public ModelAndView agregarGasto(@ModelAttribute Gasto gasto, @RequestParam Long idViaje){
        ModelMap model = new ModelMap();
        try {
            Viaje viaje = servicioViaje.obtenerViajePorId(idViaje);
            gasto.setViaje(viaje);
            this.servicioGasto.guardarGasto(gasto);
            model.put("exito", "Gasto Agregado exitosamente");
        }
        catch (Exception e){
            model.put("viaje", new Viaje());
            model.put("error", "Error al registrar el gasto");
            return new ModelAndView("crear-viaje", model);
        }
        return new ModelAndView("redirect:crear-viaje");
    }

    @RequestMapping(value = "/mostrar-gastos", method = RequestMethod.GET)
    public ModelAndView mostrarGastos(@RequestParam("idViaje") Long idViaje) {
        ModelMap model = new ModelMap();
        try {
            Viaje viaje = servicioViaje.obtenerViajePorId(idViaje);
            List<Gasto> gastos = servicioGasto.obtenerGastosPorViaje(viaje);
            model.put("viaje", viaje);
            model.put("gastos", gastos);
        } catch (Exception e) {
            model.put("error", "Error al obtener los gastos del viaje");
        }
        return new ModelAndView("mostrar-gastos", model);
    }
    @RequestMapping(value = "/modificar-viaje", method = RequestMethod.GET)
    public ModelAndView ModificarViaje(@ModelAttribute("viaje") Viaje viaje, @RequestParam("viaje") Long id, HttpSession session) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            Boolean modificado = servicioViaje.ModificarViaje(usuario,viaje, id);
        } catch (Exception e) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("redirect:/home");
    }

    private ModelMap cargarOrigenYDestinoAlModel() {
        List<Ciudad> ciudades = servicioCiudad.obtenerListaDeCiudades();
        ModelMap modelo = new ModelMap();
        modelo.put("viaje", new Viaje());
        modelo.put("ciudades", ciudades);
        return modelo;
    }

}
