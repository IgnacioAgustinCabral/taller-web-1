package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioGasto {
    void agregarGasto(Gasto gasto);

    List<Gasto> listarGastos();

    List<Gasto> listarGastosPorViaje(Viaje viaje);
}
