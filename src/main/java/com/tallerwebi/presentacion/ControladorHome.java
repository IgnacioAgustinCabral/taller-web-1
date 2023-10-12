package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincia;
import com.tallerwebi.dominio.RepositorioViajeDisplay;
import com.tallerwebi.dominio.ServicioProvincia;
import com.tallerwebi.dominio.ViajeDisplay;
import com.tallerwebi.infraestructura.RepositorioViajeImpl;
import com.tallerwebi.infraestructura.ViajeDisplayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorHome {

    private final RepositorioViajeDisplay repositorioViajeDisplay;
    private ServicioProvincia servicioProvincia;

    @Autowired
    public ControladorHome(RepositorioViajeDisplay repositorioViajeDisplay, ServicioProvincia servicioProvincia) {
        this.repositorioViajeDisplay = repositorioViajeDisplay;
        this.servicioProvincia = servicioProvincia;
    }


    @Transactional
    @RequestMapping(path = "/home")
    public ModelAndView irAHome() {


        List<ViajeDisplay> datos = repositorioViajeDisplay.listarViajeDisplay();
        List<Provincia> provincia = servicioProvincia.obtenerProvinciasConImagenes();

        ModelMap model = new ModelMap();

        model.put("ultimosViajes", datos);
        model.put("provincias", provincia);

        return new ModelAndView("home", model);
    }

}
