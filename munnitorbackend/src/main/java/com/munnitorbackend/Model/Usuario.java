package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "nombre_usuario",nullable = false, unique = true)
    private String nombreUsuario;

    @NotNull
    private String password;

    private byte rol;

    private boolean activo;

    public Usuario() {
        super();
    }

    public Usuario(Long id, String email, String nombreUsuario, String password, byte rol, boolean activo) {
        this.id = id;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }

    public Usuario(String email, String nombreUsuario, String password, byte rol, boolean activo) {
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }

    public byte getRol() {
        return rol;
    }

    public void setRol(byte rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
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


    public List<GrantedAuthority> buildgrante(byte rol){
        // el byte puede contener valores de -128 a 127 (es decir puedo tener 365 roles)
        //El tipo de datos byte es un entero de 8 bits de complemento a dos
        //rol 0 = no existe
        //rol 1 = lector
        //rol 2 = LECTOR Y USUARIO
        //rol 3 =LECTOR, USUARIO y ADMINISTRADOR
        //rol 4 =LECTOR, USUARIO, ADMINISTRADOR y ROOT (control de todo el sistema)
        List<GrantedAuthority> auths = new ArrayList<>();
        String[] roles = {"ROLE_LECTOR","ROLE_USUARIO","ROLE_ADMINISTRADOR","ROLE_ROOT"};
        switch (rol) {
            case 2:
                auths.add(new SimpleGrantedAuthority(roles[1]));
                break;
            case 3:
                auths.add(new SimpleGrantedAuthority(roles[2]));
                break;
            case 4:
                auths.add(new SimpleGrantedAuthority(roles[3]));
                break;
            default:
                auths.add(new SimpleGrantedAuthority(roles[0]));
                break;
        }

        /**for(int i=0; i<rol; i++) {
           auths.add(new SimpleGrantedAuthority(roles[i]));
        }*/
        return auths;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return buildgrante(getRol());
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getNombreUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActivo();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActivo();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActivo();
    }

    @Override
    public boolean isEnabled() {
        return isActivo();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
