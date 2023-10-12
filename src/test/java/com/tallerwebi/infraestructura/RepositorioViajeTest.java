package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.RepositorioViaje;
import com.tallerwebi.dominio.Usuario;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes={SpringWebTestConfig.class, HibernateTestConfig.class})

public class RepositorioViajeTest {

    @Autowired
    private RepositorioViaje repositorio;
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Transactional
    @Rollback
    @Test
    public void queSePuedaCrearUnViajeYseGuarde(){
        //preparacion
        Viaje viaje = dadoQueTengoUnViajeGuardado();
        Viaje busqueda= cuandoBuscoUnViajePorId(viaje);
        entoncesEsperoQueBusquedaNoSeaNull(busqueda);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarViajesPorDestinoTucuman(){
        dadoQueTengo3viajesGuardadosY2viajesConDestinoTucuman();
        List <Viaje> buscados = cuandoBuscoViajesPorDestino("Tucuman");
        entoncesQueDevuelva2viajesConDestinoTucuman(buscados);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaModificarElDestinoDeUnViaje(){
        Viaje viaje = dadoQueTengoUnViajeGuardado();
        Viaje buscado = cuandoCambioElDestinoASalta(viaje);
        entoncesDebeCoincidirElDestinoYNoDebeDarNullValue(buscado);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedanListarLosViajes(){
        dadoQueTengo3viajesGuardadosY2viajesConDestinoTucuman();
        List<Viaje> busqueda = cuandoListoTodosLosViajes();
        entoncesDevuelve3viajesYlaListaNoEsNull(busqueda);
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarViajePorOrigen(){

        dadoQueTengo3viajesGuardadosY2viajesConDestinoTucuman();
        List<Viaje> buscados =  cuandoBuscoPorOrigen("Buenos Aires");
        entoncesLaListaDevuelve2ValoresYnoEsNullValue(buscados);

    }
    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarViajesPorFecha(){
        dadoQueTengo2ViajesGuardadosConDistintaFecha();
        List <Viaje> busqueda = cuandoBuscoPorFecha("20/10/2023 14:05:00");
        entoncesElTamanioEs1(busqueda);
    }
    @Transactional
    @Rollback
    @Test
    public void queSePuedaEliminarUnViaje(){

        Viaje viaje =  dadoQueTengoUnViajeGuardado();
        Viaje eliminado = cuandoLoEliminoYloBusco(viaje);
        entoncesElValorEsNull(eliminado);
    }


    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarViajesCreadosPorUnUsuario(){
        dadoQueTengo2ViajesGuardadosConDistintaFecha();
        Usuario creador = new Usuario();
        repositorioUsuario.guardar(creador);

        Viaje viaje = new Viaje("Buenos Aires", "Tucuman", LocalDateTime.now().toString(), 2, "probando", creador);
        Viaje viaje2 = new Viaje("Buenos Aires", "Rosario", LocalDateTime.now().toString(), 2, "probando", creador);

        repositorio.guardar(viaje);
        repositorio.guardar(viaje2);

        List <Viaje> buscados = repositorio.buscarPorUsuario(creador);

        for (Viaje viajesObtenidos : buscados) {
            assertThat(viajesObtenidos.getUsuario(),equalTo(creador));
        }
        assertThat(buscados, is(notNullValue()));
        assertThat(buscados,is(hasSize(2)));
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarPorOrigenDestinoYfecha(){

        Usuario usuario = new Usuario();
        repositorioUsuario.guardar(usuario);

        Viaje viaje = new Viaje("Buenos Aires", "Tucuman", LocalDateTime.now().withSecond(0).withNano(0).toString(), 2, "probando", usuario);
        Viaje viaje2 = new Viaje("Buenos Aires", "Jujuy", LocalDateTime.now().withSecond(0).withNano(0).toString(), 2, "probando", usuario);
        Viaje viaje3 = new Viaje("Buenos Aires", "Tucuman", LocalDateTime.now().withSecond(0).withNano(0).toString(), 2, "probando", usuario);

        repositorio.guardar(viaje);
        repositorio.guardar(viaje2);
        repositorio.guardar(viaje3);

        List <Viaje> buscados = repositorio.buscarPorOrigenDestinoYfecha(viaje.getOrigen(),viaje.getDestino(),viaje.getFecha_hora());

        assertThat(buscados,is(hasSize(2)));
        assertThat(buscados,is(notNullValue()));

    }

    private void entoncesEsperoQueBusquedaNoSeaNull(Viaje busqueda) {
        assertThat(busqueda , is(notNullValue()));
    }

    private Viaje dadoQueTengoUnViajeGuardado() {
        Usuario usuario = new Usuario();
        repositorioUsuario.guardar(usuario);
        Viaje viaje = new Viaje("Buenos Aires", "Tucuman", LocalDateTime.now().toString(), 2, "probando", usuario);
        //ejecucion
        repositorio.guardar(viaje);
        return viaje;
    }

    private Viaje cuandoBuscoUnViajePorId(Viaje viaje){
        return repositorio.buscarPorId(viaje.getId());
    }

    private void entoncesQueDevuelva2viajesConDestinoTucuman(List<Viaje> buscados) {
        assertThat(buscados ,hasSize(2));
        for(Viaje viajeListado : buscados){
            assertThat(viajeListado.getDestino(), equalTo("Tucuman"));
        }
    }

    private List<Viaje> cuandoBuscoViajesPorDestino(String viaje) {
        return repositorio.buscarPorDestino(viaje);
    }

    private ArrayList<Viaje> dadoQueTengo3viajesGuardadosY2viajesConDestinoTucuman() {
        //preparacion
        ArrayList <Viaje> viajes = new ArrayList<>();
        Usuario usuario = new Usuario();
        repositorioUsuario.guardar(usuario);
        Viaje viaje = new Viaje("Buenos Aires", "Tucuman", LocalDateTime.now().toString(), 2, "probando", usuario);
        Viaje viaje2 = new Viaje("Buenos Aires", "Bariloche", LocalDateTime.now().toString(), 3, "probando", usuario);
        Viaje viaje3 = new Viaje("San Juan", "Tucuman", LocalDateTime.now().toString(), 4, "probando", usuario);

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
        String nuevoDestino = "Salta";
        assertThat(viajeModificado , is(notNullValue()));
        assertThat(viajeModificado.getDestino(), equalTo(nuevoDestino));
    }
    private Viaje cuandoCambioElDestinoASalta(Viaje viaje) {
        String nuevoDestino = "Salta";
        viaje.setDestino(nuevoDestino);
        repositorio.actualizar(viaje);
        return repositorio.buscarPorId(viaje.getId());
    }

    private void entoncesDevuelve3viajesYlaListaNoEsNull(List<Viaje> busqueda) {
        assertThat(busqueda , is(notNullValue()));
        assertThat(busqueda.size(), equalTo(3));
    }

    private List<Viaje> cuandoListoTodosLosViajes() {
        return repositorio.listarViajes();
    }

    private void entoncesLaListaDevuelve2ValoresYnoEsNullValue(List<Viaje> buscados) {
        assertThat(buscados , is(notNullValue()));
        assertThat(buscados.size(), equalTo(2));
    }

    private List<Viaje> cuandoBuscoPorOrigen(String origen) {

        return repositorio.buscarPorOrigen(origen);
    }
    private void entoncesElTamanioEs1(List<Viaje> busqueda) {
        //validacion
        assertThat( busqueda , is(hasSize(1)));
        assertThat(busqueda, is(notNullValue()));
    }

    private List<Viaje> cuandoBuscoPorFecha(String fecha) {
        return repositorio.buscarPorFecha(fecha);
    }

    private void dadoQueTengo2ViajesGuardadosConDistintaFecha() {
        Usuario creador = new Usuario();
        repositorioUsuario.guardar(creador);
        Viaje viaje = new Viaje("Buenos Aires", "Tucuman", LocalDateTime.now().withSecond(0).withNano(0).toString(), 2, "probando", creador);
        Viaje viaje2 = new Viaje("Buenos Aires", "Tucuman","20/10/2023 14:05:00", 2, "probando", creador);
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

