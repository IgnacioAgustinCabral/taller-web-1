package com.tallerwebi.dominio;


import com.tallerwebi.infraestructura.ServicioViajeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class ServicioViajeTest {

    private RepositorioViaje repositorioViaje;
    private ServicioViaje servicioViaje;
    public static final Integer CANTIDAD_VIAJES = 4;

    @BeforeEach
    public void init(){
        this.repositorioViaje = mock(RepositorioViaje.class);
        this.servicioViaje = new ServicioViajeImpl(this.repositorioViaje);
    }
    @Test
    public void queSeObtenganTodosLosViajes(){
        List <Viaje> viajes = generarViajes(CANTIDAD_VIAJES);
        when(this.repositorioViaje.listarViajes()).thenReturn(viajes);

        List <Viaje> viajesObtenidos = this.servicioViaje.obtenerViajes();

        assertThat(viajesObtenidos,not(empty()));
        assertThat(viajesObtenidos.size(), is(CANTIDAD_VIAJES) );

    }

    @Test
    public void queSePuedanBuscarViajesPorDestino(){
        List <Viaje> viajesEsperados = generarViajes(CANTIDAD_VIAJES);
        when(this.repositorioViaje.buscarPorDestino(anyLong())).thenReturn(viajesEsperados);

        List <Viaje> viajesObtenidos = new ArrayList<>();
        for (Viaje viaje: viajesEsperados) {
            viajesObtenidos = this.servicioViaje.obtenerViajesPorDestino(viaje.getDestino());
        }

        //assertThat(viajesObtenidos,not(empty()));
        assertThat(viajesObtenidos.size(), is(CANTIDAD_VIAJES) );

    }



    @Test
    public void queSePuedanBuscarViajesPorOrigen(){
        List <Viaje> viajesEsperados = generarViajes(CANTIDAD_VIAJES);

        when(this.repositorioViaje.buscarPorOrigen(anyLong())).thenReturn(viajesEsperados);

        List <Viaje> viajesObtenidos = new ArrayList<>();
        for (Viaje viaje : viajesEsperados) {
            viajesObtenidos = this.servicioViaje.obtenerViajesPorOrigen(viaje.getOrigen().getId());

        }
        assertThat(viajesObtenidos,not(empty()));
        assertThat(viajesObtenidos.size(), is(CANTIDAD_VIAJES) );

    }
    @Test
    public void queSePuedaCrearUnViaje(){
        Viaje viajeEsperado = crearViaje();
        this.repositorioViaje.guardar(viajeEsperado);
        verify(repositorioViaje, times(1)).guardar(eq(viajeEsperado));
    }

    @Test
    public void queSePuedanBuscarViajesPorFecha(){
        Usuario usuario = new Usuario();
        List <Viaje> viajesEsperados = new ArrayList();
        Provincia buenosAires = new Provincia("Buenos Aires","");
        Ciudad junin = new Ciudad ("Junin",buenosAires,"");
        Ciudad tandil = new Ciudad ("Tandil",buenosAires,"");

        viajesEsperados.add(new  Viaje(tandil,junin, LocalDate.now().toString(), 2, "probando", usuario));
        viajesEsperados.add(new  Viaje(tandil,junin, LocalDate.now().toString(), 2, "probando", usuario));
        viajesEsperados.add(new  Viaje(tandil,junin, LocalDate.now().toString(), 2, "probando", usuario));
        when(this.repositorioViaje.buscarPorFecha(any())).thenReturn(viajesEsperados);

        List <Viaje> viajesObtenidos = this.servicioViaje.obtenerViajesPorFecha(LocalDate.now().toString());

        assertThat(viajesObtenidos,not(empty()));
        assertThat(viajesObtenidos.size(), is(3) );

        for (Viaje viaje: viajesObtenidos) {
            assertThat(viaje.getFecha(),equalTo(viajesEsperados.get(0).getFecha()));
        }
    }

    @Test
    /*public void queSePuedanBuscarViajesPorMultiplesFiltros(){

        Usuario usuario = new Usuario();
        List <Viaje> viajesEsperados = new ArrayList();
        Provincia buenosAires = new Provincia("Buenos Aires","");
        Ciudad junin = new Ciudad ("Junin",buenosAires,"");
        Ciudad tandil = new Ciudad ("Tandil",buenosAires,"");
        viajesEsperados.add(new  Viaje(tandil,junin, LocalDate.now().toString(), 2, "probando", usuario));
        viajesEsperados.add(new  Viaje(tandil,junin, LocalDate.now().toString(), 2, "probando", usuario));
        viajesEsperados.add(new  Viaje(tandil,junin, LocalDate.now().toString(), 2, "probando", usuario));
        when(this.repositorioViaje.buscarPorOrigenDestinoYfecha(any(),any(),any())).thenReturn(viajesEsperados);

        List <Viaje> viajesObtenidos = this.servicioViaje.obtenerViajesPorFiltroMultiple(tandil,junin,LocalDate.now().toString());

        assertThat(viajesObtenidos,not(empty()));
        assertThat(viajesObtenidos.size(), is(3) );

        for (Viaje viaje: viajesObtenidos) {
            assertThat(viaje.getOrigen(),equalTo(viajesEsperados.get(0).getOrigen()));
            assertThat(viaje.getDestino(),equalTo(viajesEsperados.get(0).getDestino()));
            assertThat(viaje.getFecha(),equalTo(viajesEsperados.get(0).getFecha()));
        }
    }*/

    private List<Viaje> generarViajes(int cantidadDeseada) {
        List <Viaje>   viajes = new ArrayList<>();
        for( int i=1; i<=cantidadDeseada; i++){
            viajes.add(crearViaje());
        }
        return viajes;
    }

    private Viaje crearViaje() {
        Usuario usuario = new Usuario();
        Provincia buenosAires = new Provincia("Buenos Aires","");
        Ciudad junin = new Ciudad ("Junin",buenosAires,"");
        Ciudad tandil = new Ciudad ("Tandil",buenosAires,"");
        return new  Viaje(junin,tandil, LocalDate.now().toString(), 2, "probando", usuario);
    }
    private Usuario crearUsuario() {
        return new Usuario();
    }
}
