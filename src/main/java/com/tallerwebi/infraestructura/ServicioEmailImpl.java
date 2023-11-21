package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.ServicioEmail;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class ServicioEmailImpl implements ServicioEmail {
    @Override
    public void enviarCorreo(String destinatario, String asunto, String cuerpo) throws MessagingException {
        // Configuración para el servidor de correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Autenticación
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("travelando.unlam@gmail.com", "Travel1234");
            }
        };

        // Crear sesión
        Session session = Session.getInstance(properties, authenticator);

        // Crear mensaje de correo
        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress("travelando.unlam@gmail.com"));
        mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensaje.setSubject(asunto);
        mensaje.setText(cuerpo);

        // Enviar correo
        Transport.send(mensaje);
    }
}
