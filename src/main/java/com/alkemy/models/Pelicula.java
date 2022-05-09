package com.alkemy.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
public class Pelicula implements Serializable {    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Lob
    @Column(columnDefinition = "BLOB")
    @Getter @Setter
    private byte[] imagen;
    @Getter @Setter
    private String titulo;
    @Getter @Setter
    private Date fecha;
    @Getter @Setter
    private int calificacion;
    @ManyToOne
    @Getter @Setter
    private Genero genero;
    @JsonBackReference
    @ManyToMany(mappedBy = "peliculas", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Personaje> personajes;

    public Pelicula() {
        this.personajes = new ArrayList<>();
    }    

}
