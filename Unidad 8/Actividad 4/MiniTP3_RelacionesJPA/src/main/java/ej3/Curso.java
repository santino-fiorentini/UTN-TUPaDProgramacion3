package ej3;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "CURSOS")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @ManyToMany(mappedBy = "cursos")
    private Set<Persona> inscriptos = new LinkedHashSet<>();

    public Curso() {
    }

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Persona> getInscriptos() {
        return inscriptos;
    }

    public void setInscriptos(Set<Persona> inscriptos) {
        this.inscriptos = inscriptos;
    }
}