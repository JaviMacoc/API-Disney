package com.alkemy;

import com.alkemy.models.Genero;
import com.alkemy.models.Pelicula;
import com.alkemy.models.Personaje;
import com.alkemy.services.GeneroService;
import com.alkemy.services.PeliculaService;
import com.alkemy.services.PersonajeService;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataBaseMocking {

    @Autowired
    PeliculaService peliculaService;
    @Autowired
    PersonajeService personajeService;
    @Autowired
    GeneroService generoService;
    
    public void rellenar() throws IOException {
        
        String fileName = "";
        InputStream bis = this.getClass().getResourceAsStream(fileName);
        

        Personaje pj1 = new Personaje();
        Personaje pj2 = new Personaje();
        Personaje pj3 = new Personaje();
        Personaje pj4 = new Personaje();
        Personaje pj5 = new Personaje();
        Personaje pj6 = new Personaje();

        Pelicula pl1 = new Pelicula();
        Pelicula pl2 = new Pelicula();
        Pelicula pl3 = new Pelicula();

        Genero g1 = new Genero();
        Genero g2 = new Genero();
        
        pj1.setNombre("Capitan America");
        pj2.setNombre("Black Widow");
        pj3.setNombre("Iron Man");
        pj3.setEdad(45);
        pj4.setNombre("Luke Skywalker");
        pj4.setEdad(30);
        pj5.setNombre("Obi Wan Kenobi");
        pj6.setNombre("Anakin Skywalker");
        pj6.setEdad(60);
        /*try {
            pj1.setImagen(IOUtils.toByteArray(getFileFromResourceAsStream("static/img/personaje/cpt_america.jpg")));
        } catch (IOException ex) {
            System.out.println("No hay recurso");
        }
        try {
            pj2.setImagen(IOUtils.toByteArray(getFileFromResourceAsStream("static/img/personaje/black_widow.jpg")));            
        } catch (IOException ex) {
            System.out.println("No hay recurso");
        }
        try {
            pj3.setImagen(IOUtils.toByteArray(getFileFromResourceAsStream("static/img/personaje/iron_man.jpg")));
        } catch (IOException ex) {
            System.out.println("No hay recurso");
        }
        try {
            pj4.setImagen(IOUtils.toByteArray(getFileFromResourceAsStream("static/img/personaje/luke_skywalker.jpg")));
        } catch (IOException ex) {
            System.out.println("No hay recurso");
        }
        try {
            pj5.setImagen(IOUtils.toByteArray(getFileFromResourceAsStream("static/img/personaje/obi_wan.png")));
        } catch (IOException ex) {
            System.out.println("No hay recurso");
        }*/

        pl1.setTitulo("Star Wars");
        pl2.setTitulo("Avengers");
        pl3.setTitulo("Avengers 2");
        g1.setNombre("Aventuras");
        g2.setNombre("Fantasia");
        
        /*try {
            pl1.setImagen(IOUtils.toByteArray(getFileFromResourceAsStream("static/img/pelicula/starWars.jpg")));
        } catch (IOException ex) {
            System.out.println("No hay recurso");
        }
        try {
            pl1.setImagen(IOUtils.toByteArray(getFileFromResourceAsStream("static/img/pelicula/marvel.jpg")));
        } catch (IOException ex) {
            System.out.println("No hay recurso");
        }
        
        try {
            g1.setImagen(IOUtils.toByteArray(getFileFromResourceAsStream("static/img/genero/aventura.jpg")));
        } catch (IOException ex) {
            System.out.println("No hay recurso");
        }*/
        List<Personaje> pjsStarWars= new ArrayList<>();        
        List<Personaje> pjsAvengers= new ArrayList<>(); 
        
        List<Pelicula> plsStarWars = new ArrayList<>();
        List<Pelicula> plsMarvel = new ArrayList<>();
        List<Pelicula> plsTodas = new ArrayList<>();
        
        plsStarWars.add(pl1);
        plsMarvel.add(pl2);
        plsMarvel.add(pl3);
        plsTodas.add(pl1);
        plsTodas.add(pl2);
        plsTodas.add(pl3);
        
        pjsStarWars.add(pj6);
        pjsStarWars.add(pj5);
        pjsStarWars.add(pj4);
        pjsAvengers.add(pj1);
        pjsAvengers.add(pj2);
        pjsAvengers.add(pj3);              
        
        /*pl1.setPersonajes(pjsStarWars);
        pl2.setPersonajes(pjsAvengers);*/
              
        pj1.setPeliculas(plsMarvel);
        pj2.setPeliculas(plsMarvel);
        pj3.setPeliculas(plsMarvel);
        pj4.setPeliculas(plsStarWars);
        pj5.setPeliculas(plsStarWars);
        
        pl1.setGenero(g1);
        pl2.setGenero(g2);
        pl3.setGenero(g1);
        
        /*g1.setPeliculas(plsStarWars);
        g2.setPeliculas(plsMarvel);*/
        
        generoService.create(g1);
        generoService.create(g2);
        
        personajeService.create(pj1);
        personajeService.create(pj2);
        personajeService.create(pj3);
        personajeService.create(pj4);
        personajeService.create(pj5);
        personajeService.create(pj6);
        
        peliculaService.create(pl1);
        peliculaService.create(pl2);
        peliculaService.create(pl3);
        
    }
   
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

}
