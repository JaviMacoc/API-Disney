package com.alkemy.services;

import com.alkemy.models.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.repository.GeneroRepository;

@Service
public class GeneroService {

    @Autowired
    GeneroRepository generoRepository;
    
    public Genero findById(int id){
        return generoRepository.findById(id).orElseThrow();
    }
    
    public void create(Genero genero){
        generoRepository.save(genero);
    }
    
    public void remove(int id){
        generoRepository.deleteById(id);
    }
    
}
