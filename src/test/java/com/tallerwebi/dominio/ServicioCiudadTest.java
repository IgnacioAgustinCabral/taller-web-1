package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.ServicioCiudadImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioCiudadTest {

    private static final int CANTIDAD_CIUDADES = 3 ;
    private RepositorioCiudad repositorioCiudad;
    private ServicioCiudadImpl servicioCiudad;

    @BeforeEach
    public void init(){
        this.repositorioCiudad = mock(RepositorioCiudad.class);
        this.servicioCiudad= new ServicioCiudadImpl(this.repositorioCiudad);
    }

    @Test
    public void queSeObtenganTodasLasCiudades(){
        List <Ciudad> ciudades = generarCiudades(CANTIDAD_CIUDADES);
        when(repositorioCiudad.listarCiudades()).thenReturn(ciudades);

        List <Ciudad> listaCiudades = servicioCiudad.obtenerListaDeCiudades();
        assertThat(listaCiudades,hasSize(3));
        assertThat(listaCiudades, notNullValue());

    }

    private List<Ciudad> generarCiudades(int cantidadDeseada) {
        List <Ciudad>   ciudades = new ArrayList<>();
        for( int i=1; i<=cantidadDeseada; i++){
            ciudades.add(crearCiudad());
        }
        return ciudades;
    }

    private Ciudad crearCiudad() {
        Provincia provincia = new Provincia();
        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa
        return new  Ciudad("Colon",provincia,imagen);
    }


}
