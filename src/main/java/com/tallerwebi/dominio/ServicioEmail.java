package com.tallerwebi.dominio;

import javax.mail.MessagingException;

public interface ServicioEmail {

    void enviarCorreo(String destinatario, String Asunto, String cuerpo) throws MessagingException;
}
