package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.ServicioViaje;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.Viaje;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class ControladorViajeTest {

    private ControladorViaje controladorViaje;
    private Usuario usuarioMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioViaje servicioViajeMock;
    private Viaje viajeMock;


    @BeforeEach
    public void init(){
        viajeMock = mock(Viaje.class);
        usuarioMock = mock(Usuario.class);
        when(usuarioMock.getEmail()).thenReturn("dami@unlam.com");
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioViajeMock = mock(ServicioViaje.class);
        controladorViaje = new ControladorViaje(servicioViajeMock);
    }


    @Test
    public void queCuandoSeCreeUnViajeTeRedirijaAlHome(){
        // preparacion

        // ejecucion
        ModelAndView modelAndView = controladorViaje.crearViaje(viajeMock, sessionMock);

        // validacion
        assertThat(modelAndView, notNullValue());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/home"));
    }

    @Test
    public void queCuandoCreesUnViajeSiHayErrorDeberiaMostrarVistaCrearViaje() {
        // Preparación

        // Ejecución
        ModelAndView modelAndView = controladorViaje.crearViaje(viajeMock, null);

        // Validación
        assertThat(modelAndView, notNullValue());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("crear-viaje"));
        assertThat(modelAndView.getModel().get("error"), equalTo("Error al registrar el viaje"));
    }

    @Test
    public void queCuandoVayaAMisViajesMeRedirijaYLosListe(){
        // preparacion
        //when(requestMock.getSession()).thenReturn(sessionMock);
        //usuarioMock = (Usuario) sessionMock.getAttribute("usuario");
        //when(servicioViajeMock.obtenerViajesCreadosPorUnUsuario(usuarioMock).thenReturn());

        // ejecucion
        ModelAndView modelAndView = controladorViaje.verMisViajes(sessionMock);

        // validacion
        assertThat(modelAndView, notNullValue());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("perfil/mis-viajes"));
    }
    @Test
    public void queCuandoVayaAMisViajesSinUnaSesionMeRedirijaAlLogin(){
        // preparacion
        //when(requestMock.getSession()).thenReturn(sessionMock);
        //usuarioMock = (Usuario) sessionMock.getAttribute("usuario");
        //when(servicioViajeMock.obtenerViajesCreadosPorUnUsuario(usuarioMock).thenReturn());

        // ejecucion
        ModelAndView modelAndView = controladorViaje.verMisViajes(null);

        // validacion
        assertThat(modelAndView, notNullValue());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
    }



}
