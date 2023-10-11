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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes={SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioUsuarioTest {

    @Autowired
    private RepositorioUsuario repositorio;

    @Transactional
    @Rollback
    @Test
    public void queSePuedaCrearUnUsuarioYseGuarde(){
        //preparacion
        Usuario creador = new Usuario("Carolina", "Rojas",12345678L,"2000/01/01", 011,12345678L,"carolinarojas@unlam.edu.ar", "contraseña1", "images/avatars/av-1.jpg", "admin", true);
        //TODO: sacar el id de los tests, lo maneja hibernate

        //ejecucion
        repositorio.guardar(creador);

        Usuario busqueda = repositorio.buscarUsuario(creador.getEmail(), creador.getPassword());
        //validacion
        assertThat(busqueda , is(notNullValue()));
    }

    @Transactional
    @Rollback
    @Test
    public void queSePuedaBuscarUsuarioPorEmail(){

        //TODO: sacar el id de los tests, lo maneja hibernate
        //preparacion
        String expected = "albertosamudio@unlam.edu.ar";
        Usuario creador = new Usuario("Alberto", "Samudio",12345678L,"2000/01/01", 011,12345678L,"albertosamudio@unlam.edu.ar", "contraseña1", "images/avatars/av-1.jpg", "admin", true);

        //ejecucion
        repositorio.guardar(creador);
        Usuario usuarioEncontrado = repositorio.buscarUsuario(expected);

        //validacion
        assertThat(usuarioEncontrado , is(notNullValue()));
        assertThat(usuarioEncontrado.getEmail() , equalTo(expected));
    }
}