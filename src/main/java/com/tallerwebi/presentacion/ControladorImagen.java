package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

@Controller
public class ControladorImagen {
    private ServicioUsuario servicioUsuario;

    private ServicioCiudad servicioCiudad;

    private ServicioProvincia servicioProvincia;

    @Autowired
    public ControladorImagen(ServicioUsuario servicioUsuario, ServicioCiudad servicioCiudad, ServicioProvincia servicioProvincia) {
        this.servicioUsuario = servicioUsuario;
        this.servicioCiudad = servicioCiudad;
        this.servicioProvincia = servicioProvincia;
    }

    @RequestMapping("/usuario/imagen/{usuarioId}")
    public void usuarioImagen(@PathVariable Long usuarioId, HttpServletResponse response) {

        // Recuperar la entidad del usuario con la imagen BLOB desde la base de datos
        Usuario user = servicioUsuario.obtenerUsuarioPorId(usuarioId);

        // Configurar la respuesta HTTP para servir la imagen
        response.setContentType("image/jpg"); // Ajusta el tipo MIME según el tipo de imagen

        try {
            // Obtener un OutputStream para la respuesta y escribir la imagen
            byte[] imagenBytes = Base64.getDecoder().decode(user.getImagenDePerfil());
            OutputStream out = response.getOutputStream();
            out.write(imagenBytes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/destino/imagen/{id}")
    public void destinoImagen(@PathVariable Long id, HttpServletResponse response) {
        Ciudad ciudad = servicioCiudad.obtenerCiudadPorId(id);

        response.setContentType("image/jpg");

        try {
            OutputStream out = response.getOutputStream();
            InputStream imageInputStream = new ByteArrayInputStream(ciudad.getImagen());
            IOUtils.copy(imageInputStream, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/provincia/imagen/{id}")
    public void provinciaImagen(@PathVariable Long id, HttpServletResponse response) {
        Provincia provincia = servicioProvincia.obtenerProvinciaPorId(id);

        response.setContentType("image/jpg");

        try {

            OutputStream out = response.getOutputStream();
            InputStream imageInputStream = new ByteArrayInputStream(provincia.getImagen());
            IOUtils.copy(imageInputStream, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
