package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.NullEmailValidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Transactional
public class ControladorViaje {

    private ServicioViaje servicioViaje;
    private ServicioUsuario servicioUsuario;
    private ServicioCiudad servicioCiudad;
    private ServicioGasto servicioGasto;

    @Autowired
    public ControladorViaje(ServicioGasto servicioGasto, ServicioUsuario servicioUsuario, ServicioViaje servicioViaje, ServicioCiudad servicioCiudad) {

        this.servicioUsuario = servicioUsuario;
        this.servicioViaje = servicioViaje;
        this.servicioCiudad = servicioCiudad;
        this.servicioGasto = servicioGasto;
    }

    @RequestMapping(value = "/crear-viaje", method = RequestMethod.GET)
    public ModelAndView mostrarVistaCrearViaje(@RequestParam(value = "viaje", required = false) Long viajeId, HttpSession session) {
        try{
            ModelMap modelo = new ModelMap();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            this.servicioUsuario.validarEmailUsuario(usuario);

            // Caso 1: Usuario no registrado
            if (session == null || session.getAttribute("isLogged") == null) {
                return new ModelAndView("redirect:/home");
            }

            // Caso 2: Usuario logueado pero email no verificado
            if (!usuario.isEmailValidado()) {
                System.out.println(usuario.isEmailValidado()+ "email validado?");
                ModelMap model = new ModelMap();
                model.put("errorCrearViaje", "¡Debes validar tu correo electrónico para crear un viaje!");
                return new ModelAndView("notificacion", model);
            }

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
        }catch(NullEmailValidoException e){

            ModelMap modelo = new ModelMap();
            modelo.put("mensaje", e.getMessage());
            return new ModelAndView("error/error", modelo);
        }
        catch(Exception e){
            ModelMap modelo = new ModelMap();
            modelo.put("mensaje", e.getMessage());
            return new ModelAndView("error/error",modelo);
        }
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
    @RequestMapping(path = "/eliminar/{viajeId}", method = RequestMethod.GET)
    public ModelAndView eliminarViaje(@PathVariable Long viajeId, HttpSession session) {
        ModelMap model = new ModelMap();

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            this.servicioViaje.eliminarViaje(viajeId, usuario);
        } catch (Exception e) {
            model.put("mensaje", "Error al eliminar el viaje: " + e.getMessage());
            return new ModelAndView("error/error", model);
        }


        return new ModelAndView("redirect:/mi-perfil");
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
            return new ModelAndView("viaje", model);

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
            model.put("viaje", new Viaje());
            model.put("error", "Error al registrar el gasto");
            return new ModelAndView("crear-viaje", model);
        }
        return new ModelAndView("redirect:crear-viaje");
    }

    @RequestMapping(value = "/mostrar-gastos", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> mostrarGastos(@RequestParam("idViaje") Long idViaje) {
        try {
            Viaje viaje = servicioViaje.obtenerViajePorId(idViaje);
            List<Gasto> gastos = servicioGasto.obtenerGastosPorViaje(viaje);
            Integer usuariosUnidos = viaje.getListaPasajeros().size() + 1;

            // Calcular la suma total de los montos de los gastos
            double montoTotal = gastos.stream().mapToDouble(Gasto::getMonto).sum();

            // Crear un mapa con la estructura deseada
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("gastos", gastos);
            respuesta.put("montoTotal", montoTotal);
            respuesta.put("cantidadUnidos", usuariosUnidos);

            return ResponseEntity.ok().body(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error al obtener los gastos del viaje"));
        }
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

    @RequestMapping(path = "/mis-viajes", method = RequestMethod.GET )
    public ModelAndView verMisViajes(HttpSession session) {
        try{
            if(session.getAttribute("usuario") != null){
                ModelMap model = new ModelMap();
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                Set<Viaje> viajes = servicioViaje.obtenerViajesDePasajero(usuario);

                if(viajes == null)
                    viajes = new HashSet<>();
                //Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId((Long) session.getAttribute("id"));
                model.put("usuario", usuario);
                model.put("viajes", viajes);
                return new ModelAndView("misviajes", model);
            }else{
                return new ModelAndView("redirect:/login");
            }
        }catch(Exception e){
            ModelMap model = new ModelMap();
            model.put("mensaje", e.getMessage());
            return new ModelAndView("error/error",model);
        }
    }

}
