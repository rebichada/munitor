package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
@Table(name="vacunas")
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacunas")
    private Long id;

    

    @Column
    @NotNull
    private String nombre;

    @Column(length = 45)
    @NotNull
    private String tipo;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
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
