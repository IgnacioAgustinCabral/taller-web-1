package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.NullEmailValidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario){this.repositorioUsuario = repositorioUsuario;}


    @Override
    public List<Usuario> obtenerUsuarios() {
        return repositorioUsuario.listarUsuarios();
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        return repositorioUsuario.buscarUsuario(email);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return repositorioUsuario.buscarUsuarioPorId(id);
    }

    @Override
    public Boolean validarEmailUsuario(Usuario usuario) throws Exception{

        Usuario nuevo = this.repositorioUsuario.buscarUsuarioPorId(usuario.getId());

        if (nuevo.isEmailValidado() == null)
            throw new NullEmailValidoException("No se conoce estado de validacion de email.");

        return nuevo.isEmailValidado();
    }
}
