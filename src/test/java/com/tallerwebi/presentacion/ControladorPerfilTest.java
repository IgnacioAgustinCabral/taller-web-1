package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class ControladorPerfilTest {
    private ControladorPerfil controladorPerfil;
    private Usuario usuarioMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioViaje servicioViajeMock;
    private Viaje viajeMock;
    private ServicioCiudad servicioCiudadMock;
    private HttpSession sessionMockUsuario;
    private ServicioUsuario servicioUsuarioMock;
    private ServicioComentario servicioComentarioMock;
    private ServicioGasto servicioGastoMock;

    @BeforeEach
    public void init(){
        viajeMock = mock(Viaje.class);
        usuarioMock = mock(Usuario.class);
        when(usuarioMock.getEmail()).thenReturn("test@unlam.com");
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioViajeMock = mock(ServicioViaje.class);
        servicioCiudadMock = mock(ServicioCiudad.class);
        servicioUsuarioMock = mock(ServicioUsuario.class);
        servicioGastoMock = mock(ServicioGasto.class);
        controladorPerfil = new ControladorPerfil(servicioViajeMock, servicioUsuarioMock,servicioGastoMock, servicioComentarioMock);
        sessionMockUsuario = mock(HttpSession.class);
    }


    @Test
    public void queCuandoVayaAMisViajesConUnaSesionIniciadaMeLleveAMisViajes(){
        // preparacion
        //TODO Accedi a la sesion mediante MockHttpSession
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usuario", usuarioMock);

        // ejecucion
        ModelAndView modelAndView = controladorPerfil.verMiPerfil(session);

        // validacion
        assertThat(modelAndView, notNullValue());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("perfil"));
    }

    @Test
    public void queCuandoVayaAMisViajesSinUnaSesionMeRedirijaAlLogin(){
        // preparacion

        // ejecucion
        ModelAndView modelAndView = controladorPerfil.verMiPerfil(sessionMockUsuario);

        // validacion
        assertThat(modelAndView, notNullValue());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
    }


}
