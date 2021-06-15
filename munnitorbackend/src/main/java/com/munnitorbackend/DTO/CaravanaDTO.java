package com.munnitorbackend.DTO;

import java.io.Serializable;
import java.util.Date;

public class CaravanaDTO implements Serializable {

    private String cuig;
    private char colorCaravana;
    private String idInternacional;
    private String numeroManejo;
    private String numeroRenspa;
    private String marcaFechaProduccion;
    private Date fechaImpresion;
    private String numeroImpresor;
    private String rangoImpresor;

    public CaravanaDTO(String cuig, char colorCaravana, String idInternacional, String numeroManejo, String numeroRenspa, String marcaFechaProduccion, Date fechaImpresion, String numeroImpresor, String rangoImpresor) {
        this.cuig = cuig;
        this.colorCaravana = colorCaravana;
        this.idInternacional = idInternacional;
        this.numeroManejo = numeroManejo;
        this.numeroRenspa = numeroRenspa;
        this.marcaFechaProduccion = marcaFechaProduccion;
        this.fechaImpresion = fechaImpresion;
        this.numeroImpresor = numeroImpresor;
        this.rangoImpresor = rangoImpresor;
    }

    public String getCuig() {
        return cuig;
    }

    public void setCuig(String cuig) {
        this.cuig = cuig;
    }

    public char getColorCaravana() {
        return colorCaravana;
    }

    public void setColorCaravana(char colorCaravana) {
        this.colorCaravana = colorCaravana;
    }

    public String getIdInternacional() {
        return idInternacional;
    }

    public void setIdInternacional(String idInternacional) {
        this.idInternacional = idInternacional;
    }

    public String getNumeroManejo() {
        return numeroManejo;
    }

    public void setNumeroManejo(String numeroManejo) {
        this.numeroManejo = numeroManejo;
    }

    public String getNumeroRenspa() {
        return numeroRenspa;
    }

    public void setNumeroRenspa(String numeroRenspa) {
        this.numeroRenspa = numeroRenspa;
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

    public String getNumeroImpresor() {
        return numeroImpresor;
    }

    public void setNumeroImpresor(String numeroImpresor) {
        this.numeroImpresor = numeroImpresor;
    }

    public String getRangoImpresor() {
        return rangoImpresor;
    }

    public void setRangoImpresor(String rangoImpresor) {
        this.rangoImpresor = rangoImpresor;
    }
}
