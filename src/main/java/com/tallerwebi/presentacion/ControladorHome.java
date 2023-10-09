package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ViajeDisplay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorHome {
    @RequestMapping(path = "/home")
    public ModelAndView irAHome() {

        List<ViajeDisplay> datos = new ArrayList<>();

        ViajeDisplay viaje1 = new ViajeDisplay("Carolina Rojas", "images/avatars/av-1.jpg", "15/03/2024", "images/products/jujuy_web.jpg", "Moron, AMBA", "Purmamarca, Jujuy");
        datos.add(viaje1);

        ViajeDisplay viaje2 = new ViajeDisplay("Mariano Ochoa", "images/avatars/av-2.jpg", "01/12/2024", "images/products/marDelPlata_web.jpg", "Santa Rosa, La Pampa", "Mar Del Plata, Buenos Aires");
        datos.add(viaje2);

        ViajeDisplay viaje3 = new ViajeDisplay("Julia Ameghino", "images/avatars/av-3.jpg", "08/06/2024", "images/products/misiones_web.jpg", "San Justo, AMBA", "Puerto Iguazu, Misiones");
        datos.add(viaje3);

        ViajeDisplay viaje4 = new ViajeDisplay("Leandro Ulloa", "images/avatars/av-4.jpg", "22/03/2024", "images/products/rioNegro_web.jpg", "Bahia Blanca, Buenos Aires", "El Bolsón, Rio Negro");
        datos.add(viaje4);

        ViajeDisplay viaje5 = new ViajeDisplay("Federico Perez", "images/avatars/av-5.jpg", "15/08/2024", "images/products/santacruz_web.jpg", "Olivos, AMBA", "Río Gallegos, Santa Cruz");
        datos.add(viaje5);

        ModelMap model = new ModelMap();
        model.put("ultimosViajes", datos);



        return new ModelAndView("home", model);
    }




    // Constructor para crear algunos viajes ficticios
    public ControladorHome() {

    }

    /*@RequestMapping("/my-home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("datosHome", listaViajes);
        modelAndView.addObject("busquedaForm", new BusquedaForm());
        return modelAndView;
    }*/
}
