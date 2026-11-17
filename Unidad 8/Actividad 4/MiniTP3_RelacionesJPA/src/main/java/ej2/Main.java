package ej2;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej2PU");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Crear cursos
            Curso java = new Curso("Java Básico");
            Curso baseDatos = new Curso("Base de Datos");
            Curso programacion = new Curso("Programación III");

            em.persist(java);
            em.persist(baseDatos);
            em.persist(programacion);

            // Crear personas
            Persona lucia = new Persona("Lucía");
            Persona marcos = new Persona("Marcos");
            Persona carolina = new Persona("Carolina");

            // Inscribir personas mediante el helper
            lucia.agregarCurso(java);
            lucia.agregarCurso(baseDatos);

            marcos.agregarCurso(java);
            marcos.agregarCurso(programacion);

            carolina.agregarCurso(java);
            carolina.agregarCurso(baseDatos);
            carolina.agregarCurso(programacion);

            // Persistir personas
            em.persist(lucia);
            em.persist(marcos);
            em.persist(carolina);

            em.getTransaction().commit();

            // Recuperar personas y mostrar sus cursos
            System.out.println("Personas y sus cursos:");

            Persona p1 = em.find(Persona.class, lucia.getId());
            Persona p2 = em.find(Persona.class, marcos.getId());
            Persona p3 = em.find(Persona.class, carolina.getId());

            for (Persona persona : new Persona[]{p1, p2, p3}) {

                System.out.print("  " + persona.getNombre() + " → ");

                for (Curso curso : persona.getCursos()) {
                    System.out.print(curso.getNombre() + ", ");
                }

                System.out.println();
            }

            // Recuperar cursos y mostrar sus inscriptos
            System.out.println("\nCursos y sus inscriptos:");

            Curso c1 = em.find(Curso.class, java.getId());
            Curso c2 = em.find(Curso.class, baseDatos.getId());
            Curso c3 = em.find(Curso.class, programacion.getId());

            for (Curso curso : new Curso[]{c1, c2, c3}) {

                System.out.print("  " + curso.getNombre() + " → ");

                for (Persona persona : curso.getInscriptos()) {
                    System.out.print(persona.getNombre() + ", ");
                }

                System.out.println();
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}

/*
La relación ManyToMany necesita una tabla intermedia porque
una persona puede estar inscripta en varios cursos y un curso
puede tener varias personas.

La tabla PERSONA_CURSO almacena las relaciones mediante dos
claves foráneas: PERSONA_ID y CURSO_ID.

Persona es el lado propietario porque tiene la anotación
@JoinTable. Curso es el lado inverso porque utiliza mappedBy.

Si olvidáramos mappedBy, Hibernate podría generar dos tablas
intermedias independientes.

El método agregarCurso() actualiza las dos colecciones en memoria.
Si solamente agregáramos el curso a Persona.cursos, la colección
Curso.inscriptos no se actualizaría automáticamente.

La tabla intermedia permite representar todas las asociaciones
sin tener que almacenar múltiples claves foráneas directamente
en las tablas PERSONAS o CURSOS.

En H2 se verificó que existe una única tabla PERSONA_CURSO
con las columnas PERSONA_ID y CURSO_ID.
*/