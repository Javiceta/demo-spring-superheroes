package com.example.demo;

import com.example.demo.model.Superheroe;
import com.example.demo.service.SuperheroeService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    
    @Mock
    private SuperheroeService serviceMock;
    
    @Autowired
    private SuperheroeService superheroeService;
    
    @Test
    void contextLoads() {
    }
    
    /**
     * Test unitario que comprueba el funcionamiento de la búsqueda de un superhéroe por ID
     */
    @Test
    public void whenSuperheroeIdIsProvided_thenRetrievedSuperheroeIsCorrect() {
        Long testId = 1L;
        Superheroe mockHeroe = new Superheroe("Superman", 100); mockHeroe.setId(1L);
        
        Mockito
                .when(serviceMock.obtenerSuperheroePorId(testId))
                .thenReturn(mockHeroe);
        
        Superheroe response = superheroeService.obtenerSuperheroePorId(testId);
        Assert.assertEquals(mockHeroe.getNombre(), response.getNombre());
        Assert.assertEquals(mockHeroe.getFuerza(), response.getFuerza());
    }

}
