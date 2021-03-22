/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Superheroe;
import com.example.demo.repository.SuperheroeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author R79061236
 */

@Service
public class SuperheroeService {
    
    private final SuperheroeRepository superheroeRepository;
    
    public SuperheroeService(SuperheroeRepository superheroeRepository) {
        this.superheroeRepository = superheroeRepository;
    }
    
    public List<Superheroe> obtenerTodosSuperheroes() {
        return superheroeRepository.findAll();
    }
    
    public ResponseEntity<Superheroe> obtenerSuperheroePorId(Long id) {
        Superheroe heroe = superheroeRepository.findById(id)
                .orElseThrow(); //TO DO custom exception
        return ResponseEntity.ok().body(heroe);
    }
    
    public ResponseEntity<List<Superheroe>> obtenerSuperheroesPorCadenaEnNombre(String cadena) {
        List<Superheroe> superheroes = superheroeRepository.findByCadenaInNombre(cadena);
        
        return ResponseEntity.ok().body(superheroes);
    }
    
    public ResponseEntity<Superheroe> actualizarSuperheroe(Long id, Superheroe superheroeModificado) {
        Superheroe heroe = superheroeRepository.findById(id)
                .orElseThrow(); //TO DO custom exception
        
        heroe.setNombre(superheroeModificado.getNombre());
        heroe.setFuerza(superheroeModificado.getFuerza());
        final Superheroe superheroeActualizado = superheroeRepository.save(heroe);
        
        return ResponseEntity.ok(superheroeActualizado);
    }
    
    public Map<String, Boolean> eliminarSuperheroe(Long id) {
        Superheroe heroe = superheroeRepository.findById(id)
                .orElseThrow(); //TO DO custom exception

        superheroeRepository.delete(heroe);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Borrado", Boolean.TRUE);
        
        return respuesta;
    }
}
