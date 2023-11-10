package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioComentarioImpl implements ServicioComentario {

    private RepositorioComentario repositorioComentario;
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioComentarioImpl (RepositorioComentario repositorioComentario, RepositorioUsuario repositorioUsuario){
        this.repositorioComentario = repositorioComentario;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Comentario> obtenerComentarios() {
        return repositorioComentario.obtenerComentarios();
    }

    @Override
    public List<Comentario> obtenerComentariosPorUsuario(Usuario idUsuario) {
        return repositorioComentario.buscarComentariosPorUsuario(idUsuario);
    }

    @Override
    public Comentario obtenerComentarioPorId(Comentario idComentario) {
        return repositorioComentario.buscarComentarioPorId(idComentario);
    }

    @Override
    public void crearComentario(Comentario comentario) {
        this.repositorioComentario.guardarComentario(comentario);
    }
}
