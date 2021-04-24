/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.munnitorbackend.DTO;

import java.io.Serializable;

/**
 *
 * @author Julito
 */
public class UserLoginDTO implements Serializable {

    private String email;
    private String password;

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginDTO() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

