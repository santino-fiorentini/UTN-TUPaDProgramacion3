package ej4;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej4PU");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Creamos el departamento.
            Departamento departamento =
                    new Departamento("Tecnología");

            // Creamos los empleados.
            Empleado ana = new Empleado("Ana", null);
            Empleado marcos = new Empleado("Marcos", null);

            // Agregamos los empleados mediante el helper.
            departamento.addEmpleado(ana);
            departamento.addEmpleado(marcos);

            // Persistimos las entidades.
            em.persist(departamento);
            em.persist(ana);
            em.persist(marcos);

            em.getTransaction().commit();

            // Recuperamos el departamento.
            Departamento encontrado =
                    em.find(Departamento.class, departamento.getId());

            System.out.println("Departamento: "
                    + encontrado.getNombre());

            System.out.println("Empleados:");

            for (Empleado empleado : encontrado.getEmpleados()) {
                System.out.println("  - " + empleado.getNombre());
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}