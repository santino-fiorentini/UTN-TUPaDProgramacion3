package ej1;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej1PU");

        // PARTE A - CAMBIO QUE NO SE GUARDA

        Long empleadoId;
        Empleado empleado = new Empleado("Laura", 50000.0);

        EntityManager em1 = emf.createEntityManager();

        try {
            em1.getTransaction().begin();

            em1.persist(empleado);

            em1.getTransaction().commit();

            empleadoId = empleado.getId();

        } finally {
            em1.close();
        }

        // El empleado ahora está DETACHED.
        empleado.setSalario(80000.0);

        EntityManager em2 = emf.createEntityManager();

        try {
            Empleado encontrado = em2.find(Empleado.class, empleadoId);

            System.out.println("PARTE A");
            System.out.println("Salario en base de datos: "
                    + encontrado.getSalario());

            // El objeto está DETACHED porque se cerró
            // el EntityManager que lo administraba.
            // Por eso, el cambio de salario no se guarda.

        } finally {
            em2.close();
        }

        // PARTE B - UTILIZANDO MERGE()

        Empleado empleadoB = new Empleado("Laura", 50000.0);

        EntityManager em3 = emf.createEntityManager();

        try {
            em3.getTransaction().begin();

            em3.persist(empleadoB);

            em3.getTransaction().commit();

        } finally {
            em3.close();
        }

        // Modificamos el objeto DETACHED.
        empleadoB.setSalario(80000.0);

        EntityManager em4 = emf.createEntityManager();

        try {
            em4.getTransaction().begin();

            // Reasociamos el estado del objeto mediante merge().
            Empleado managed = em4.merge(empleadoB);

            em4.getTransaction().commit();

            System.out.println("\nPARTE B");
            System.out.println("¿Son la misma instancia? "
                    + (empleadoB == managed));

            Empleado encontrado = em4.find(
                    Empleado.class, empleadoB.getId());

            System.out.println("Salario en base de datos: "
                    + encontrado.getSalario());

            // merge() devuelve una nueva instancia administrada.
            // El objeto original continúa DETACHED.

        } finally {
            em4.close();
            emf.close();
        }
    }
}