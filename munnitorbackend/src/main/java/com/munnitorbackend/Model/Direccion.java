package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long id;

    @Column(name = "nombre_provincia", nullable = false)
    private String nombreProvincia;

    @Column(name = "nombre_localidad",nullable = false)
    private String nombreLocalidad;

    @Column(name = "descripcion_ubicacion")
    private String descripcionUbicacion;

    @Column(name = "nombre_calle")
    private String nombreCalle;

    @Column(name = "numero_calle")
    private int numeroCalle;

    private double latitud;

    private double longitud;

    @Column(name = "id_provincia", nullable = false)
    private Long idProvincia;

    @Column(name = "id_localidad", nullable = false)
    private Long idLocalidad;

    @Column(name = "codigo_postal",nullable = false)
    private String codigoPostal;

    public Direccion() {
        super();
    }

    public Direccion(Long id, String nombreProvincia, String nombreLocalidad, String descripcionUbicacion, String nombreCalle, int numeroCalle, double latitud, double longitud, Long idProvincia, Long idLocalidad, String codigoPostal) {
        this.id = id;
        this.nombreProvincia = nombreProvincia;
        this.nombreLocalidad = nombreLocalidad;
        this.descripcionUbicacion = descripcionUbicacion;
        this.nombreCalle = nombreCalle;
        this.numeroCalle = numeroCalle;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idProvincia = idProvincia;
        this.idLocalidad = idLocalidad;
        this.codigoPostal = codigoPostal;
    }

    public Direccion(String nombreProvincia, String nombreLocalidad, String descripcionUbicacion, String nombreCalle, int numeroCalle, double latitud, double longitud, Long idProvincia, Long idLocalidad, String codigoPostal) {
        this.nombreProvincia = nombreProvincia;
        this.nombreLocalidad = nombreLocalidad;
        this.descripcionUbicacion = descripcionUbicacion;
        this.nombreCalle = nombreCalle;
        this.numeroCalle = numeroCalle;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idProvincia = idProvincia;
        this.idLocalidad = idLocalidad;
        this.codigoPostal = codigoPostal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public String getDescripcionUbicacion() {
        return descripcionUbicacion;
    }

    public void setDescripcionUbicacion(String descripcionUbicacion) {
        this.descripcionUbicacion = descripcionUbicacion;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public int getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(int numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Long getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Long idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
