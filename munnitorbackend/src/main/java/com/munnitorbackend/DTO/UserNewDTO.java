package com.munnitorbackend.DTO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserNewDTO implements Serializable {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String email;
    private String password;
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String nationality;

    public UserNewDTO(String email, String password, Long id, String firstName, String lastName, String birthDate, String nationality) {
        super();
        this.email = email;
        this.password = password;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public UserNewDTO() {
        super();
    }

    public Date getBirthDateInDateConverted() throws ParseException {
        return dateFormat.parse(this.birthDate);
    }
    public void setDateBirthDate(Date date){
        this.setBirthDate(dateFormat.format(date));
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }



    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
