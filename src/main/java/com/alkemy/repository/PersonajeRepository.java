package com.alkemy.repository;

import com.alkemy.models.Personaje;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer>{
        
    public List<Personaje> findByNombreContainingIgnoreCase(String nombre);    
    
    public List<Personaje> findByEdad(int edad);
      
    @Query(value="select pj from Personaje pj join pj.peliculas pl where pl.id = :id")
    public ArrayList<Personaje> findByPeliculas(@Param("id") int perliculaId);
    //public ArrayList<Personaje> findByPeliculas(int perliculaId);
}
