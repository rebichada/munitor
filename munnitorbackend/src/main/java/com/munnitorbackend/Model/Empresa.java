package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="empresas")
public class Empresa {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    @Column(name="id_empresa")
    private Long id;

    @JoinColumn(name ="id_direccion", referencedColumnName = "id_direccion")
    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Direccion direccion;

    @Column(name = "razon_social")
    @NotNull
    private String razonSocial;

    @NotNull
    private String cuit;

    private String email;

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

    public Empresa(Long id, Direccion direccion, String razonSocial, String cuit, String email, String telefono) {
        this.id = id;
        this.direccion = direccion;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.email = email;
        this.telefono = telefono;
    }

    public Empresa(Direccion direccion, String razonSocial, String cuit, String email, String telefono) {
        this.direccion = direccion;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.email = email;
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
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
