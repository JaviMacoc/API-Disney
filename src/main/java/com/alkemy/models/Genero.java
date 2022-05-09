package com.alkemy.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@JsonIgnoreProperties({"peliculas"})
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String nombre;
    @Lob
    @Column(columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] imagen;    
    @OneToMany(mappedBy = "genero")    
    @Getter @Setter
    private List<Pelicula> peliculas;

    public Genero() {
        this.peliculas = new ArrayList<>();
    }   
    
}
