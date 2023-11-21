package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ServicioLogin {

    Usuario consultarUsuario(String email, String password);
    void registrar(Usuario usuario, MultipartFile imagenDePerfil) throws UsuarioExistente, IOException;

    void validarCorreo(String token);
}
