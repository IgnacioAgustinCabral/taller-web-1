package com.tallerwebi.infraestructura;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import com.tallerwebi.dominio.ServicioEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("servicioEmail")
public class ServicioEmailImpl implements ServicioEmail {

    private String FROM_EMAIL_ADDRESS = "travelando.unlam@gmail.com";
    @Value("${SENDGRID_API_KEY}")
    private String API_KEY;

    public void enviarMailRegistro(String toMail, String token) throws IOException {

        SendGrid sendGrid = new SendGrid(API_KEY);
        Email from = new Email(FROM_EMAIL_ADDRESS);
        String subject = "Registro TravelAndo - Verificación de Correo";
        Email to = new Email(toMail);

        // Contenido del correo en formato HTML
        String cuerpoCorreo = "<html><body>" +
                "<p>Gracias por Registrarte en TravelAndo!</p>" +
                "<p>Por favor, verifica tu dirección de email para poder comenzar a planificar tus próximos viajes por Argentina.</p>" +
                "<p><strong>Buen Viaje!</strong></p>" +
                "<a href='http://localhost:8080/spring/validar-email?token=" + token + "' style='background-color: #bb1524; color: #ffffff; padding: 10px 20px; text-decoration: none; display: inline-block; border-radius: 5px;'>Verificar tu Email</a>" +
                "<p><strong>Una vez verificado:</strong></p>" +
                "<ol>" +
                "<li>Puedes empezar a Crear tus viajes para que otros se unan.</li>" +
                "<li>Puedes Unirte a viajes creados por otros Usuarios.</li>" +
                "<li>Acceder a Descuentos Exclusivos de viajeros TravelAndo.</li>" +
                "</ol>" +
                "<p><strong>¡Y Mucho MÁS!</strong></p>" +
                "<p>Necesitas Ayuda? Nuestro Equipo de Asesores siempre está dispuesto a darte una mano!</p>" +
                "<a href='mailto:travelando.unlam@gmail.com' style='background-color: #004376; color: #ffffff; padding: 10px 20px; text-decoration: none; display: inline-block; border-radius: 5px;'>Contactar Soporte</a>" +
                "</body></html>";

        Content content = new Content("text/html", cuerpoCorreo);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void enviarMailInstruccion(String email, String token) throws IOException {
        SendGrid sendGrid = new SendGrid(API_KEY);
        Email from = new Email(FROM_EMAIL_ADDRESS);
        String subject = "TravelAndo - Restablecer Contraseña";
        Email to = new Email(email);

        String cuerpoCorreo = "<html><body>" +
                "<h1><strong>Restablecer Contraseña</strong></h1>" +
                "<a href='http://localhost:8080/spring/verificar-token-password?token=" + token + "' style='background-color: #bb1524; color: #ffffff; padding: 10px 20px; text-decoration: none; display: inline-block; border-radius: 5px;'>Restablecer contraseña</a>" +
                "<p>Se ha solicitado un cambio de contraseña para tu cuenta, si fuiste vos porfavor entrá al link para modificar tu contraseña sino ignora este e-mail</p>" +
                "<p>Necesitas Ayuda? Nuestro Equipo de Asesores siempre está dispuesto a darte una mano!</p>" +
                "<a href='mailto:travelando.unlam@gmail.com' style='background-color: #004376; color: #ffffff; padding: 10px 20px; text-decoration: none; display: inline-block; border-radius: 5px;'>Contactar Soporte</a>" +
                "</body></html>";

        Content content = new Content("text/html", cuerpoCorreo);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();

        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException ex){
            throw new IOException();
        }

    }
}
