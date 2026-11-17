package ej2;

import jakarta.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej2PU");

        Long luciaId;

        // PARTE 1: CREAR LOS DATOS

        EntityManager em1 = emf.createEntityManager();

        try {
            em1.getTransaction().begin();

            Curso java = new Curso("Java Básico");
            Curso baseDatos = new Curso("Base de Datos");
            Curso programacion = new Curso("Programación III");

            Persona lucia = new Persona("Lucía");
            lucia.agregarCurso(java);
            lucia.agregarCurso(baseDatos);

            Persona marcos = new Persona("Marcos");
            marcos.agregarCurso(java);
            marcos.agregarCurso(programacion);

            Persona carolina = new Persona("Carolina");
            carolina.agregarCurso(java);
            carolina.agregarCurso(baseDatos);
            carolina.agregarCurso(programacion);

            em1.persist(lucia);
            em1.persist(marcos);
            em1.persist(carolina);

            em1.getTransaction().commit();

            luciaId = lucia.getId();

            System.out.println("Datos guardados correctamente.");

        } finally {
            em1.close();
        }

        // PARTE 2: FETCH LAZY

        EntityManager em2 = emf.createEntityManager();

        try {
            System.out.println("\n-- FETCH LAZY");

            Persona persona =
                    em2.find(Persona.class, luciaId);

            System.out.println("Persona cargada: "
                    + persona.getNombre());

            // La consulta de cursos se ejecuta al acceder
            // a la colección LAZY.
            System.out.println("Cursos de Lucía:");

            for (Curso curso : persona.getCursos()) {
                System.out.println("- " + curso.getNombre());
            }

        } finally {
            em2.close();
        }

        // PARTE 3: FETCH EAGER Y PROBLEMA N+1

        EntityManager em3 = emf.createEntityManager();

        try {
            System.out.println("\n-- FETCH EAGER");

            List<PersonaEager> personas = em3.createQuery(
                    "SELECT p FROM PersonaEager p",
                    PersonaEager.class
            ).getResultList();

            for (PersonaEager p : personas) {
                System.out.println("Persona: " + p.getNombre());
            }

            System.out.println("Personas recuperadas: "
                    + personas.size());

        } finally {
            em3.close();
            emf.close();
        }

        /*
         El problema N+1 ocurre cuando JPA ejecuta una consulta
         inicial para recuperar varias entidades y después realiza
         una consulta adicional por cada entidad para cargar
         sus relaciones.

         En este ejemplo, con tres personas, se esperan cuatro
         consultas: una inicial y tres adicionales.

         El FetchType por defecto de ManyToMany es LAZY.
         Esto tiene sentido porque una persona puede tener muchos
         cursos y no siempre necesitamos recuperarlos.

         Si accedemos a una relación LAZY después de cerrar
         el EntityManager, Hibernate lanza
         LazyInitializationException porque ya no puede ejecutar
         la consulta necesaria para cargar la relación.

         EAGER puede ser conveniente cuando sabemos que siempre
         necesitamos los datos relacionados y la cantidad de
         información es pequeña y controlada.

         Utilizamos PersonaEager para demostrar EAGER sin modificar
         Persona, que debe conservar LAZY para poder comparar
         ambos comportamientos en una misma ejecución.
        */
    }
}