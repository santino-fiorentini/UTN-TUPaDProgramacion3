package ej3b;

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
         * Utilizar @Table y @Column permite definir explícitamente
         * los nombres de las tablas y columnas de la base de datos.
         *
         * El nombre del atributo Java puede ser diferente del nombre
         * de la columna SQL, por ejemplo codigoInterno y CODIGO.
         *
         * Esto facilita la escritura de consultas SQL, la creación
         * de reportes y la integración con otras aplicaciones.
         *
         * La configuración explícita también permite mantener
         * nombres previsibles en el esquema, incluso si cambia
         * la estrategia de nombres utilizada por el proveedor JPA.
         */
    }
}