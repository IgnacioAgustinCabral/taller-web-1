package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {
     @RequestMapping("/home")
     public ModelAndView irAHome(){
         /*DatosHome dato1 = new DatosHome("Mendoza",new Date());
         DatosHome dato2 = new DatosHome("Córdoba",new Date());

         List<DatosHome> datos = new LinkedList<>();

         datos.add(dato1);
         datos.add(dato2);

         ModelMap model = new ModelMap();
         model.put("datosHome",datos);*/

         return new ModelAndView("home");
     }


    // Constructor para crear algunos viajes ficticios
    public HomeController() {

    }

    /*@RequestMapping("/my-home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("datosHome", listaViajes);
        modelAndView.addObject("busquedaForm", new BusquedaForm());
        return modelAndView;
    }*/
}
