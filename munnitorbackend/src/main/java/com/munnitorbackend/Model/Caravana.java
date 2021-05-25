package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "caravanas")
public class Caravana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caravana")
    private Long id;

    @JoinColumn(name ="id_empresa", referencedColumnName = "id_empresa")
    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Empresa empresa;

    @Column(name = "id_internacional",nullable = false,length = 5)
    private String idInternacional;

    @Column(length = 10,nullable = false)
    private String CUIG;

    @Column(name = "color_caranavana",length = 1,nullable = false)
    private char colorCaravana;

    @Column(name = "digito_verificador",length = 1,nullable = false)
    private char digitoVerificador;

    @Column(name = "numero_manejo",length = 10,nullable = false)
    private String numeroManejo;

    @Column(name = "numero_RENSPA",length = 45,nullable = false)
    private String numeroRENSPA;

    @Column(name = "marca_fecha_produccion",length = 45)
    private String marcaFechaProduccion;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Column(name = "fecha_impresion",nullable = false)
    private Date fechaImpresion;

    @Column(name = "numero_impresor",length = 10,nullable = false)
    private int numeroImpresor;

    @Column(name = "rango_impresor",length = 15,nullable = false)
    private String rangoImpresor;

    public Caravana() {
        super();
    }

    public Caravana(Long id, String idInternacional, String CUIG, char colorCaravana, char digitoVerificador, String numeroManejo, String numeroRENSPA, String marcaFechaProduccion, Date fechaImpresion, int numeroImpresor, String rangoImpresor) {
        this.id = id;
        this.idInternacional = idInternacional;
        this.CUIG = CUIG;
        this.colorCaravana = colorCaravana;
        this.digitoVerificador = digitoVerificador;
        this.numeroManejo = numeroManejo;
        this.numeroRENSPA = numeroRENSPA;
        this.marcaFechaProduccion = marcaFechaProduccion;
        this.fechaImpresion = fechaImpresion;
        this.numeroImpresor = numeroImpresor;
        this.rangoImpresor = rangoImpresor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdInternacional() {
        return idInternacional;
    }

    public void setIdInternacional(String idInternacional) {
        this.idInternacional = idInternacional;
    }

    public String getCUIG() {
        return CUIG;
    }

    public void setCUIG(String CUIG) {
        this.CUIG = CUIG;
    }

    public char getColorCaravana() {
        return colorCaravana;
    }

    public void setColorCaravana(char colorCaravana) {
        this.colorCaravana = colorCaravana;
    }

    public char getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(char digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public String getNumeroManejo() {
        return numeroManejo;
    }

    public void setNumeroManejo(String numeroManejo) {
        this.numeroManejo = numeroManejo;
    }

    public String getNumeroRENSPA() {
        return numeroRENSPA;
    }

    public void setNumeroRENSPA(String numeroRENSPA) {
        this.numeroRENSPA = numeroRENSPA;
    }

    public String getMarcaFechaProduccion() {
        return marcaFechaProduccion;
    }

    public void setMarcaFechaProduccion(String marcaFechaProduccion) {
        this.marcaFechaProduccion = marcaFechaProduccion;
    }

    public Date getFechaImpresion() {
        return fechaImpresion;
    }

    public void setFechaImpresion(Date fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public int getNumeroImpresor() {
        return numeroImpresor;
    }

    public void setNumeroImpresor(int numeroImpresor) {
        this.numeroImpresor = numeroImpresor;
    }

    public String getRangoImpresor() {
        return rangoImpresor;
    }

    public void setRangoImpresor(String rangoImpresor) {
        this.rangoImpresor = rangoImpresor;
    }
}
