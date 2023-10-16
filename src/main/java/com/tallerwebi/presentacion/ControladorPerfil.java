package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.RepositorioViajeDisplay;
import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.ViajeDisplay;
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

import java.util.List;

@Controller
@Transactional
@RequestMapping("/perfil")
public class ControladorPerfil {

    private ServicioUsuario servicioUsuario;
    private final RepositorioViajeDisplay repositorioViajeDisplay;

    @Autowired
    public ControladorPerfil(RepositorioViajeDisplay repositorioViajeDisplay,ServicioUsuario servicioUsuario){
        this.repositorioViajeDisplay = repositorioViajeDisplay;
        this.servicioUsuario = servicioUsuario;}
    @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView irAPerfil(@RequestParam(required = false) String email){
        ModelMap modelo = new ModelMap();
        //TODO: completar con try catch - manejar excepciones - revisar porque trae un mv
        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorEmail(email);
        List<ViajeDisplay> viajes = repositorioViajeDisplay.listarViajeDisplay();
        modelo.put("usuario",usuarioBuscado);
        modelo.put("viajes",viajes);
        return new ModelAndView("perfil/perfil",modelo);
    }

/*    @RequestMapping(value="/usuario", method = RequestMethod.GET )
    public ModelAndView encontrarUsuarioPorId(@RequestParam(required = false) Long id){

        Usuario usuarioBuscado = servicioUsuario.obtenerUsuarioPorId(id);
        ModelMap model = new ModelMap();
        model.put("usuario", usuarioBuscado);
        return new ModelAndView("perfil/perfil", model);

    }*/

}
