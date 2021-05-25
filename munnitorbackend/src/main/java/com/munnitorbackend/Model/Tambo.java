package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "tambos")
public class Tambo {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id_tambo")
    private Long id;

    @JoinColumn(name ="id_direccion", referencedColumnName = "id_direccion")
    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Direccion direccion;

    @Column(name = "nombre_tambo", nullable = false,length = 100)
    private String nombreTambo;

    @Column(length = 30)
    private String telefono;

    @Column(length = 50, nullable = false)
    private String nombreCuartel;

    public Tambo() {
        super();
    }

    public Tambo(Long id, String nombreTambo, String telefono, String nombreCuartel) {
        this.id = id;
        this.nombreTambo = nombreTambo;
        this.telefono = telefono;
        this.nombreCuartel = nombreCuartel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTambo() {
        return nombreTambo;
    }

    public void setNombreTambo(String nombreTambo) {
        this.nombreTambo = nombreTambo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCuartel() {
        return nombreCuartel;
    }

    public void setNombreCuartel(String nombreCuartel) {
        this.nombreCuartel = nombreCuartel;
    }
}
