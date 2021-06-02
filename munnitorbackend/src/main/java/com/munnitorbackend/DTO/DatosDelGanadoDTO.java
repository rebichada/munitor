package com.munnitorbackend.DTO;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class DatosDelGanadoDTO implements Serializable {
    private Long idGanado;
    private Long idCaravana;
    private char sexo;
    private Double temperatura;
    private int cantidadPasos;
    private String CUIG;

    public DatosDelGanadoDTO (){
        super();
    }

    public DatosDelGanadoDTO(Long idGanado, Long idCaravana, char sexo, Double temperatura, int cantidadPasos, String CUIG) {
        this.idGanado = idGanado;
        this.idCaravana = idCaravana;
        this.sexo = sexo;
        this.temperatura = temperatura;
        this.cantidadPasos = cantidadPasos;
        this.CUIG = CUIG;
    }

    public Long getIdGanado() {
        return idGanado;
    }

    public void setIdGanado(Long idGanado) {
        this.idGanado = idGanado;
    }

    public Long getIdCaravana() {
        return idCaravana;
    }

    public void setIdCaravana(Long idCaravana) {
        this.idCaravana = idCaravana;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
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

    public String getCUIG() {
        return CUIG;
    }

    public void setCUIG(String CUIG) {
        this.CUIG = CUIG;
    }
}
