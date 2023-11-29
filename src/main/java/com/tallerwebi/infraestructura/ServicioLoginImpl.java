package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.ServicioEmail;
import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.TokenInvalidoException;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ServicioLoginImpl(RepositorioUsuario servicioLoginDao, ServicioEmail servicioEmail, RepositorioUsuario repositorioUsuario) {
        this.servicioLoginDao = servicioLoginDao;
        this.servicioEmail = servicioEmail;
        this.repositorioUsuario = repositorioUsuario;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Usuario consultarUsuario(String email, String passwordIngresada) {
        Usuario usuarioBuscado = servicioLoginDao.buscarUsuario(email);

        if (usuarioBuscado != null && passwordEncoder.matches(passwordIngresada, usuarioBuscado.getPassword())) {
            return usuarioBuscado;
        }

        return null;
    }

    @Override
    public void registrar(Usuario usuario, MultipartFile imagenDePerfil) throws UsuarioExistente, IOException {
        Usuario usuarioEncontrado = servicioLoginDao.buscarUsuario(usuario.getEmail());
        if (usuarioEncontrado != null) {
            throw new UsuarioExistente();
        }

        String token = generarToken();

        usuario.setTokenValidacion(token);

        usuario.setImagenDePerfil(Base64.getEncoder().encode(imagenDePerfil.getBytes()));

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        servicioLoginDao.guardar(usuario);

        enviarCorreoValidacion(usuario.getEmail(), token);
    }

    public String generarToken() {
        String caracteresValidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder token = new StringBuilder(32);

        for (int i = 0; i < 32; i++) {
            int indice = secureRandom.nextInt(caracteresValidos.length());
            token.append(caracteresValidos.charAt(indice));
        }

        return token.toString();
    }

    @Override
    public void actualizarToken(Usuario usuario) throws IOException {
        String nuevoToken = generarToken();
        usuario.setTokenValidacion(nuevoToken);
        servicioLoginDao.actualizar(usuario);
        enviarCorreoValidacion(usuario.getEmail(), nuevoToken);
    }

    @Override
    public void enviarMailConInstruccion(String email) throws IOException {
        String token = generarToken();
        Usuario usuario = repositorioUsuario.buscarUsuario(email);

        if (usuario != null) {
            usuario.setTokenResetPassword(token);
        }

        servicioEmail.enviarMailInstruccion(email, token);
    }

    @Override
    public Boolean verificarTokenPassword(String tokenPassword) {
        Usuario usuario = repositorioUsuario.buscarPorTokenPassword(tokenPassword);

        if (usuario != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void modificarPassword(String password, String token) {
        Usuario usuario = repositorioUsuario.buscarPorTokenPassword(token);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setTokenResetPassword(null);
        repositorioUsuario.guardar(usuario);
    }


    private void enviarCorreoValidacion(String destinatario, String token) throws IOException {
        servicioEmail.enviarMailRegistro(destinatario, token);
    }

    @Override
    public void validarCorreo(String token) {
        verificarYValidarCorreo(token);
    }

    private void verificarYValidarCorreo(String token) {

        Usuario usuario = repositorioUsuario.buscarPorTokenValidacion(token);

        if (usuario != null) {
            if (!usuario.isEmailValidado()) {
                usuario.setEmailValidado(true);
                repositorioUsuario.actualizar(usuario);
                //servicioEmail.enviarCorreoConfirmacion(usuario.getEmail(), "¡Correo validado con éxito!");
            } else {
                throw new TokenInvalidoException("El correo ya ha sido validado anteriormente.");
            }
        } else {
            throw new TokenInvalidoException("Token de validación no válido");
        }
    }


}


