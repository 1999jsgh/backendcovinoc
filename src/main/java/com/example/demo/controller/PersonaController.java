package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.service.PersonaService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Utils;
import com.example.demo.dto.PersonaDto;
import com.example.demo.entity.Persona;
import org.springframework.web.server.ResponseStatusException;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/newUser")
    public Map<String, Object> create(@RequestBody PersonaDto per) {
        try {
            if (per.getNombre().isEmpty() || per.getTelefono().isEmpty() || per.getIdentificacion().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la gestion por datos vacios", null);
            } else {
                try {
                    if (per.getTelefono().length() == 10) {
                        Long telefono = Long.parseLong(per.getTelefono());
                        try {
                            if (per.getIdentificacion().length() == 10) {
                                Long identificacion = Long.parseLong(per.getIdentificacion());
                                Persona persona = new Persona(per.getNombre(), per.getTelefono(), per.getIdentificacion());
                                personaService.save(persona);
                                return Utils.mapear(true, "Registro exitoso", persona);
                            } else {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error se ingresaron letras en la identificación", null);
                            }
                        } catch (Exception e) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error se ingresaron letras en la identificación", null);
                        }
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al digilenciar la telefono", null);
                    }
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error se ingresaron letras en la telefono", null);
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de Registro", null);
        }
    }

    @GetMapping("/getUsers")
    public List<Persona> findAll() {
        return personaService.findAll();
    }


    @DeleteMapping("/deleteUser/{id}")
    public Map<String, Object> id(@PathVariable int id) {
        try {
            personaService.deleteById(id);
            return Utils.msj("Usuario eliminado");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error al eliminar Usuario");
        }

    }

    @PutMapping(path = "/updateUser")
    public Map<String, Object> editarCliente(@RequestBody PersonaDto personaDto) {
        try {
            Optional<Persona> usuario = personaService.findById(personaDto.getPersona_id());
            System.out.println(personaDto.getPersona_id());
            if (usuario.isPresent()) {
                try {
                    if (personaDto.getTelefono().length() == 10 && personaDto.getIdentificacion().length() == 10) {
                        Long telefono = Long.parseLong(personaDto.getTelefono());
                        Long identificacion = Long.parseLong(personaDto.getIdentificacion());
                        Persona persona = usuario.get();
                        persona.setNombre(personaDto.getNombre());
                        persona.setTelefono(personaDto.getTelefono());
                        persona.setIdentificacion(personaDto.getIdentificacion());
                        personaService.save(persona);
                        return Utils.mapear(true, "Actualizacion Exitosa", persona);
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error se ingresaron mas de 10 digitos en identificación o telefono", null);
                    }
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error se ingresaron Letras en identificación o telefono", null);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registro no encontrado", null);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al acceder a los registros", null);
        }
    }
}
