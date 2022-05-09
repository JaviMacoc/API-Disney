package com.alkemy.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Lob
    @Column(columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] imagen;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private int edad;
    @Getter @Setter
    private int peso;
    @Getter @Setter
    private String historia;
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "personajesEnPeliculas",
            joinColumns = @JoinColumn(name="personaje"),
            inverseJoinColumns = @JoinColumn(name="pelicula"))
    @Getter @Setter
    private List<Pelicula> peliculas;

    public Personaje() {
        this.peliculas = new ArrayList<>();
    }
    
    
}
