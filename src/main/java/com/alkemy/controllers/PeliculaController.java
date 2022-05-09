package com.alkemy.controllers;

import com.alkemy.models.Pelicula;
import com.alkemy.models.Personaje;
import com.alkemy.services.PeliculaService;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pelicula>> findAll() {
        return new ResponseEntity<>(peliculaService.findAll(), HttpStatus.OK);
    }

    @GetMapping(params = "id", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pelicula> findById(@RequestParam("id") int id) {
        try {
            return new ResponseEntity<>(peliculaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(params = "titulo", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pelicula>> findByTitulo(@RequestParam("titulo") String titulo) {
        return new ResponseEntity<>(peliculaService.findByTitulo(titulo), HttpStatus.OK);
    }

    @GetMapping(params = "generoId", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pelicula>> findByGenero(@RequestParam("generoId") int generoId) {
        return new ResponseEntity<>(peliculaService.findByGenero(generoId), HttpStatus.OK);
    }

    @GetMapping(params = "order", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pelicula>> findByGenero(@RequestParam("order") String order) {
        if (order.equals("ASC")) {
            return new ResponseEntity<>(peliculaService.findAll(), HttpStatus.OK);
        }
        if (order.equals("DESC")) {
            return new ResponseEntity<>(peliculaService.findAllDesc(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public Pelicula create(Pelicula pelicula) {
        return peliculaService.create(pelicula);
    }

    @PostMapping("/edit")
    public Pelicula edit(Pelicula pelicula) {
        return peliculaService.edit(pelicula);
    }

    @GetMapping("/remove")
    public void remove(int id) {
        peliculaService.remove(id);
    }

    @PostMapping("{peliculaId}/characters/{personajeId}")
    public ResponseEntity<Pelicula> agregarPersonaje(@PathParam("peliculaId") int peliculaId, int personajeId) {
        try {
            return new ResponseEntity<>(peliculaService.addCharacter(peliculaId, personajeId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{peliculaId}/characters/{personajeId}")
    public ResponseEntity<Pelicula> quitarPersonaje(@PathParam("peliculaId") int peliculaId, int personajeId) {
        try {
            return new ResponseEntity<>(peliculaService.removeCharacter(peliculaId, personajeId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
