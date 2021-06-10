package com.munnitorbackend.DTO;

import java.io.Serializable;

public class UserNewDTO implements Serializable {
    private String email;
    private String password;
    private String rol;
    private String nombreUsuario;

    public UserNewDTO(){
        super();
    }

    public UserNewDTO(String email, String password, String rol, String nombreUsuario) {
        super();
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.nombreUsuario = nombreUsuario;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
