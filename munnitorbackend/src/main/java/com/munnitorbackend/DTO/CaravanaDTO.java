package com.munnitorbackend.DTO;

import java.io.Serializable;

public class CaravanaDTO implements Serializable {
    private Long idCaravana;
    private String cuig;
    private char colorCaravana;
    private String idInternacional;

    public CaravanaDTO(Long idCaravana, String cuig, char colorCaravana, String idInternacional) {
        this.idCaravana = idCaravana;
        this.cuig = cuig;
        this.colorCaravana = colorCaravana;
        this.idInternacional = idInternacional;
    }

    public CaravanaDTO(String cuig, char colorCaravana, String idInternacional) {
        this.cuig = cuig;
        this.colorCaravana = colorCaravana;
        this.idInternacional = idInternacional;
    }

    public CaravanaDTO() {
        super();
    }

    public Long getIdCaravana() {
        return idCaravana;
    }

    public void setIdCaravana(Long idCaravana) {
        this.idCaravana = idCaravana;
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
}
