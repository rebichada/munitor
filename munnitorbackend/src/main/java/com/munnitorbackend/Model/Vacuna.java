package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
@Table(name="vacunas")
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacuna")
    private Long id;

    @Column
    @NotNull
    private String nombre;

    @Column(length = 45)
    @NotNull
    private String tipo;

    @Column
    @NotNull
    private Integer cantidad;

    public Vacuna(Long id, String nombre, String tipo, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public Vacuna(){
        super();
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
