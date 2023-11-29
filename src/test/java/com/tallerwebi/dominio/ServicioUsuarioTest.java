package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.ServicioUsuarioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioUsuarioTest {

    private RepositorioUsuario repositorioUsuario;
    private ServicioUsuario servicioUsuario;
    private static final Integer CANTIDAD_USUARIOS = 4;

    @BeforeEach
    public void init(){
        this.repositorioUsuario = mock(RepositorioUsuario.class);
        this.servicioUsuario = new ServicioUsuarioImpl(this.repositorioUsuario);
    }

    @Test
    public void queSeObtenganTodosLosUsuarios(){
        List<Usuario> usuarios = generarUsuarios(CANTIDAD_USUARIOS);
        when(this.repositorioUsuario.listarUsuarios()).thenReturn(usuarios);

        List <Usuario> usuariosObtenidos = this.servicioUsuario.obtenerUsuarios();

        assertThat(usuariosObtenidos,not(empty()));
        assertThat(usuariosObtenidos.size(), is(CANTIDAD_USUARIOS));
    }

    @Test
    public void queSePuedaBuscarUsuarioPorEmail(){
        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa

        Usuario usuarioEsperado = new Usuario("Nombre", "Apellido","12345678","2000/01/01", 011,12345678L,"nombreapellido@unlam.edu.ar", "contraseña", "admin", true,imagen);

        when(this.repositorioUsuario.buscarUsuario(anyString())).thenReturn(usuarioEsperado);

        Usuario usuarioObtenido = this.servicioUsuario.obtenerUsuarioPorEmail("nombreapellido@unlam.edu.ar");

        assertThat(usuarioObtenido, is(notNullValue()));
        assertThat(usuarioObtenido.getEmail(), equalTo(usuarioEsperado.getEmail()) );
    }

    private List<Usuario> generarUsuarios(int cantidadDeseada) {
        List <Usuario> usuarios = new ArrayList<>();
        for( int i=1; i<=cantidadDeseada; i++){
            usuarios.add(crearUsuario(i));
        }
        return usuarios;
    }

    private Usuario crearUsuario(Integer id) {
        byte[] imagen = new byte[]{0x12, 0x34, 0x56, 0x78}; // imagen falsa
        return new Usuario("Nombre" + id, "Apellido" + id ,"12345678","2000/01/01", 011,12345678L,"nombreapellido"+ id +"@unlam.edu.ar", "contraseña" + id, "admin", true,imagen);
    }
}
