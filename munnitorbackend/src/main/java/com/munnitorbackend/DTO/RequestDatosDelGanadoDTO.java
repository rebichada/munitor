package com.munnitorbackend.DTO;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class RequestDatosDelGanadoDTO implements Serializable {
    private Long id;
    private Long idGanado;
    private Double temperatura;
    private boolean movimiento;

    public RequestDatosDelGanadoDTO(){
        super();
    }

    public RequestDatosDelGanadoDTO(Long id,Long idGanado, Double temperatura, boolean movimiento) {
        this.id=id;
        this.idGanado = idGanado;
        this.temperatura = temperatura;
        this.movimiento=movimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
