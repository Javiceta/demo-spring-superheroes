/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Superheroe;
import com.example.demo.service.SuperheroeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private SuperheroeService superheroeService;
    
    @GetMapping("/superheroes")
    public List<Superheroe> getAllSuperheroes() {
        return superheroeService.obtenerTodosSuperheroes();
    }
    
    @GetMapping("/superheroes/id/{id}")
    public ResponseEntity<Superheroe> getSuperheroeById(@PathVariable(value = "id") Long superHeroeId) {
        return superheroeService.obtenerSuperheroePorId(superHeroeId);
    }
    
    @GetMapping("/superheroes/name/{cadena}")
    public ResponseEntity<List<Superheroe>> getSuperheroeByCadenaInName(@PathVariable(value = "cadena") String cadena) {
        return superheroeService.obtenerSuperheroesPorCadenaEnNombre(cadena);
    }
    
    @PutMapping("/superheroes/id/{id}")
    public ResponseEntity <Superheroe> updateSuperheroe(@PathVariable(value = "id") Long superHeroeId
        , @Validated @RequestBody Superheroe superheroeModificado){
        return superheroeService.actualizarSuperheroe(superHeroeId, superheroeModificado);
    }
    
    @DeleteMapping("/superheroes/id/{id}")
    public Map<String, Boolean> deleteSuperheroe(@PathVariable(value = "id") Long superHeroeId) {
        return superheroeService.eliminarSuperheroe(superHeroeId);
    }
}
