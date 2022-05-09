package com.alkemy.services;

import com.alkemy.models.Pelicula;
import com.alkemy.models.Personaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.repository.PeliculaRepository;
import java.util.ArrayList;

@Service
public class PeliculaService {

    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    PersonajeService personajeService;

    public List<Pelicula> findAll() {
        return peliculaRepository.findAll();
    }

    public List<Pelicula> findAllDesc() {
        return peliculaRepository.findAllByOrderByIdDesc();
    }

    public Pelicula findById(int id) {
        return peliculaRepository.findById(id).orElseThrow();
    }

    public List<Pelicula> findByTitulo(String titulo) {
        return peliculaRepository.findByTitulo(titulo);
    }

    public List<Pelicula> findByGenero(int generoId) {
        return peliculaRepository.findByGenero(generoId);
    }

    public Pelicula create(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void remove(int id) {
        peliculaRepository.deleteById(id);
    }

    public Pelicula edit(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public Pelicula addCharacter(int peliculaId, int personajeId) {
        Pelicula pelicula = this.findById(peliculaId);
        Personaje personaje = personajeService.findById(personajeId);
        List<Personaje> personajesDeLaPelicula = pelicula.getPersonajes();
        if (!personajesDeLaPelicula.contains(personaje)) {
            personajesDeLaPelicula.add(personaje);
            pelicula.setPersonajes(personajesDeLaPelicula);
            peliculaRepository.save(pelicula);
        }
        return pelicula;
    }

    public Pelicula removeCharacter(int peliculaId, int personajeId) {
        Pelicula pelicula = this.findById(peliculaId);
        Personaje personaje = personajeService.findById(personajeId);
        List<Personaje> personajesDeLaPelicula = pelicula.getPersonajes();
        if (personajesDeLaPelicula.contains(personaje)) {
            personajesDeLaPelicula.remove(personaje);
            pelicula.setPersonajes(personajesDeLaPelicula);
            peliculaRepository.save(pelicula);
        }
        return pelicula;
    }
}
