package ej3a;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("pruebaJPAPU");

        EntityManager em = emf.createEntityManager();

        try {

            Articulo articulo = new Articulo(
                    "ART-001",
                    "Monitor curvo 32 pulgadas",
                    89999.99
            );

            em.getTransaction().begin();

            em.persist(articulo);

            em.getTransaction().commit();

            System.out.println("Artículo guardado con ID: " +
                    articulo.getId());

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
         * Hibernate generó automáticamente el nombre ARTICULO
         * para la tabla y los nombres de sus columnas a partir
         * de los atributos definidos en Java.
         *
         * Los nombres de la base de datos están relacionados
         * directamente con los nombres de la clase y sus atributos.
         *
         * El resultado puede variar según la estrategia de nombres
         * configurada en Hibernate.
         *
         * Estos nombres pueden resultar poco claros al escribir
         * consultas SQL o integrar la base de datos con otras
         * herramientas y aplicaciones.
         */
    }
}