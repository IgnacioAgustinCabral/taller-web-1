package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.TokenInvalidoException;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class ControladorLogin {

    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @RequestMapping("/login")
    public ModelAndView irALogin() {
        ModelMap modelo = new ModelMap();
        modelo.put("datosLogin", new DatosLogin());
        return new ModelAndView("login2", modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
        if (usuarioBuscado != null) {

            request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioBuscado);
            session.setAttribute("isLogged", true);
            session.setAttribute("emailVerificado", usuarioBuscado.isEmailValidado());
            return new ModelAndView("redirect:/home");
        } else {
            model.put("error", "Usuario o clave incorrecta");
        }
        return new ModelAndView("login2", model);
    }

    @RequestMapping(path = "/registrarme", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ModelAndView registrarme(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, @RequestPart("imagenDePerfil") MultipartFile imagenDePerfil) {
        ModelMap model = new ModelMap();

        if (result.hasErrors()) {
            // Hay errores de validación, maneja según tu lógica
            model.addAttribute("error", "Error en el formulario");
            return new ModelAndView("nuevo-usuario", model);
        }

        try {
            servicioLogin.registrar(usuario, imagenDePerfil);
        } catch (UsuarioExistente e) {
            model.put("error", "El usuario ya existe");
            return new ModelAndView("nuevo-usuario", model);
        } catch (Exception e) {
            model.put("error", "Error al registrar el nuevo usuario");
            return new ModelAndView("nuevo-usuario", model);
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(path = "/nuevo-usuario", method = RequestMethod.GET)
    public ModelAndView nuevoUsuario() {
        ModelMap model = new ModelMap();
        model.put("usuario", new Usuario());
        return new ModelAndView("nuevo-usuario", model);
    }

    @RequestMapping("/validar-email")
    public ModelAndView validarEmail(@RequestParam("token") String token) {
        ModelMap model = new ModelMap();

        try {
            servicioLogin.validarCorreo(token);
            model.put("mensaje", "¡Correo validado con éxito!");
            return new ModelAndView("notificacion", model);
        } catch (TokenInvalidoException e) {
            model.put("error", "Token de validación no válido");
            return new ModelAndView("notificacion", model);
        }
    }

    @RequestMapping("/reenviar-token")
    public ModelAndView reenviarToken(HttpServletRequest request) {
        ModelMap model = new ModelMap();

        try {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario != null) {
                servicioLogin.actualizarToken(usuario);
                model.put("mensaje", "¡Token reenviado con éxito!");
            } else {
                model.put("error", "Usuario no encontrado en sesión");
            }
        } catch (IOException e) {
            model.put("error", "Error al reenviar el token: " + e.getMessage());
        }

        return new ModelAndView("notificacion", model);
    }

    @RequestMapping(value = "/restablecer-password", method = RequestMethod.GET)
    public ModelAndView restablecerPassword() {

        return new ModelAndView("formulario-envio-mail-reseteo");
    }

    @RequestMapping(value = "/restablecer-password", method = RequestMethod.POST)
    public ModelAndView enviarMailRestablecerPassword(@RequestParam("email") String email) throws IOException {
        servicioLogin.enviarMailConInstruccion(email);

        ModelMap modelo = new ModelMap();
        modelo.put("datosLogin", new DatosLogin());
        modelo.put("envioMail", "Revisa tu casilla de correo para restablecer tu contraseña");
        return new ModelAndView("login2", modelo);
    }

    @RequestMapping(value = "/verificar-token-password", method = RequestMethod.GET)
    public ModelAndView verificarTokenPassword(@RequestParam("token") String tokenPassword) {
        ModelMap modelo = new ModelMap();

        if (servicioLogin.verificarTokenPassword(tokenPassword)) {
            modelo.put("token", tokenPassword);
            return new ModelAndView("modificar-password", modelo);
        } else {
            modelo.put("error", "Link inválido o ya usado, volvé a solicitar el cambio de contraseña");
            return new ModelAndView("formulario-envio-mail-reseteo", modelo);
        }

    }

    @RequestMapping(value = "/modificar-password", method = RequestMethod.POST)
    public ModelAndView modificarPassword(@RequestParam("password") String password,@RequestParam("token") String token) {
        servicioLogin.modificarPassword(password,token);
        ModelMap modelo = new ModelMap();
        modelo.put("datosLogin", new DatosLogin());
        modelo.put("exito","Contraseña restablecida con éxito, iniciá sesión porfavor");
        return new ModelAndView("login2",modelo);
    }
}

