package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
