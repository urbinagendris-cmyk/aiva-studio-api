package com.aivastudio.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.aivastudio.model.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity {

    private String nombre;
    private String email;
    private String password;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, String password) {
        super(id);
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}