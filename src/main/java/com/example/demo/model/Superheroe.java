/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que representa un superhéroe.
 * 
 * @author R79061236
 */

@Entity
@Table(name = "superheroe")
public class Superheroe implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    
    @Column (name = "nombre", nullable = false)
    private String nombre;
    
    @Column (name = "fuerza", nullable = false)
    private Integer fuerza;
    
    public Superheroe() {}
    
    public Superheroe(String nombre, Integer fuerza) {
        this.nombre = nombre;
        this.fuerza = fuerza;
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

    public Integer getFuerza() {
        return fuerza;
    }

    public void setFuerza(Integer fuerza) {
        this.fuerza = fuerza;
    }
}
