package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.ServicioProvinciaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioProvinciaTest {

    private static final Integer CANTIDAD_PROVINCIAS = 3;
    private RepositorioProvincia repositorioProvincia;
    private ServicioProvincia servicioProvincia;

    @BeforeEach
    public void init(){
        this.repositorioProvincia = mock(RepositorioProvincia.class);
        this.servicioProvincia= new ServicioProvinciaImpl(this.repositorioProvincia);
    }

    @Test
    public void queSeObtenganTodasLasProvincias(){
        List<Provincia> provincias = generarProvincia(CANTIDAD_PROVINCIAS);
        when(this.repositorioProvincia.obtenerTodasLasProvincias()).thenReturn(provincias);

        List <Provincia> provinciaList = this.servicioProvincia.obtenerProvincias();

        assertThat(provinciaList,not(empty()));
        assertThat(provinciaList.size(), is(CANTIDAD_PROVINCIAS) );

    }

    @Test
    public void queSePuedanBuscarProvinciasPorNombre(){
        Provincia provinciaEsperada = crearProvincia();
        when(this.repositorioProvincia.buscarProvinciaPorNombre(anyString())).thenReturn(provinciaEsperada);

        Provincia provinciaXnombre = this.servicioProvincia.obtenerProvinciaPorNombre("Salta");

        assertThat(provinciaXnombre,is(notNullValue()));
        assertThat(provinciaXnombre.getNombre(),equalTo("Salta"));


    }

    private List<Provincia> generarProvincia(Integer cantidadProvincias) {
        List <Provincia>   provincias = new ArrayList<>();
        for( int i=1; i<=cantidadProvincias; i++){
            provincias.add(crearProvincia());
        }
        return provincias;
    }

    private Provincia crearProvincia() {
        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa
        return new Provincia("Salta",imagen);
    }
}
