package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioUsuario {

    Usuario buscarUsuario(String email, String password);
    Usuario buscarUsuario(String email);
    void guardar(Usuario usuario);
    Usuario buscar(String email);
    void modificar(Usuario usuario);
    List<Usuario> listarUsuarios();

    Usuario buscarUsuarioPorId(Long id);
}

