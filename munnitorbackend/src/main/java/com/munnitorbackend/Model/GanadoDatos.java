package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ganado_datos")
public class GanadoDatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ganado_datos")
    private Long id;

    @JoinColumn(name="id_ganado", referencedColumnName = "id_ganado")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Ganado ganado;

    private Double temperatura;

    @Column(length = 10, name = "cantidad_de_pasos")
    private int pasos;

    @Column(name = "cantidad_comio", length = 2)
    private int cantidadComio;

    private  double peso;

    @Column(name = "bool_comio")
    private boolean comio;

    @Column(name = "bool_movimiento")
    private boolean movimiento;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "fecha_de_registro")
    private Date fechaDeRegistro;

    public GanadoDatos(){
        super();
    }

    public GanadoDatos(Long id, Ganado ganado,Date fechaDeRegistro, Double temperatura, int pasos, int cantidadComio, double peso, boolean comio, boolean movimiento) {
        this.id = id;
        this.ganado = ganado;
        this.temperatura = temperatura;
        this.pasos = pasos;
        this.cantidadComio = cantidadComio;
        this.peso = peso;
        this.comio = comio;
        this.fechaDeRegistro=fechaDeRegistro;
        this.movimiento=movimiento;
    }

    public GanadoDatos(Long id, Double temperatura,Date fechaDeRegistro, int pasos, int cantidadComio, double peso, boolean comio, boolean movimiento) {
        this.id = id;
        this.temperatura = temperatura;
        this.pasos = pasos;
        this.cantidadComio = cantidadComio;
        this.peso = peso;
        this.comio = comio;
        this.fechaDeRegistro=fechaDeRegistro;
        this.movimiento=movimiento;
    }

    public GanadoDatos(Ganado ganado, Double temperatura, int pasos, int cantidadComio, double peso, boolean comio, Date fechaDeRegistro, boolean movimiento) {
        this.ganado = ganado;
        this.temperatura = temperatura;
        this.pasos = pasos;
        this.cantidadComio = cantidadComio;
        this.peso = peso;
        this.comio = comio;
        this.fechaDeRegistro = fechaDeRegistro;
        this.movimiento=movimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(Date fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    public Ganado getGanado() {
        return ganado;
    }

    public void setGanado(Ganado ganado) {
        this.ganado = ganado;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public int getCantidadComio() {
        return cantidadComio;
    }

    public void setCantidadComio(int cantidadComio) {
        this.cantidadComio = cantidadComio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isComio() {
        return comio;
    }

    public void setComio(boolean comio) {
        this.comio = comio;
    }

}
