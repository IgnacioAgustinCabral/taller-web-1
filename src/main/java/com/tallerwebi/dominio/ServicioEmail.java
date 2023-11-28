package com.tallerwebi.dominio;

import java.io.IOException;

public interface ServicioEmail {


    void enviarMailRegistro(String destinatario, String cuerpoCorreo) throws IOException;

    void enviarSolicitudUnirseViaje(String emailCreadorViaje, String usuarioInteresado, String linkAceptar, String linkRechazar) throws IOException;

    void enviarRespuestaAceptada(String emailUsuario, String creadorViaje);
}
