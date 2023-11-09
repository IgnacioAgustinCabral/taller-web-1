package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioComentario {

    List<Comentario> obtenerComentarios();

    List<Comentario> obtenerComentariosPorUsuario ();
}
