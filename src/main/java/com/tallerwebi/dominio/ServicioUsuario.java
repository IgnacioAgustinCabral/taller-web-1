package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioUsuario {
    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuarioPorEmail(String email);

    Usuario obtenerUsuarioPorId(Long id);
    Boolean validarEmailUsuario(Usuario usuario) throws Exception;
}
