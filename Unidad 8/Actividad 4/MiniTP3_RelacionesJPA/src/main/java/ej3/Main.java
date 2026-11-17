package ej3;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        ejecutarParteA();

        System.out.println();

        ejecutarParteB();
    }

    // PARTE A: SIN UTILIZAR EL HELPER
    public static void ejecutarParteA() {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej3aPU");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Curso javaBasico = new Curso("Java Básico");
            em.persist(javaBasico);

            Persona lucia = new Persona("Lucía");

            // Solamente se modifica el lado propietario
            lucia.getCursos().add(javaBasico);

            em.persist(lucia);

            em.getTransaction().commit();

            System.out.println("PARTE A - SIN HELPER");

            System.out.println("Cursos de Lucía: "
                    + lucia.getCursos().size());

            System.out.println("Inscriptos en Java Básico: "
                    + javaBasico.getInscriptos().size());

        } finally {
            em.close();
            emf.close();
        }
    }

    // PARTE B: UTILIZANDO EL HELPER
    public static void ejecutarParteB() {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej3bPU");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Curso javaBasico = new Curso("Java Básico");
            em.persist(javaBasico);

            Persona lucia = new Persona("Lucía");

            // Se actualizan ambos lados de la relación
            lucia.agregarCurso(javaBasico);

            em.persist(lucia);

            em.getTransaction().commit();

            System.out.println("PARTE B - CON HELPER");

            System.out.println("Cursos de Lucía: "
                    + lucia.getCursos().size());

            System.out.println("Inscriptos en Java Básico: "
                    + javaBasico.getInscriptos().size());

        } finally {
            em.close();
            emf.close();
        }
    }
}

/*
PARTE A:

La colección inscriptos aparece vacía porque solamente
modificamos el lado propietario de la relación.

JPA guarda la relación en la base de datos, pero no actualiza
automáticamente la colección inversa del objeto Curso que
ya se encuentra en memoria.

Si recuperáramos el curso utilizando un nuevo EntityManager,
JPA cargaría nuevamente la relación desde la base de datos
y la colección mostraría a Lucía como inscripta.

Esta inconsistencia puede generar errores en la lógica de una
aplicación si consultamos la colección inversa antes de
sincronizarla.

Por ejemplo, un sistema podría considerar que un curso no
tiene inscriptos cuando en realidad sí los tiene.


PARTE B:

El método agregarCurso() actualiza tanto la colección de
cursos de Persona como la colección de inscriptos de Curso.

La asignación directa solamente modificaba el lado propietario,
mientras que el helper sincroniza ambos lados en memoria.

JPA no actualiza automáticamente la colección inversa porque
la responsabilidad de administrar la relación corresponde
al lado propietario.

Esta diferencia es importante cuando la aplicación necesita
consultar ambas colecciones dentro de una misma transacción.

En aplicaciones de gestión de cursos, pedidos o inscripciones,
trabajar con colecciones inconsistentes podría provocar
decisiones incorrectas en la lógica del programa.
*/