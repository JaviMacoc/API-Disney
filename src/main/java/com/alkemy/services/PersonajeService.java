package com.alkemy.services;

import com.alkemy.models.Personaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.repository.PersonajeRepository;

@Service
public class PersonajeService {

    @Autowired
    PersonajeRepository personajeRepository;
        
    public List<Personaje> findAll(){
        return personajeRepository.findAll();
    }
    
    public Personaje findById(int id){        
        return personajeRepository.findById(id).orElseThrow();
    }
    
    public List<Personaje> findByNombre(String nombre){        
        return personajeRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public List<Personaje> findByAge(int edad){        
        return personajeRepository.findByEdad(edad);
    }
    
    public List<Personaje> findByMovie(int id){
        return personajeRepository.findByPeliculas(id);
    }    
    
    public Personaje create(Personaje personaje){
        return personajeRepository.save(personaje);
    }
    
    public void remove(int id){        
        personajeRepository.deleteById(id);        
    }
    
    public Personaje edit(Personaje personaje){
        return personajeRepository.save(personaje);
    }
}
