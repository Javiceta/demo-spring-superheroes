/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.controller.SuperheroeController;
import com.example.demo.model.Superheroe;
import com.example.demo.repository.SuperheroeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de los servicios de la API.
 * Los métodos de consulta son cacheados.
 * 
 * @author R79061236
 */

@Service
public class SuperheroeService {
    
    private static final Logger log = LoggerFactory.getLogger(SuperheroeController.class);
    private final SuperheroeRepository superheroeRepository;
    
    public SuperheroeService(SuperheroeRepository superheroeRepository) {
        this.superheroeRepository = superheroeRepository;
    }
    
    /**
     * Obtiene un listado de todos los superhéroes en formato JSON.
     * El resultado de la consulta se almacena en caché.
     * 
     * @return listado de superhéroes
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "superheroesTodosCache")
    public List<Superheroe> obtenerTodosSuperheroes() {
        log.info("Obteniendo todos los superhéroes");
        return superheroeRepository.findAll();
    }
    
    /**
     * Obtiene un superhéroe dado su ID.
     * Lanza una excepción en caso de no encontrarse.
     * El resultado de la consulta se almacena en caché.
     * 
     * @param id
     * @return superhéroe
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "superheroePorIdCache")
    public Superheroe obtenerSuperheroePorId(Long id) {
        log.info("Obteniendo superhéroe por ID (" + id + ")");
        Superheroe heroe = superheroeRepository.findById(id)
                .orElseThrow();
        return heroe;
    }
    
    /**
     * Obtiene un listado de superhéroes dada una cadena que esté incluida en el nombre.
     * Devuelve una lista vacía si no encuentra ninguno.
     * La consulta es insensible a mayúsculas.
     * El resultado de la consulta se almacena en caché.
     * 
     * @param cadena
     * @return listado de superhéroes
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Cacheable(value = "superheroesPorNombre")
    public List<Superheroe> obtenerSuperheroesPorCadenaEnNombre(String cadena) {
        log.info("Obteniendo superhéroes por la subcadena en el nombre ('" + cadena + "')");
        List<Superheroe> superheroes = superheroeRepository.findByCadenaInNombre(cadena);
        
        return superheroes;
    }
    
    /**
     * Modifica un superhéroe dado su ID.
     * Actualiza el nombre y la fuerza superhéroe con los datos enviados
     * Lanza una excepción en caso de no encontrar el superhéroe
     * 
     * @param id
     * @param superheroeModificado
     * @return el superhéroe actualizado
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superheroe actualizarSuperheroe(Long id, Superheroe superheroeModificado) {
        Superheroe heroe = superheroeRepository.findById(id)
                .orElseThrow();
        
        heroe.setNombre(superheroeModificado.getNombre());
        heroe.setFuerza(superheroeModificado.getFuerza());
        final Superheroe superheroeActualizado = superheroeRepository.save(heroe);
        
        return superheroeActualizado;
    }
    
    /**
     * Elimina un superhéroe dado su ID
     * Lanza una excepción en caso de no encontrar el superhéroe
     * Devuelve un mensaje de confirmación del borrado en caso exitoso
     * 
     * @param id
     * @return 'Borrado: true'
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Map<String, Boolean> eliminarSuperheroe(Long id) {
        Superheroe heroe = superheroeRepository.findById(id)
                .orElseThrow();

        superheroeRepository.delete(heroe);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Borrado", Boolean.TRUE);
        
        return respuesta;
    }
}
