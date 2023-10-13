package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCiudadImpl implements ServicioCiudad {
    private  RepositorioCiudad repositorioCiudad;

    public ServicioCiudadImpl(RepositorioCiudad repositorioCiudad) {
        this.repositorioCiudad = repositorioCiudad;
    }


    @Override
    public List<Ciudad> obtenerListaDeCiudades() {
        return repositorioCiudad.listarCiudades();
    }
}
