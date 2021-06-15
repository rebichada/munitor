package com.munnitorbackend.DTO;

import com.sun.istack.NotNull;

import java.io.Serializable;

public class VacunaDTO implements Serializable {
    private Long id;
    private String nombre;
    private String tipo;
    private String descripcion;

    public VacunaDTO(){
        super();
    }

    public VacunaDTO(Long id, String nombre, String tipo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
