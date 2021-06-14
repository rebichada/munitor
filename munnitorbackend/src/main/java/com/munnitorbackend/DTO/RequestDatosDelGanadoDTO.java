package com.munnitorbackend.DTO;

import java.io.Serializable;

public class RequestDatosDelGanadoDTO implements Serializable {
    private Long id;
    private Long idGanado;
    private String cuig;
    private Double temperatura;
    private int pasos;

    public RequestDatosDelGanadoDTO(){
        super();
    }

    public RequestDatosDelGanadoDTO(Long id, Long idGanado, String cuig, Double temperatura, int pasos) {
        this.id=id;
        this.idGanado = idGanado;
        this.cuig = cuig;
        this.temperatura = temperatura;
        this.pasos=pasos;
    }

    public String getCuig() {
        return cuig;
    }

    public void setCuig(String cuig) {
        this.cuig = cuig;
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

    public Long getIdGanado() {
        return idGanado;
    }

    public void setIdGanado(Long idGanado) {
        this.idGanado = idGanado;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

}
