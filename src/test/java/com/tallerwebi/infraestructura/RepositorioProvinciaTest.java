package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Provincia;
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
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioProvinciaTest {

    @Autowired
    RepositorioProvincia repositorio;

    @Transactional
    @Rollback
    @Test
    public void queSeListenTodasLasProvincias() {
        dadoQueTengo3Pronvincias();
        List<Provincia> busqueda = cuandoListoLasProvincias();
        entoncesSeListan3(busqueda);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarUnaProvinciaPorNombreSalta() {
        dadoQueTengo3Pronvincias();
        Provincia buscada = cuandoBuscoUnaProvinciaPorNombre("Salta");
        entoncesObtengo1Provincia(buscada);

    }

    private void entoncesObtengo1Provincia(Provincia buscada) {
        assertThat(buscada, is(notNullValue()));
    }

    private Provincia cuandoBuscoUnaProvinciaPorNombre(String nombre) {
        return repositorio.buscarProvinciaPorNombre(nombre);
    }

    private void entoncesSeListan3(List<Provincia> busqueda) {
        assertThat(busqueda, hasSize(3));
        assertThat(busqueda, is(notNullValue()));
    }

    private List<Provincia> cuandoListoLasProvincias() {
        return repositorio.obtenerTodasLasProvincias();
    }

    private void dadoQueTengo3Pronvincias() {
        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa

        Provincia cordoba = new Provincia("Cordoba", imagen);
        Provincia buenosAires = new Provincia("Buenos Aires", imagen);
        Provincia salta = new Provincia("Salta", imagen);

        repositorio.guardar(cordoba);
        repositorio.guardar(buenosAires);
        repositorio.guardar(salta);

    }
}
