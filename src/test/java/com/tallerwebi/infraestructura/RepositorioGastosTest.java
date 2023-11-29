package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Gasto;
import com.tallerwebi.dominio.RepositorioGasto;
import com.tallerwebi.dominio.RepositorioViaje;
import com.tallerwebi.dominio.Viaje;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioGastosTest {

    @Autowired
    RepositorioGasto repositorioGasto;
    @Autowired
    RepositorioViaje repositorioViaje;


    @Transactional
    @Rollback
    @Test
    public void queCuandoAgregueUnGastoAunViajesSeGuardeIdViaje(){
        Viaje nuevo = new Viaje();
        repositorioViaje.guardar(nuevo);

        Gasto nafta = new Gasto("nafta",10.000,nuevo,false);
        Gasto peaje = new Gasto("peaje",5000, nuevo,false);

        repositorioGasto.agregarGasto(nafta);
        repositorioGasto.agregarGasto(peaje);

        List<Gasto> gastosObtenidos = repositorioGasto.listarGastos();
        assertThat(gastosObtenidos, hasSize(2));
        assertThat(gastosObtenidos, notNullValue());
        assertEquals(nuevo,(nafta.getViaje()));

    }

    @Transactional
    @Rollback
    @Test
    public void queSeObtenganLosGastosParaUnViajeEspecifico(){
        Viaje nuevo = new Viaje();
        repositorioViaje.guardar(nuevo);
        Viaje nuevo2 = new Viaje();
        repositorioViaje.guardar(nuevo2);


        Gasto nafta = new Gasto("nafta",10.000,nuevo,false);
        Gasto peaje = new Gasto("peaje",5000, nuevo,false);
        Gasto nafta2 = new Gasto("peaje",5000, nuevo2,false);

        repositorioGasto.agregarGasto(nafta);
        repositorioGasto.agregarGasto(peaje);
        repositorioGasto.agregarGasto(nafta2);

        List<Gasto> gastosObtenidos = repositorioGasto.listarGastosPorViaje(nuevo);
        assertThat(gastosObtenidos, hasSize(2));
        assertThat(gastosObtenidos, notNullValue());

    }


}
