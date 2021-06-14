package com.munnitorbackend.DTO;

import java.io.Serializable;

public class RequestDatosDelGanadoDTO implements Serializable {
    private Long idGanado;
    private Double temperatura;
    private boolean movimiento;

    public RequestDatosDelGanadoDTO(){
        super();
    }

    public RequestDatosDelGanadoDTO(Long idGanado, Double temperatura,boolean movimiento) {
        this.idGanado = idGanado;
        this.movimiento=movimiento;
        this.temperatura = temperatura;
    }

    public boolean isMovimiento() {
        return movimiento;
    }

    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
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
