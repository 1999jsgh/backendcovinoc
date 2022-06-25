package com.example.demo.service;

import com.example.demo.controller.PersonaController;
import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaService personaService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        persona= new Persona();
//        persona.setNombre("juan");
//        persona.setTelefono("3203551846");
//        persona.setIdentificacion("1090531282");
    }

    @Test
    void findAll() {
        assertNotNull(personaService.findAll());
    }

//    @Test
//    void save() {
//
//        when(personaRepository.save(any(Persona.class))).thenReturn(persona);
//        Persona persona= new Persona("juan","3203551846","1090531282");
//        assertNotNull(personaService.save(persona));
//    }
}