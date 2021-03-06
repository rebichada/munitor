package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long id;

    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Direccion direccion;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", unique = true)
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Usuario usuario;

    @JoinColumn(name = "id_tambo", referencedColumnName = "id_tambo")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Tambo tambo;

    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 40, nullable = false)
    private String apellido;

    @Column(name = "dni", length = 15, nullable = false)
    private int dni;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Column(name = "fecha_nacimiento")
    private Date fechaDeNacimiento;

    private String nacionalidad;

    @Column(length = 20)
    private String telefono;


    public Empleado() {
        super();
    }

    public Empleado(Long id, Direccion direccion, Usuario usuario, Tambo tambo, String nombre, String apellido, int dni, Date fechaDeNacimiento, String nacionalidad, String telefono) {
        this.id = id;
        this.direccion = direccion;
        this.usuario = usuario;
        this.tambo = tambo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    public Empleado(Direccion direccion, Usuario usuario, Tambo tambo, String nombre, String apellido, int dni, Date fechaDeNacimiento, String nacionalidad, String telefono) {
        this.direccion = direccion;
        this.usuario = usuario;
        this.tambo = tambo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Tambo getTambo() {
        return tambo;
    }

    public void setTambo(Tambo tambo) {
        this.tambo = tambo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
