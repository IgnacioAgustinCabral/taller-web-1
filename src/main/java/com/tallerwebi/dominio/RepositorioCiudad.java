package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioCiudad {

    public void guardar(Ciudad ciudad) ;

    List<Ciudad> listarCiudades();

    Ciudad obtenerCiudadPorId(Long id);
}
