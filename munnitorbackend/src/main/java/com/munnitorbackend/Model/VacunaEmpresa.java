package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "vacunas_empresas")
public class VacunaEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_vacunas_empresas")
    private Long idVacunasEmpresas;

    @JoinColumn(name ="fk_id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Empresa empresa;

    @JoinColumn(name ="fk_id_vacuna", referencedColumnName = "id_vacuna")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Vacuna vacuna;

    @Column(name = "cantidad_vacunas")
    @NotNull
    private Integer cantidadVacunas;

    public VacunaEmpresa(Long idVacunasEmpresas, Empresa empresa, Vacuna vacuna, Integer cantidadVacunas) {
        this.idVacunasEmpresas = idVacunasEmpresas;
        this.empresa = empresa;
        this.vacuna = vacuna;
        this.cantidadVacunas = cantidadVacunas;
    }

    public VacunaEmpresa(Empresa empresa, Vacuna vacuna, Integer cantidadVacunas) {
        this.empresa = empresa;
        this.vacuna = vacuna;
        this.cantidadVacunas = cantidadVacunas;
    }

    public VacunaEmpresa(){
        super();
    }

    public Long getIdVacunasEmpresas() {
        return idVacunasEmpresas;
    }

    public void setIdVacunasEmpresas(Long idVacunasEmpresas) {
        this.idVacunasEmpresas = idVacunasEmpresas;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }

    public Integer getCantidad() {
        return cantidadVacunas;
    }

    public void setCantidad(Integer cantidadVacunas) {
        this.cantidadVacunas = cantidadVacunas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
