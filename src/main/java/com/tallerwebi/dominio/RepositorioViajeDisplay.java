package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioViajeDisplay {

    ViajeDisplay buscarPorId(Long id);

    List<ViajeDisplay> listarViajeDisplay();
}
