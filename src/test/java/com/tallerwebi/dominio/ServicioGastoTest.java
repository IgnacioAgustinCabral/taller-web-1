package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.ServicioGastoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServicioGastoTest {

    private RepositorioGasto repositorioGasto;
    private ServicioGasto servicioGasto;
    private final int CANTIDAD_GASTOS = 2;

    @BeforeEach
    public void init(){
        this.repositorioGasto = mock(RepositorioGasto.class);
        this.servicioGasto = new ServicioGastoImpl(repositorioGasto);
    }

    @Test
    public void queSeObtenganTodosLosGastosDeUnViaje(){
        List<Gasto> gastos = generarGastos(CANTIDAD_GASTOS);
        when(this.repositorioGasto.listarGastosPorViaje(any())).thenReturn(gastos);
        List<Gasto>gastosObtenidos = new ArrayList<>();

        for (Gasto gasto: gastos) {
            gastosObtenidos = servicioGasto.obtenerGastosPorViaje(gasto.getViaje());
        }

        assertThat(gastosObtenidos.size(),is(CANTIDAD_GASTOS));
        assertThat(gastosObtenidos, notNullValue());
    }

    @Test
    public void queSePuedanAgregarGastosDeUnViaje(){
        Viaje viaje = new Viaje();
        Gasto gasto1 = new Gasto("nafta",15000,viaje,false);
        Gasto gasto2= new Gasto("peaje",5000,viaje,false);

        this.servicioGasto.guardarGasto(gasto1);
        this.servicioGasto.guardarGasto(gasto2);


        verify(repositorioGasto, times(2)).agregarGasto(any());
    }

    private List<Gasto> generarGastos(int cantidad) {
        List<Gasto> gastos = new ArrayList<>();
        for( int i=1; i<=cantidad; i++){
            gastos.add(crearGasto());
        }
        return gastos;
    }

    private Gasto crearGasto() {
        Viaje viaje = new Viaje();

      return new Gasto("nafta",5000,viaje,false);
    }

}
