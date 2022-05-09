package com.alkemy.controllers;

import com.alkemy.models.Personaje;
import com.alkemy.services.PersonajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    PersonajeService personajeService;

    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Personaje>> findAll() {
        return new ResponseEntity<>(personajeService.findAll(), HttpStatus.OK);
    }

    @GetMapping(params = "id", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personaje> findById(@RequestParam int id) {        
        try {
            return new ResponseEntity<>(personajeService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(params = "nombre", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Personaje>> findByNombre(@RequestParam String nombre) {        
        return new ResponseEntity<>(personajeService.findByNombre(nombre), HttpStatus.OK);
    }
    
    @GetMapping(params = "edad", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Personaje>> findByEdad(@RequestParam int edad) {        
        return new ResponseEntity<>(personajeService.findByAge(edad), HttpStatus.OK);
    }

    @GetMapping(params = "peliculaId", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Personaje>> findByMovie(@RequestParam int peliculaId) {
        return new ResponseEntity<>(personajeService.findByMovie(peliculaId), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Personaje> create(Personaje personaje) {
        try {
            personajeService.create(personaje);
            return new ResponseEntity<>(personajeService.create(personaje), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/editar")
    public ResponseEntity<Personaje> edit(Personaje personaje) {
        try {
            personajeService.edit(personaje);
            return new ResponseEntity<>( personajeService.edit(personaje), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/borrar")
    public HttpStatus remove(int id) {
        try {
            personajeService.remove(id);
            return HttpStatus.GONE;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
