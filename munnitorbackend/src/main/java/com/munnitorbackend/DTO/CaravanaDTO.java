package com.munnitorbackend.DTO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaravanaDTO implements Serializable {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd-MM-yyyy");
    private String id;
    private String idEmpresa;
    private String cuig;
    private char colorCaravana;
    private char digitoVerificador;
    private String idInternacional;
    private String numeroManejo;
    private String numeroRenspa;
    private String marcaFechaProduccion;
    private String fechaImpresion;
    private String numeroImpresor;
    private String rangoImpresor;

    public CaravanaDTO(String id,String idEmpresa,String cuig, char colorCaravana, String idInternacional, String numeroManejo, String numeroRenspa, String marcaFechaProduccion, String fechaImpresion, char digitoVerificador, String numeroImpresor, String rangoImpresor) {
        this.id=id;
        this.idEmpresa=idEmpresa;
        this.cuig = cuig;
        this.colorCaravana = colorCaravana;
        this.idInternacional = idInternacional;
        this.numeroManejo = numeroManejo;
        this.numeroRenspa = numeroRenspa;
        this.marcaFechaProduccion = marcaFechaProduccion;
        this.fechaImpresion = fechaImpresion;
        this.digitoVerificador=digitoVerificador;
        this.numeroImpresor = numeroImpresor;
        this.rangoImpresor = rangoImpresor;
    }

    public CaravanaDTO(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public char getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(char digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public String getFechaImpresion() {
        return fechaImpresion;
    }

    public void setFechaImpresion(String fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public Date getFechaImpresionInDateConverted() throws ParseException {
        return dateFormat.parse(this.fechaImpresion);
    }

    public void setFechaImpresionInDate(Date date) {
        this.fechaImpresion = dateFormat.format(date);
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
