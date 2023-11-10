package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioComentario {

    void guardarComentario (Comentario comentario);

    void eliminarComentario (Comentario comentario);

    Comentario buscarComentarioPorId (Comentario id);

    List<Comentario> obtenerComentarios ();

    List<Comentario> buscarComentariosPorUsuario (Usuario usuario);
}
