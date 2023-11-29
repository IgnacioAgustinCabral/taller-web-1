package com.tallerwebi.infraestructura;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.tallerwebi.dominio.ServicioEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service("servicioEmail")
@Transactional
public class ServicioEmailImpl implements ServicioEmail {

    private String FROM_EMAIL_ADDRESS = "travelando.unlam@gmail.com";
    @Value("${SENDGRID_API_KEY}")
    private String API_KEY;
    String imgUrl = "https://i.imgur.com/J7NByxf.png";


    public void enviarMailRegistro(String toMail, String token) throws IOException {

        SendGrid sendGrid = new SendGrid(API_KEY);
        Email from = new Email(FROM_EMAIL_ADDRESS);
        String subject = "Registro TravelAndo - Verificación de Correo";
        Email to = new Email(toMail);

        // Contenido del correo en formato HTML
        String cuerpoCorreo = "<html><body>" +
                "<img width=\"93\" height=\"73\" src=\"" + imgUrl + "\" alt=\"Descripción de la imagen\">" +
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
                "<img width=\"93\" height=\"73\" src=\"" + imgUrl + "\" alt=\"Descripción de la imagen\">" +
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
    @Override
    public void enviarSolicitudUnirseViaje(String toMail, String usuarioInteresado, Long idUsuarioInteresado, String linkAceptar, String linkRechazar) throws IOException {
        SendGrid sendGrid = new SendGrid(API_KEY);
        Email from = new Email(FROM_EMAIL_ADDRESS);
        String subject = "Solicitud para unirse a tu viaje";
        Email to = new Email(toMail);

        String cuerpoCorreo = "<html><body>" +
                "<img width=\"93\" height=\"73\" src=\"" + imgUrl + "\" alt=\"Descripción de la imagen\">" +
                "<p>¡Hola!</p>" +
                "<p>Tienes una solicitud para unirte al viaje.</p>" +
                "<p>Nombre del usuario interesado: " + usuarioInteresado + "</p>" +
                "<p><a href=\"http://localhost:8080/spring/usuario?idUsuario=" + idUsuarioInteresado
                + "\" style='background-color: green; color: white; padding: 10px 20px; text-decoration: none; display: inline-block; border-radius: 5px;'>Ver Perfil</a></p>"
                + "<p><a href=\"" + linkAceptar + "\" style='background-color: #0060aa; color: white; padding: 10px 20px; text-decoration: none; display: inline-block; border-radius: 5px;'>Aceptar</a>"
                + "<a href=\"" + linkRechazar + "\" style='background-color: #ee1b2e; color: white; padding: 10px 20px; text-decoration: none; display: inline-block; border-radius: 5px; margin-left: 5px;'>Rechazar</a></p>"
                + "<p>Gracias,</p>"
                + "<p>TravelAndo Team</p>" +
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
    public void enviarRespuestaRechazada(String toMail, String motivo, String nombreCreador) throws IOException {
        SendGrid sendGrid = new SendGrid(API_KEY);
        Email from = new Email(FROM_EMAIL_ADDRESS);
        String subject = "Respuesta a tu solicitud para unirse al Viaje";
        Email to = new Email(toMail);

        String cuerpoCorreo = "<html><body>" +
                "<img width=\"93\" height=\"73\" src=\"" + imgUrl + "\" alt=\"Descripción de la imagen\">" +
                "<p>Lamentamos informarte que tu solicitud para unirte al viaje ha sido rechazada.</p>" +
                "<p>El creador del viaje, " + nombreCreador + ", ha proporcionado el siguiente motivo:</p>" +
                "<p style='background-color: #ee1b2e; color: white; padding: 10px; border-radius: 5px;'>" + motivo + "</p>" +
                "<p>Gracias por tu interés.</p>" +
                "<p>TravelAndo Team</p>" +
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
    public void enviarRespuestaAceptada(String toMail, String creadorViaje, Long idViaje, String telefono, String emailCreador)
            throws IOException {

        SendGrid sendGrid = new SendGrid(API_KEY);
        Email from = new Email(FROM_EMAIL_ADDRESS);
        String subject = "Solicitud Aceptada para Unirse al Viaje";
        Email to = new Email(toMail);

        String enlaceViaje = "http://localhost:8080/spring/ver-viaje?id=" + idViaje;

        String cuerpoCorreo = "<html><body>" +
                "<img width=\"93\" height=\"73\" src=\"" + imgUrl + "\" alt=\"Descripción de la imagen\">" +
                "<p>¡Hola!</p>" +
                "<p>" + creadorViaje + " ha aceptado tu solicitud para unirte al viaje.</p>" +
                "<p>Puedes ver los detalles del viaje haciendo clic en el siguiente enlace:</p>" +
                "<a href='" + enlaceViaje + "' style='background-color: #0060aa; color: #ffffff; padding: 10px 20px; " +
                "text-decoration: none; display: inline-block; border-radius: 5px;'>Ver Viaje</a>" +
                "<br>" +
                "<h3>Datos de Contacto:</h3>" +
                "<table style='border-collapse: collapse; width: 100%;'>" +
                "<tr><td style='border: 1px solid #dddddd; text-align: left; padding: 8px;'><b>Nombre Completo</b></td>" +
                "<td style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>" + creadorViaje + "</td></tr>" +
                "<tr><td style='border: 1px solid #dddddd; text-align: left; padding: 8px;'><b>Email</b></td>" +
                "<td style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>" + emailCreador + "</td></tr>" +
                "<tr><td style='border: 1px solid #dddddd; text-align: left; padding: 8px;'><b>Teléfono</b></td>" +
                "<td style='border: 1px solid #dddddd; text-align: left; padding: 8px;'>" + telefono + "</td></tr>" +
                "</table>" +
                "<p>¡Esperamos que disfrutes del viaje!</p>" +
                "<p>TravelAndo Team</p>" +
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


}
