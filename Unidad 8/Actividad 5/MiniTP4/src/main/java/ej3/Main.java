package ej3;

import jakarta.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej3PU");

        EntityManager em = emf.createEntityManager();

        try {

            // CREAR Y GUARDAR LOS DATOS

            em.getTransaction().begin();

            em.persist(new Persona("Homero", "Springfield", 39));
            em.persist(new Persona("Marge", "Springfield", 36));
            em.persist(new Persona("Ned", "Springfield", 40));
            em.persist(new Persona("Burns", "Shelbyville", 104));

            em.getTransaction().commit();

            // PARTE A: CONCATENACION INSEGURA

            System.out.println("-- PARTE A: CONCATENACION");

            String ciudadFiltro = "Springfield";

            List<Persona> resultado = em.createQuery(
                    "SELECT p FROM Persona p WHERE p.ciudad = '"
                            + ciudadFiltro + "'",
                    Persona.class
            ).getResultList();

            System.out.println("Personas encontradas: "
                    + resultado.size());

            // Probamos el ataque de inyección

            ciudadFiltro = "' OR '1'='1";

            resultado = em.createQuery(
                    "SELECT p FROM Persona p WHERE p.ciudad = '"
                            + ciudadFiltro + "'",
                    Persona.class
            ).getResultList();

            System.out.println("Con ataque de inyección: "
                    + resultado.size());

            /*
             PARTE A:

             Al ingresar "' OR '1'='1", la consulta se modifica
             y termina con la condición:

             WHERE p.ciudad = '' OR '1'='1'

             La expresión '1'='1' siempre es verdadera, por lo
             tanto devuelve todos los registros.

             JPA traduce la consulta JPQL a SQL y la condición
             introducida pasa a formar parte de la consulta.

             En una aplicación real, un atacante podría modificar
             las condiciones de una consulta para acceder a
             información que no debería estar disponible.
            */

            // PARTE B: PARAMETROS NOMBRADOS

            System.out.println("\n-- PARTE B: PARAMETROS NOMBRADOS");

            ciudadFiltro = "Springfield";

            resultado = em.createQuery(
                            "SELECT p FROM Persona p "
                                    + "WHERE p.ciudad = :ciudad "
                                    + "ORDER BY p.nombre ASC",
                            Persona.class
                    ).setParameter("ciudad", ciudadFiltro)
                    .getResultList();

            System.out.println("Personas en Springfield:");

            for (Persona p : resultado) {
                System.out.println("- " + p.getNombre()
                        + " (" + p.getEdad() + " años)");
            }

            System.out.println("Total: " + resultado.size());

            // PROBAR NUEVAMENTE EL ATAQUE

            ciudadFiltro = "' OR '1'='1";

            resultado = em.createQuery(
                            "SELECT p FROM Persona p WHERE p.ciudad = :ciudad",
                            Persona.class
                    ).setParameter("ciudad", ciudadFiltro)
                    .getResultList();

            System.out.println("Con parámetros y ataque: "
                    + resultado.size());

            // CONSULTA COUNT CON GETSINGLERESULT

            Long cantidad = em.createQuery(
                            "SELECT COUNT(p) FROM Persona p "
                                    + "WHERE p.edad > :edadMin",
                            Long.class
                    ).setParameter("edadMin", 39)
                    .getSingleResult();

            System.out.println("Personas mayores de 39 años: "
                    + cantidad);

        } finally {
            em.close();
            emf.close();
        }

        /*
         PARTE B:

         getResultList() devuelve una lista con cero o más
         resultados. Si no encuentra registros, devuelve una
         lista vacía.

         getSingleResult() devuelve exactamente un resultado.
         Si no encuentra ninguno lanza NoResultException y si
         encuentra más de uno lanza NonUniqueResultException.

         JPQL utiliza nombres de entidades y atributos Java,
         mientras que SQL utiliza nombres de tablas y columnas.

         Los parámetros nombrados permiten enviar valores
         mediante setParameter(), evitando que los datos
         ingresados por el usuario modifiquen la consulta.

         Además, mejoran la legibilidad, el mantenimiento
         y la reutilización de las consultas.
        */
    }
}