package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioProvincia {
    void guardar(Provincia cordoba);

    List<Provincia> obtenerTodasLasProvincias();

    Provincia buscarProvinciaPorNombre(String nombre);

    List <Provincia> buscarProvinciasConImagenes();
}
