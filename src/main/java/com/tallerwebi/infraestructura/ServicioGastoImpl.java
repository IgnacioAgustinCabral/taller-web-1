package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Gasto;
import com.tallerwebi.dominio.RepositorioGasto;
import com.tallerwebi.dominio.ServicioGasto;
import com.tallerwebi.dominio.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioGastoImpl implements ServicioGasto {

    private final RepositorioGasto repositorioGasto;

    @Autowired
    public ServicioGastoImpl(RepositorioGasto repositorioGasto){
        this.repositorioGasto = repositorioGasto;
    }
    @Override
    public List<Gasto> obtenerGastosPorViaje(Viaje viaje) {
         List<Gasto> gastos = repositorioGasto.listarGastosPorViaje(viaje);
         return gastos;
    }

    @Override
    public void guardarGasto(Gasto gasto) {
        repositorioGasto.agregarGasto(gasto);
    }

    @Override
    public List<Gasto> obtenerTodosLosGastos() {
        return repositorioGasto.listarGastos();
    }
}
