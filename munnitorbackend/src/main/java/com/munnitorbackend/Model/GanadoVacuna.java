package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ganado_vacunas")
public class GanadoVacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ganado_vacuna")
    private Long id;

    @JoinColumn(name = "id_ganado", referencedColumnName = "id_ganado")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Ganado ganado;

    @JoinColumn(name = "id_vacuna", referencedColumnName = "id_vacuna")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Vacuna vacuna;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Column(name = "fecha_de_vacunacion")
    private Date fechaDeVacunacion;

    public GanadoVacuna(Long id, Ganado ganado, Vacuna vacuna, Date fechaDeVacunacion) {
        this.id = id;
        this.ganado = ganado;
        this.vacuna = vacuna;
        this.fechaDeVacunacion = fechaDeVacunacion;
    }

    public GanadoVacuna(Ganado ganado,Vacuna vacuna, Date fechaDeVacunacion) {
        this.ganado = ganado;
        this.vacuna = vacuna;
        this.fechaDeVacunacion = fechaDeVacunacion;
    }

    public GanadoVacuna(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ganado getGanado() {
        return ganado;
    }

    public void setGanado(Ganado ganado) {
        this.ganado = ganado;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }

    public Date getFechaDeVacunacion() {
        return fechaDeVacunacion;
    }

    public void setFechaDeVacunacion(Date fechaDeVacunacion) {
        this.fechaDeVacunacion = fechaDeVacunacion;
    }
}
