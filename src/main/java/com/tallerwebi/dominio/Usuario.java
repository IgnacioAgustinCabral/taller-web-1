package com.tallerwebi.dominio;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;
    private Boolean emailValidado = false;
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Pattern(regexp = ".*[A-Z].*", message = "La contraseña debe contener al menos una letra mayúscula")
    private String password;
    private String rol;
    private Boolean activo = false;
    @NotBlank(message = "El campo Nombre es obligatorio")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Nombre debe contener solo letras")
    private String nombre;
    @NotBlank(message = "El campo Apellido es obligatorio")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Apellido debe contener solo letras")
    private  String apellido;
    @NotBlank(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "^[0-9]{1,9}$", message = "El DNI debe contener solo números y como máximo 9 dígitos")
    private String dni;
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    private String fecha_nac;
    @Digits(integer = 3, fraction = 0, message = "El código de área debe contener solo dígitos")
    private Integer cod_area;
    @NotNull(message = "El teléfono no puede estar vacío")
    @Digits(integer = 10, fraction = 0, message = "El teléfono debe contener solo 10 dígitos")
    private Long telefono;
    private String tokenValidacion;
    @Lob
    private byte[] imagenDePerfil;
    private String tokenResetPassword;
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy = "listaPasajeros", fetch = FetchType.EAGER)
    private Set<Viaje> viajes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioDestino", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comentario> comentariosRecibidos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioOrigen", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comentario> comentariosRealizados = new ArrayList<>();

    //TODO: constructor de pruebas.
    public Usuario(){};

    public Usuario(String nombre, String apellido, String dni, String fecha_nac, Integer cod_area, Long telefono, String email, String password, String rol, Boolean activo, byte[] imagenDePerfil){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
        this.cod_area = cod_area;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;
        this.password= password;
        this.activo = activo;
        this.imagenDePerfil = imagenDePerfil;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean activo() {
        return activo;
    }

    public void activar() {
        activo = true;
    }

    public Integer getCod_area() {
        return cod_area;
    }

    public String getDni() {
        return dni;
    }

    public Long getTelefono() {
        return telefono;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setCod_area(Integer cod_area) {
        this.cod_area = cod_area;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getTokenValidacion() {
        return tokenValidacion;
    }

    public void setTokenValidacion(String tokenValidacion) {
        this.tokenValidacion = tokenValidacion;
    }

    public Boolean isEmailValidado() {
        return emailValidado;
    }

    public void setEmailValidado(Boolean emailValidado) {
        this.emailValidado = emailValidado;
    }

    public Set<Viaje> getListaViajes() {
        return viajes;
    }

    public List<Comentario> getComentariosRecibidos() {
        return comentariosRecibidos;
    }

    public void setComentariosRecibidos(List<Comentario> comentariosRecibidos) {
        this.comentariosRecibidos = comentariosRecibidos;
    }

    public List<Comentario> getComentariosRealizados() {
        return comentariosRealizados;
    }

    public void setComentariosRealizados(List<Comentario> comentariosRealizados) {
        this.comentariosRealizados = comentariosRealizados;
    }

    public void setListaViajes(Set<Viaje> listaViajes) {
        this.viajes = listaViajes;
    }

    public byte[] getImagenDePerfil() {
        return imagenDePerfil;
    }

    public void setImagenDePerfil(byte[] imagenDePerfil) {
        this.imagenDePerfil = imagenDePerfil;
    }

    public String getTokenResetPassword() {
        return tokenResetPassword;
    }

    public void setTokenResetPassword(String tokenResetPassword) {
        this.tokenResetPassword = tokenResetPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password) && Objects.equals(dni, usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, dni);
    }
}
