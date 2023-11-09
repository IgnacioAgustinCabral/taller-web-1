package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.ServicioCiudad;
import com.tallerwebi.dominio.ServicioViaje;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.Viaje;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    private ServicioCiudad servicioCiudadMock;
    private HttpSession sessionMockUsuario;

    @BeforeEach
    public void init(){
        viajeMock = mock(Viaje.class);
        usuarioMock = mock(Usuario.class);
        when(usuarioMock.getEmail()).thenReturn("test@unlam.com");
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioViajeMock = mock(ServicioViaje.class);
        servicioCiudadMock = mock(ServicioCiudad.class);
        controladorViaje = new ControladorViaje(servicioViajeMock,servicioCiudadMock);
        sessionMockUsuario = mock(HttpSession.class);
    }


    @Test
    public void queCuandoSeCreeUnViajeTeRedirijaAlHome(){
        // preparacion
        usuarioMock = new Usuario();
        viajeMock = new Viaje();
        rellenarDatosViaje(viajeMock);

        // ejecucion
        ModelAndView modelAndView = controladorViaje.crearViaje(viajeMock, sessionMock);

        // validacion
        assertThat(modelAndView, notNullValue());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/home"));
        assertThat(modelAndView.getModel().get("error"), nullValue());
    }

    @Test
    public void queCuandoCreesUnViajeSiHayErrorDeberiaMostrarVistaCrearViaje() {
        // Preparación
        usuarioMock = new Usuario();
        viajeMock = new Viaje();
        rellenarDatosViaje(viajeMock);

        // Ejecución
        ModelAndView modelAndView = controladorViaje.crearViaje(viajeMock, null);

        // Validación
        assertThat(modelAndView, notNullValue());
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("crear-viaje"));
        assertThat(modelAndView.getModel().get("error"), equalTo("Error al registrar el viaje, revise los campos"));
    }

    private void rellenarDatosViaje(Viaje viajeMock) {

        usuarioMock.setId(8L);
        viajeMock.setFecha("2024-03-15");
        viajeMock.setNoFumar(true);
        viajeMock.setNoMascotas(true);
        viajeMock.setNoNinios(true);
        viajeMock.setCantidad(4);
        viajeMock.setDescripcion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vulputate vehicula mauris");
        viajeMock.setUsuario(usuarioMock);

    }
}
