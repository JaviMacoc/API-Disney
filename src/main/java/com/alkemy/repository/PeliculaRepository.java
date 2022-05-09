package com.alkemy.repository;

import com.alkemy.models.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{
    
    public List<Pelicula> findAllByOrderByIdDesc();
    
    public List<Pelicula> findByTitulo(String titulo);
    
    @Query("select pl from Pelicula pl join pl.genero g where g.id = :generoId")
    public List<Pelicula> findByGenero(int generoId);
    
}
