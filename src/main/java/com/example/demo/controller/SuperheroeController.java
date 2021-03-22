/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Superheroe;
import com.example.demo.repository.SuperheroeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author R79061236
 */

@RestController
@RequestMapping("/api")
public class SuperheroeController {
    
    @Autowired
    private SuperheroeRepository superheroeRepository;
    
    @GetMapping("/superheroes")
    public List<Superheroe> getAllSuperheroes() {
        return superheroeRepository.findAll();
    }
    
    @GetMapping("/superheroes/id/{id}")
    public ResponseEntity<Superheroe> getSuperheroeById(@PathVariable(value = "id") Long superHeroeId) {
        Superheroe heroe = superheroeRepository.findById(superHeroeId)
                .orElseThrow(); //TO DO custom exception
        return ResponseEntity.ok().body(heroe);
    }
    
    @GetMapping("/superheroes/name/{cadena}")
    public ResponseEntity<List<Superheroe>> getSuperheroeByCadenaInName(@PathVariable(value = "cadena") String cadena) {
        List<Superheroe> superheroes = superheroeRepository.findByCadenaInNombre(cadena);
        
        return ResponseEntity.ok().body(superheroes);
    }
    
    @PutMapping("/superheroes/id/{id}")
    public ResponseEntity <Superheroe> updateSuperheroe(@PathVariable(value = "id") Long superHeroeId
        , @Validated @RequestBody Superheroe superheroeModificado){
        Superheroe heroe = superheroeRepository.findById(superHeroeId)
                .orElseThrow(); //TO DO custom exception
        
        heroe.setNombre(superheroeModificado.getNombre());
        heroe.setFuerza(superheroeModificado.getFuerza());
        final Superheroe superheroeActualizado = superheroeRepository.save(heroe);
        
        return ResponseEntity.ok(superheroeActualizado);
    }
    
    @DeleteMapping("/superheroes/id/{id}")
    public Map<String, Boolean> deleteSuperheroe(@PathVariable(value = "id") Long superHeroeId) {
        Superheroe heroe = superheroeRepository.findById(superHeroeId)
                .orElseThrow(); //TO DO custom exception

        superheroeRepository.delete(heroe);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Borrado", Boolean.TRUE);
        
        return respuesta;
    }
}
