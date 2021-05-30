package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ganado")
public class Ganado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ganado")
    private Long id;

    @JoinColumn(name = "id_caravana", referencedColumnName = "id_caravana", unique = true)
    @OneToOne
    @OnDelete(action= OnDeleteAction.CASCADE)
    @Nullable
    private Caravana caravana;

    @JoinColumn(name="id_tambo", referencedColumnName = "id_tambo", unique = true)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Tambo tambo;

    @Column(nullable = false, length = 1)
    private char sexo;

    @Column(name = "cantidad_servidas", length = 1)
    private int cantidadServidas;

    @NotNull
    private String descripcion;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fecha_de_nacimiento")
    private Date fechaDeNacimiento;

    public Ganado() {
        super();
    }

    public Ganado(Long id, char sexo, String descripcion, Date fechaDeNacimiento,int cantidadServidas) {
        this.id = id;
        this.sexo = sexo;
        this.cantidadServidas = cantidadServidas;
        this.descripcion = descripcion;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Ganado(Long id, Caravana caravana, Tambo tambo,int cantidadServidas, char sexo, String descripcion, Date fechaDeNacimiento) {
        this.id = id;
        this.caravana = caravana;
        this.tambo = tambo;
        this.sexo = sexo;
        this.descripcion = descripcion;
        this.cantidadServidas = cantidadServidas;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Caravana getCaravana() {
        return caravana;
    }

    public void setCaravana(Caravana caravana) {
        this.caravana = caravana;
    }

    public Tambo getTambo() {
        return tambo;
    }

    public void setTambo(Tambo tambo) {
        this.tambo = tambo;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getCantidadServidas() {
        return cantidadServidas;
    }

    public void setCantidadServidas(int cantidadServidas) {
        this.cantidadServidas = cantidadServidas;
    }
}
