package com.munnitorbackend.Model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="empresas")
public class Empresa {

    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    @Column(name="id_empresa")
    private Long id;

    @Column(name = "razon_social")
    @NotNull
    private String razonSocial;

    @NotNull
    @Column(length = 15)
    private String cuit;

    private String email;

    @Column(length = 30)
    private String telefono;

    public Empresa() {
        super();
    }

    public Empresa(Long id, String razonSocial, String cuit, String email, String telefono) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.email = email;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
