package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Boolean emailValidado = false;
    private String password;
    private String rol;
    private Boolean activo = false;
    private String nombre;
    private  String apellido;
    private Long dni;
    private String fecha_nac;
    private Integer cod_area;
    private Long telefono;
    private String tokenValidacion;
    @Lob
    private byte[] imagenDePerfil;
    private String tokenResetPassword;
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy = "listaPasajeros", fetch = FetchType.EAGER)
    private Set<Viaje> viajes = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_comentario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_comentario")
    )
    private Set<Comentario> comentarios;


    //TODO: constructor de pruebas.
    public Usuario(){};

    public Usuario(String nombre, String apellido, Long dni, String fecha_nac, Integer cod_area, Long telefono, String email, String password, String rol, Boolean activo, byte[] imagenDePerfil){
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

    public Long getDni() {
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

    public void setDni(Long dni) {
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
