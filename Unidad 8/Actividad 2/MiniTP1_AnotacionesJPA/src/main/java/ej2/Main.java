package ej2;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("pruebaJPAPU");

        EntityManager em = emf.createEntityManager();

        try {

            Producto producto1 = new Producto(
                    "Teclado mecánico",
                    "KB-001",
                    EstadoProducto.ACTIVO,
                    25
            );

            Producto producto2 = new Producto(
                    "Mouse inalámbrico",
                    "MS-002",
                    EstadoProducto.PENDIENTE,
                    10
            );

            Producto producto3 = new Producto(
                    "Auriculares USB",
                    "AU-003",
                    EstadoProducto.INACTIVO,
                    0
            );

            em.getTransaction().begin();

            em.persist(producto1);
            em.persist(producto2);
            em.persist(producto3);

            em.getTransaction().commit();

            em.clear();

            Producto encontrado1 =
                    em.find(Producto.class, producto1.getId());

            Producto encontrado2 =
                    em.find(Producto.class, producto2.getId());

            Producto encontrado3 =
                    em.find(Producto.class, producto3.getId());

            System.out.println("Producto 1: " +
                    encontrado1.getNombre() +
                    " | Estado: " + encontrado1.getEstado());

            System.out.println("Producto 2: " +
                    encontrado2.getNombre() +
                    " | Estado: " + encontrado2.getEstado());

            System.out.println("Producto 3: " +
                    encontrado3.getNombre() +
                    " | Estado: " + encontrado3.getEstado());

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
         * Si utilizáramos EnumType.ORDINAL, los estados se guardarían
         * mediante números según su posición en el enum:
         * ACTIVO = 0, INACTIVO = 1 y PENDIENTE = 2.
         *
         * El problema es que, si modificamos el orden o agregamos
         * un nuevo estado entre los existentes, los números anteriores
         * podrían representar otros estados.
         *
         * EnumType.STRING evita este problema porque guarda directamente
         * el nombre del estado como texto, independientemente de su posición.
         *
         * Aunque almacenar texto ocupa más espacio que almacenar números,
         * facilita la lectura de los datos y evita depender del orden del enum.
         */
    }
}