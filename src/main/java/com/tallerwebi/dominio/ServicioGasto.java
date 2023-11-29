package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioGasto {
    List<Gasto> obtenerGastosPorViaje(Viaje viaje);

    void guardarGasto(Gasto gasto);

    List<Gasto> obtenerTodosLosGastos();
}
