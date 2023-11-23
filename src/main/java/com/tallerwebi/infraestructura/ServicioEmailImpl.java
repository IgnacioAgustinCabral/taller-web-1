package com.tallerwebi.infraestructura;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
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

    public void enviarMailRegistro(String toMail, String cuerpoCorreo) throws IOException {

        SendGrid sendGrid = new SendGrid(API_KEY);
        Email from = new Email(FROM_EMAIL_ADDRESS);
        String subject = "Registro TravelAndo";
        Email to = new Email(toMail);
        Content content = new Content("text/plain", cuerpoCorreo);
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