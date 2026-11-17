package ej3;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "PERSONAS")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "PERSONA_CURSO",
            joinColumns = @JoinColumn(name = "PERSONA_ID"),
            inverseJoinColumns = @JoinColumn(name = "CURSO_ID")
    )
    private Set<Curso> cursos = new LinkedHashSet<>();

    public Persona() {
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
        curso.getInscriptos().add(this);
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

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
}