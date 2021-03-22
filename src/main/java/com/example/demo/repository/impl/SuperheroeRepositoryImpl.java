/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository.impl;

import com.example.demo.model.Superheroe;
import com.example.demo.repository.SuperheroeRepositoryCustom;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author R79061236
 */
public class SuperheroeRepositoryImpl implements SuperheroeRepositoryCustom{
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<Superheroe> findByCadenaInNombre(String cadena) {
        Query query = entityManager.createNativeQuery(
                "SELECT sh.* FROM superheroe as sh" + 
                " WHERE sh.nombre ILIKE ?", Superheroe.class);
        query.setParameter(1, "%" + cadena + "%");
        
        return query.getResultList();
    }
}
