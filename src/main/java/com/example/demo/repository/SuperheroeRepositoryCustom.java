/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Superheroe;
import java.util.List;

/**
 * Repositorio personalizado para incluir el método de buscar un listado de
 * superhéroes por una subcadena contenida en su nombre
 * 
 * @author R79061236
 */
public interface SuperheroeRepositoryCustom {
    
    List<Superheroe> findByCadenaInNombre(String cadena);
}
