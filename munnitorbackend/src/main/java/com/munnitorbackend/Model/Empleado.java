package com.munnitorbackend.Model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_empleado")
    private Long id;

    @JoinColumn(name="id_direccion", referencedColumnName = "id_direccion")
    @OneToOne
    @OnDelete(action= OnDeleteAction.CASCADE)
    @NotNull
    private Direccion direccion;

    @JoinColumn(name="id_user", referencedColumnName="id_user", unique = true)
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;


    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;

    @Column(name = "apellido", length =40,nullable = false)
    private String apellido;

    @Column(name = "dni", length = 15,nullable = false)
    private int dni;

    @Column(length = 20)
    private String telefono;

    /**@Column(name = "nombre_usuario", length =45,nullable = false)
    private String nombreUsuario;

    @Column(name = "password_usuario",length = 30, nullable = false)
    private String passwordUsuario;

     @Column(length = 100, nullable = false)
     private String email;
     **/
    public Empleado() {
        super();
    }

    public Empleado(Long id, String nombre, String apellido, int dni, String email, String telefono, String nombreUsuario, String passwordUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;

        this.telefono = telefono;
        /**this.nombreUsuario = nombreUsuario;
        this.passwordUsuario = passwordUsuario;
         this.email = email;**/
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

/**
 *  public String getEmail() {
 *         return email;
 *     }
 *
 *     public void setEmail(String email) {
 *         this.email = email;
 *     }
 * public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }**/
}
