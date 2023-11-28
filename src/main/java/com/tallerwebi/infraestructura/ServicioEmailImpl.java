package com.tallerwebi.infraestructura;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import com.tallerwebi.dominio.ServicioEmail;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void enviarSolicitudUnirseViaje(String toMail, String usuarioInteresado, String linkAceptar, String linkRechazar) throws IOException {
        SendGrid sendGrid = new SendGrid(API_KEY);
        Email from = new Email(FROM_EMAIL_ADDRESS);
        String subject = "Solicitud para unirse a tu viaje";
        Email to = new Email(toMail);

        // Contenido del correo en formato HTML
        String cuerpoCorreo = "<html><body>" +
                "<p>Hola,</p>" +
                "<p>El usuario " + usuarioInteresado + " está interesado en unirse a tu viaje. Haz clic en los enlaces a continuación para responder:</p>" +
                "<p><a href='" + linkAceptar/*Unir a viaje*/ + "'>Aceptar Solicitud</a></p>" +
                "<p><a href='" + linkRechazar + "'>Rechazar Solicitud</a></p>" +
                "<p>Gracias,</p>" +
                "<p>TravelAndo</p>" +
                "</body></html>";

        Content content = new Content("text/html", cuerpoCorreo);
        Mail mail = new Mail(from, subject, to, content);

        // Resto del código para enviar el correo
    }

    public void enviarRespuestaRechazada(String toMail, String respuesta, String motivo) throws IOException {
        SendGrid sendGrid = new SendGrid(API_KEY);
        Email from = new Email(FROM_EMAIL_ADDRESS);
        String subject = "Respuesta a tu solicitud";
        Email to = new Email(toMail);

        // Construye el contenido del correo en función de la respuesta
        String cuerpoCorreo;
        if ("Aceptada".equals(respuesta)) {
            cuerpoCorreo = "<p>Tu solicitud ha sido aceptada. ¡Esperamos verte pronto en el viaje!</p>";
        } else {
            cuerpoCorreo = "<p>Tu solicitud ha sido rechazada.</p>";
            if (motivo != null && !motivo.isEmpty()) {
                cuerpoCorreo += "<p>Motivo: " + motivo + "</p>";
            }
        }

        cuerpoCorreo = "<html><body>" + cuerpoCorreo + "</body></html>";

        Content content = new Content("text/html", cuerpoCorreo);
        Mail mail = new Mail(from, subject, to, content);

    }

    public void enviarRespuestaAceptada(String toMail, String respuesta, String motivo) throws IOException{

    }

}
