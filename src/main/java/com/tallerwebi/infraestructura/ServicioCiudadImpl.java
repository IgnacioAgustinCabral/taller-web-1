package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioCiudadImpl implements ServicioCiudad {
    private  RepositorioCiudad repositorioCiudad;

    public ServicioCiudadImpl(RepositorioCiudad repositorioCiudad) {
        this.repositorioCiudad = repositorioCiudad;
    }


    @Override
    public List<Ciudad> obtenerListaDeCiudades() {
        return repositorioCiudad.listarCiudades();
    }

    @Override
    public Ciudad obtenerCiudadPorId(Long id) {
        return repositorioCiudad.obtenerCiudadPorId(id);
    }
}
