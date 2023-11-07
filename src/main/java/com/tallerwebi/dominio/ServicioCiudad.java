package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioCiudad {
    List<Ciudad> obtenerListaDeCiudades();

    Ciudad obtenerCiudadPorId(Long id);
}
