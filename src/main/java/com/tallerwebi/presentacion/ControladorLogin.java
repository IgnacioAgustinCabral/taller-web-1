package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.TokenInvalidoException;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class
ControladorLogin {

    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin){
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

    @RequestMapping(path = "/registrarme", method = RequestMethod.POST,consumes = {"multipart/form-data"})
    public ModelAndView registrarme(@ModelAttribute("usuario") Usuario usuario, @RequestPart("imagenDePerfil") MultipartFile imagenDePerfil) {
        ModelMap model = new ModelMap();
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
}

