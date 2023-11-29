package com.tallerwebi.dominio;

import java.io.IOException;

public interface ServicioEmail {


    void enviarMailRegistro(String destinatario, String cuerpoCorreo) throws IOException;
    void enviarRespuestaRechazada(String destinatario , String motivo, String nombreCreador) throws IOException;

    void enviarSolicitudUnirseViaje(String emailCreadorViaje, String usuarioInteresado, Long idUsuarioInteresado, String linkAceptar, String linkRechazar) throws IOException;

    public void enviarRespuestaAceptada(String toMail, String creadorViaje, Long idViaje, String telefono, String emailCreador) throws IOException;

    void enviarMailInstruccion(String email, String token) throws IOException;
}
