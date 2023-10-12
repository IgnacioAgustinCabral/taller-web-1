package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String avatar;
    private String rol;
    private Boolean activo = false;
    private String nombre;
    private  String apellido;
    private Long dni;
    private String fecha_nac;
    private Integer cod_area;
    private Long telefono;


    //TODO: constructor de pruebas.
    public Usuario(){};

    public Usuario(Long id, String nombre, String apellido, Long dni, String fecha_nac, Integer cod_area, Long telefono, String email, String password, Integer calificacion, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
        this.cod_area = cod_area;
        this.telefono = telefono;
        this.email = email;
        this.password= password;
        this.activo = activo;
    }

    public Usuario(String nombre, String apellido, Long dni, String fecha_nac, Integer cod_area, Long telefono, String email, String password,String avatar, String rol, Boolean activo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
        this.cod_area = cod_area;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;
        this.password= password;
        this.avatar = avatar;
        this.activo = activo;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
}
