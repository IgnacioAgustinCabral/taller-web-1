package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioProvincia {
    List<Provincia> obtenerProvincias();

    Provincia obtenerProvinciaPorNombre(String nombre);

    List <Provincia> obtenerProvinciasConImagenes();

    Provincia obtenerProvinciaPorId(Long id);
}
