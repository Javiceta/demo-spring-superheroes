/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.service.SuperheroeService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Configuración de Mockito para hacer los tests
 * 
 * @author R79061236
 */
@Configuration
public class SuperheroeServiceTestConfiguration {
    @Primary
    public SuperheroeService superheroeService() {
        return Mockito.mock(SuperheroeService.class);
    }
}
