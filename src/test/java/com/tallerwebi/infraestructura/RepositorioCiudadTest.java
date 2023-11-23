package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Ciudad;
import com.tallerwebi.dominio.Provincia;
import com.tallerwebi.dominio.RepositorioCiudad;
import com.tallerwebi.dominio.RepositorioProvincia;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioCiudadTest {

    @Autowired
    RepositorioCiudad repositorioCiudad;
    @Autowired
    private RepositorioProvincia repositorioProvincia;

    @Transactional
    @Rollback
    @Test
    public void queSeListenTodasLasCiudades() {
        dadoQueTengo3ciudades();
        List<Ciudad> busqueda = cuandoListoTodasLasCiudades();
        estoncesObtengo3ciudades(busqueda);

    }

    private void estoncesObtengo3ciudades(List<Ciudad> busqueda) {
        assertThat(busqueda, hasSize(3));
        assertThat(busqueda, notNullValue());
    }

    private List<Ciudad> cuandoListoTodasLasCiudades() {
        return repositorioCiudad.listarCiudades();
    }

    private void dadoQueTengo3ciudades() {
        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa

        Provincia mendoza = new Provincia("Mendoza", imagen);
        Provincia cordoba = new Provincia("Cordoba", imagen);
        Provincia entreRios = new Provincia("Entre Rios", imagen);
        repositorioProvincia.guardar(mendoza);
        repositorioProvincia.guardar(cordoba);
        repositorioProvincia.guardar(entreRios);

        Ciudad sanRafael = new Ciudad("San Rafael", mendoza, imagen);
        Ciudad carlozPaz = new Ciudad("Carlos Paz", cordoba, imagen);
        Ciudad colon = new Ciudad("Colon", entreRios, imagen);

        repositorioCiudad.guardar(sanRafael);
        repositorioCiudad.guardar(carlozPaz);
        repositorioCiudad.guardar(colon);
    }

}
