package com.munnitorbackend.DTO;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class RequestDatosDelGanadoDTO implements Serializable {
    private Long idGanado;
    private Double temperatura;
    private int cantidadPasos;

    public RequestDatosDelGanadoDTO(){
        super();
    }

    public RequestDatosDelGanadoDTO(Long idGanado, Double temperatura, int cantidadPasos) {
        this.idGanado = idGanado;
        this.temperatura = temperatura;
        this.cantidadPasos = cantidadPasos;
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

    public int getCantidadPasos() {
        return cantidadPasos;
    }

    public void setCantidadPasos(int cantidadPasos) {
        this.cantidadPasos = cantidadPasos;
    }

}
