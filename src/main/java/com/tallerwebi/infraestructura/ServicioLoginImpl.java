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

import javax.mail.MessagingException;
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
    public ServicioLoginImpl(RepositorioUsuario servicioLoginDao, ServicioEmail servicioEmail){
        this.servicioLoginDao = servicioLoginDao;
        this.servicioEmail = servicioEmail;

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

    private void enviarCorreoValidacion(String destinatario, String token){

        String enlaceVerificacion = "http://localhost:8080/spring/validar-email?token=" + token;

        String cuerpoCorreo = "Haz clic en el siguiente enlace para verificar tu correo: " + enlaceVerificacion;

        try {
            // Utilizar el servicio de correo electrónico
            servicioEmail.enviarCorreo(destinatario, "Verificación de correo electrónico", cuerpoCorreo);
        } catch (MessagingException e) {
            // Manejar errores de envío de correo
            e.printStackTrace();
        }
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


