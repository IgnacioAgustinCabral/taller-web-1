package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})

public class RepositorioViajeTest {

    @Autowired
    private RepositorioViaje repositorio;
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioProvincia repositorioProvincia;
    @Autowired
    private RepositorioCiudad repositorioCiudad;

    @Transactional
    @Rollback
    @Test
    public void queSePuedaCrearUnViajeYseGuarde() {
        //preparacion
        Viaje viaje = dadoQueTengoUnViajeGuardado();
        Viaje busqueda = cuandoBuscoUnViajePorId(viaje);
        entoncesEsperoQueBusquedaNoSeaNull(busqueda);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarViajesPorDestinoTandil() {

        List<Viaje> viajes = dadoQueTengo3viajesGuardadosY2viajesConDestinoTandil();
        List<Viaje> buscados = cuandoBuscoViajesPorDestino(viajes);
        entoncesQueDevuelva2viajesConDestinoTandil(buscados);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaModificarElDestinoDeUnViaje() {
        Viaje viaje = dadoQueTengoUnViajeGuardado();
        Viaje buscado = cuandoCambioElDestinoAunaCiudadNueva(viaje);
        entoncesDebeCoincidirElDestinoYNoDebeDarNullValue(buscado);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedanListarLosViajes() {
        dadoQueTengo3viajesGuardadosY2viajesConDestinoTandil();
        List<Viaje> busqueda = cuandoListoTodosLosViajes();
        entoncesDevuelve3viajesYlaListaNoEsNull(busqueda);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarViajePorOrigen() {
        List<Viaje> viajes = dadoQueTengo3viajesGuardadosY2viajesConDestinoTandil();
        List<Viaje> buscados = cuandoBuscoPorOrigen(viajes);
        entoncesLaListaDevuelve2ValoresYnoEsNullValue(buscados);

    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarViajesPorFecha() {
        dadoQueTengo2ViajesGuardadosConDistintaFecha();
        List<Viaje> busqueda = cuandoBuscoPorFecha(LocalDate.now().toString());
        entoncesElTamanioEs1(busqueda);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaEliminarUnViaje() {

        Viaje viaje = dadoQueTengoUnViajeGuardado();
        Viaje eliminado = cuandoLoEliminoYloBusco(viaje);
        entoncesElValorEsNull(eliminado);
    }


    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarViajesCreadosPorUnUsuario() {
        dadoQueTengo2ViajesGuardadosConDistintaFecha();
        Usuario creador = new Usuario();
        repositorioUsuario.guardar(creador);

        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa

        Provincia buenosAires = new Provincia("Buenos Aires", imagen);
        repositorioProvincia.guardar(buenosAires);

        Ciudad junin = new Ciudad("Junin", buenosAires, imagen);
        repositorioCiudad.guardar(junin);

        Ciudad tandil = new Ciudad("Tandil", buenosAires, imagen);
        repositorioCiudad.guardar(tandil);

        Viaje viaje = new Viaje(tandil, junin, LocalDate.now().toString(), 2, "probando", creador);
        Viaje viaje2 = new Viaje(tandil, junin, LocalDate.now().toString(), 2, "probando", creador);

        repositorio.guardar(viaje);
        repositorio.guardar(viaje2);

        List<Viaje> buscados = repositorio.buscarPorUsuario(creador);

        for (Viaje viajesObtenidos : buscados) {
            assertThat(viajesObtenidos.getUsuario(), equalTo(creador));
        }
        assertThat(buscados, is(notNullValue()));
        assertThat(buscados, is(hasSize(2)));
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarPorOrigenDestinoYfecha() {

        Usuario usuario = new Usuario();
        repositorioUsuario.guardar(usuario);

        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa

        Provincia buenosAires = new Provincia("Buenos Aires", imagen);
        repositorioProvincia.guardar(buenosAires);

        Ciudad junin = new Ciudad("Junin", buenosAires, imagen);
        repositorioCiudad.guardar(junin);

        Ciudad tandil = new Ciudad("Tandil", buenosAires, imagen);
        repositorioCiudad.guardar(tandil);

        Viaje viaje = new Viaje(junin, tandil, LocalDate.now().toString(), 2, "probando", usuario);
        Viaje viaje2 = new Viaje(junin, junin, LocalDate.now().toString(), 2, "probando", usuario);
        Viaje viaje3 = new Viaje(junin, tandil, LocalDate.now().toString(), 2, "probando", usuario);

        repositorio.guardar(viaje);
        repositorio.guardar(viaje2);
        repositorio.guardar(viaje3);

        List<Viaje> buscados = repositorio.buscarPorOrigenDestinoYfecha(viaje.getOrigen(), viaje.getDestino(), viaje.getFecha());

        assertThat(buscados, is(hasSize(2)));
        assertThat(buscados, is(notNullValue()));
    }

    private void entoncesEsperoQueBusquedaNoSeaNull(Viaje busqueda) {
        assertThat(busqueda, is(notNullValue()));
    }

    private Viaje dadoQueTengoUnViajeGuardado() {
        Usuario usuario = new Usuario();
        repositorioUsuario.guardar(usuario);

        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa

        Provincia buenosAires = new Provincia("Buenos Aires", imagen);
        repositorioProvincia.guardar(buenosAires);

        Ciudad junin = new Ciudad("Junin", buenosAires, imagen);
        repositorioCiudad.guardar(junin);

        Ciudad tandil = new Ciudad("Tandil", buenosAires, imagen);
        repositorioCiudad.guardar(tandil);

        Viaje viaje = new Viaje(junin, tandil, LocalDate.now().toString(), 2, "probando", usuario);
        //ejecucion
        repositorio.guardar(viaje);
        return viaje;
    }

    private Viaje cuandoBuscoUnViajePorId(Viaje viaje) {
        return repositorio.buscarPorId(viaje.getId());
    }

    private void entoncesQueDevuelva2viajesConDestinoTandil(List<Viaje> buscados) {
        assertThat(buscados, hasSize(2));
    }

    private List<Viaje> cuandoBuscoViajesPorDestino(List<Viaje> viajes) {
        List<Viaje> viajesDestino = new ArrayList<>();
        for (Viaje viaje : viajes) {
            viajesDestino = repositorio.buscarPorDestino(viaje.getDestino());
        }
        return viajesDestino;
    }

    private ArrayList<Viaje> dadoQueTengo3viajesGuardadosY2viajesConDestinoTandil() {
        //preparacion
        ArrayList<Viaje> viajes = new ArrayList<>();
        Usuario usuario = new Usuario();
        repositorioUsuario.guardar(usuario);

        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa

        Provincia buenosAires = new Provincia("Buenos Aires", imagen);
        repositorioProvincia.guardar(buenosAires);

        Ciudad junin = new Ciudad("Junin", buenosAires, imagen);
        repositorioCiudad.guardar(junin);

        Ciudad tandil = new Ciudad("Tandil", buenosAires, imagen);
        repositorioCiudad.guardar(tandil);


        Viaje viaje = new Viaje(junin, tandil, LocalDate.now().toString(), 2, "probando", usuario);
        Viaje viaje2 = new Viaje(tandil, junin, LocalDate.now().toString(), 3, "probando", usuario);
        Viaje viaje3 = new Viaje(junin, tandil, LocalDate.now().toString(), 4, "probando", usuario);

        viajes.add(viaje);
        viajes.add(viaje2);
        viajes.add(viaje3);

        //ejecucion
        repositorio.guardar(viaje);
        repositorio.guardar(viaje2);
        repositorio.guardar(viaje3);

        return viajes;
    }

    private void entoncesDebeCoincidirElDestinoYNoDebeDarNullValue(Viaje viajeModificado) {
        //validacion
        Ciudad nuevoDestino = viajeModificado.getDestino();
        assertThat(viajeModificado, is(notNullValue()));
        assertThat(viajeModificado.getDestino(), equalTo(nuevoDestino));
    }

    private Viaje cuandoCambioElDestinoAunaCiudadNueva(Viaje viaje) {
        Provincia entreRios = new Provincia();

        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa
        Ciudad nuevoDestino = new Ciudad("Concordia", entreRios, imagen);
        viaje.setDestino(nuevoDestino);
        repositorio.actualizar(viaje);
        return repositorio.buscarPorId(viaje.getId());
    }

    private void entoncesDevuelve3viajesYlaListaNoEsNull(List<Viaje> busqueda) {
        assertThat(busqueda, is(notNullValue()));
        assertThat(busqueda.size(), equalTo(3));
    }

    private List<Viaje> cuandoListoTodosLosViajes() {
        return repositorio.listarViajes();
    }

    private void entoncesLaListaDevuelve2ValoresYnoEsNullValue(List<Viaje> buscados) {
        assertThat(buscados, is(notNullValue()));
        assertThat(buscados.size(), equalTo(2));
    }

    private List<Viaje> cuandoBuscoPorOrigen(List<Viaje> viajes) {
        List<Viaje> encontrados = new ArrayList<>();
        for (Viaje viaje : viajes) {
            encontrados = repositorio.buscarPorOrigen(viaje.getOrigen());
        }
        return encontrados;
    }

    private void entoncesElTamanioEs1(List<Viaje> busqueda) {
        //validacion
        assertThat(busqueda, is(hasSize(1)));
        assertThat(busqueda, is(notNullValue()));
    }

    private List<Viaje> cuandoBuscoPorFecha(String fecha) {
        return repositorio.buscarPorFecha(fecha);
    }

    private void dadoQueTengo2ViajesGuardadosConDistintaFecha() {
        Usuario creador = new Usuario();
        repositorioUsuario.guardar(creador);

        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa

        Provincia buenosAires = new Provincia("Buenos Aires", imagen);
        repositorioProvincia.guardar(buenosAires);

        Ciudad junin = new Ciudad("Junin", buenosAires, imagen);
        repositorioCiudad.guardar(junin);

        Ciudad tandil = new Ciudad("Tandil", buenosAires, imagen);
        repositorioCiudad.guardar(tandil);

        Viaje viaje = new Viaje(tandil, junin, LocalDate.now().toString(), 2, "probando", creador);
        Viaje viaje2 = new Viaje(tandil, junin, "2023-10-20", 2, "probando", creador);
        //ejecucion
        repositorio.guardar(viaje);
        repositorio.guardar(viaje2);
    }

    private void entoncesElValorEsNull(Viaje eliminado) {
        assertThat(eliminado, is(nullValue()));
    }

    private Viaje cuandoLoEliminoYloBusco(Viaje viaje) {

        repositorio.eliminar(viaje);

        return repositorio.buscarPorId(viaje.getId());
    }

}

