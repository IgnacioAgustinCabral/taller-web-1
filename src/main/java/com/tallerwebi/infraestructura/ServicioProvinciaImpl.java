package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Provincia;
import com.tallerwebi.dominio.RepositorioProvincia;
import com.tallerwebi.dominio.ServicioProvincia;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioProvinciaImpl implements ServicioProvincia {
    private final RepositorioProvincia repositorioProvincia;

    public ServicioProvinciaImpl(RepositorioProvincia repositorioProvincia) {
        this.repositorioProvincia = repositorioProvincia;
    }

    @Override
    public List<Provincia> obtenerProvincias() {
        return repositorioProvincia.obtenerTodasLasProvincias();
    }

    @Override
    public Provincia obtenerProvinciaPorNombre(String nombre) {
        return this.repositorioProvincia.buscarProvinciaPorNombre(nombre);
    }

    @Override
    public List<Provincia> obtenerProvinciasConImagenes() {
        return this.repositorioProvincia.buscarProvinciasConImagenes();
    }

    @Override
    public Provincia obtenerProvinciaPorId(Long id) {
        return this.repositorioProvincia.obtenerProvinciaPorId(id);
    }
}
