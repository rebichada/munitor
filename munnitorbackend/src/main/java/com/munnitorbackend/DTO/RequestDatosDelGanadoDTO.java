package com.munnitorbackend.DTO;

import java.io.Serializable;

public class RequestDatosDelGanadoDTO implements Serializable {
    private Long id;
    private Long idGanado;
    private Double temperatura;
    private String movimiento;

    public RequestDatosDelGanadoDTO(){
        super();
    }

    public RequestDatosDelGanadoDTO( Long id,Long idGanado, Double temperatura,String movimiento) {
        this.id=id;
        this.idGanado = idGanado;
        this.movimiento=movimiento;
        this.temperatura = temperatura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
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
