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

    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Empleado empleado;

    @JoinColumn(name ="id_tambo", referencedColumnName = "id_tambo")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Tambo tambo;

    @JoinColumn(name ="id_vacuna", referencedColumnName = "id_vacuna")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Nullable
    private Vacuna vacuna;

    @JoinColumn(name ="id_direccion", referencedColumnName = "id_direccion")
    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Direccion direccion;

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

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Tambo getTambo() {
        return tambo;
    }

    public void setTambo(Tambo tambo) {
        this.tambo = tambo;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
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
