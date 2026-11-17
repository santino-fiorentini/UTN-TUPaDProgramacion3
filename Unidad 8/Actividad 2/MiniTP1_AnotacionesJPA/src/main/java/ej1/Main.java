package ej1;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("pruebaJPAPU");

        EntityManager em = emf.createEntityManager();

        try {

            Persona persona = new Persona("Ana López");

            em.getTransaction().begin();

            em.persist(persona);

            em.getTransaction().commit();

            System.out.println("Persona guardada con ID: " + persona.getId());

            em.clear();

            Persona personaEncontrada = em.find(Persona.class, persona.getId());

            System.out.println("Persona encontrada: " +
                    personaEncontrada.getNombre());

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
            emf.close();
        }

        /*
         * Cuando ejecutamos persist(), el objeto pasa a estar gestionado
         * por el contexto de persistencia de JPA. Esto significa que Hibernate
         * controla sus cambios y puede sincronizarlos con la base de datos.
         *
         * El atributo id comienza en null porque todavía no fue generado.
         * Al utilizar GenerationType.IDENTITY, la base de datos genera
         * el identificador cuando se ejecuta el INSERT y Hibernate lo
         * asigna al objeto.
         *
         * Mientras el objeto permanece gestionado, sus modificaciones
         * pueden sincronizarse automáticamente con la base de datos.
         *
         * Utilizamos em.clear() para desvincular los objetos del contexto
         * de persistencia. De esta manera, find() debe recuperar nuevamente
         * la persona desde la base de datos, en lugar de utilizar el objeto
         * que ya estaba gestionado.
         */
    }
}
