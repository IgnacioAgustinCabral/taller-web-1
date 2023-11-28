package com.tallerwebi.dominio;

import java.io.IOException;

public interface ServicioEmail {


    void enviarMailRegistro(String destinatario, String cuerpoCorreo) throws IOException;
    void enviarRespuestaRechazada(String destinatario , String motivo, String nombreCreador) throws IOException;
}
