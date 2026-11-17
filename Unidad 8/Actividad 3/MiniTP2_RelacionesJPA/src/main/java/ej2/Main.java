package ej2;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej2PU");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Creamos y persistimos el departamento.
            Departamento departamento =
                    new Departamento("Tecnología");

            em.persist(departamento);

            // Creamos el empleado y le asignamos el departamento.
            Empleado empleado =
                    new Empleado("Carlos", departamento);

            em.persist(empleado);

            em.getTransaction().commit();

            // Recuperamos el empleado.
            Empleado encontrado =
                    em.find(Empleado.class, empleado.getId());

            System.out.println("Empleado: "
                    + encontrado.getNombre()
                    + " | Departamento: "
                    + encontrado.getDepartamento().getNombre());

        } finally {
            em.close();
            emf.close();
        }
    }
}