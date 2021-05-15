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

    @JoinColumn(name = "id_caravana", referencedColumnName = "id_ganado")
    @OneToOne
    @OnDelete(action= OnDeleteAction.CASCADE)
    @Nullable
    private Caravana caravana;

    @JoinColumn(name="id_tambo", referencedColumnName = "id_tambo")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Tambo tambo;

    @JoinColumn(name = "id_vacuna", referencedColumnName = "id_vacuna")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Nullable
    private Vacuna vacuna;

    

    @Column(length = 10, name = "cantidad_de_pasos")
    private int pasos;

    @Column(name = "bool_comio")
    private boolean comio;

    private double temperatura;

    @Column(nullable = false, length = 1)
    private char sexo;

    @Column(name = "cantidad_servidas", length = 1)
    private int cantidadServidas;

    @Column(name = "cantidad_comio", length = 2)
    private int cantidadComio;

    private  double peso;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Column(name = "fecha_de_nacimiento")
    private Date fechaDeNacimiento;

    public Ganado() {
        super();
    }

    public Ganado(Long id, int pasos, boolean comio, double temperatura, char sexo, int cantidadServidas, int cantidadComio, double peso, Date fechaDeNacimiento) {
        this.id = id;
        this.pasos = pasos;
        this.comio = comio;
        this.temperatura = temperatura;
        this.sexo = sexo;
        this.cantidadServidas = cantidadServidas;
        this.cantidadComio = cantidadComio;
        this.peso = peso;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public boolean isComio() {
        return comio;
    }

    public void setComio(boolean comio) {
        this.comio = comio;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getCantidadServidas() {
        return cantidadServidas;
    }

    public void setCantidadServidas(int cantidadServidas) {
        this.cantidadServidas = cantidadServidas;
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

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
}
