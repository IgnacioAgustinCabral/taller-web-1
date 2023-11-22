package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.ServicioEmail;
import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.TokenInvalidoException;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

    private RepositorioUsuario servicioLoginDao;
    private ServicioEmail servicioEmail;
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioLoginImpl(RepositorioUsuario servicioLoginDao, ServicioEmail servicioEmail, RepositorioUsuario repositorioUsuario){
        this.servicioLoginDao = servicioLoginDao;
        this.servicioEmail = servicioEmail;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario consultarUsuario (String email, String password) {
        return servicioLoginDao.buscarUsuario(email, password);
    }

    @Override
    public void registrar(Usuario usuario, MultipartFile imagenDePerfil) throws UsuarioExistente, IOException {
        Usuario usuarioEncontrado = servicioLoginDao.buscarUsuario(usuario.getEmail(), usuario.getPassword());
        if(usuarioEncontrado != null){
            throw new UsuarioExistente();
        }

        String token = generarToken();

        usuario.setTokenValidacion(token);
        usuario.setImagenDePerfil(Base64.getEncoder().encode(imagenDePerfil.getBytes()));

        servicioLoginDao.guardar(usuario);

        enviarCorreoValidacion(usuario.getEmail(), token);
    }

    private String generarToken(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getEncoder().encodeToString(tokenBytes);
    }

    private void enviarCorreoValidacion(String destinatario, String token) throws IOException {

        String enlaceVerificacion = "http://localhost:8080/spring/validar-email?token=" + token;

        String cuerpoCorreo = "Hola! Gracias por Registrarte a TravelAndo. Haz clic en el siguiente enlace para verificar tu correo: " + enlaceVerificacion;

        servicioEmail.enviarMailRegistro(destinatario, cuerpoCorreo);
    }

    @Override
    public void validarCorreo(String token) {

        Usuario usuario = repositorioUsuario.buscarPorTokenValidacion(token);

        if (usuario != null) {
            usuario.setEmailValidado(true);

            repositorioUsuario.actualizar(usuario);

            //servicioEmail.enviarCorreoConfirmacion(usuario.getEmail(), "¡Correo validado con éxito!");
        } else {
            throw new TokenInvalidoException("Token de validación no válido");
        }
    }


}


