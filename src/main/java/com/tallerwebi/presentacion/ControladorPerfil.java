package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/perfil")
public class ControladorPerfil {

    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorPerfil(ServicioUsuario servicioUsuario){ this.servicioUsuario = servicioUsuario;}
    @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView irAPerfil(@RequestParam(required = false) String email, ModelAndView mv){

        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorEmail(email);
        mv.addObject("usuario",usuarioBuscado);
        mv.setViewName("perfil/perfil");
        return mv;
    }
}