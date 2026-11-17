package ej3;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej3PU");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // FACTURA F-001

            ItemFactura i1 =
                    new ItemFactura("Notebook", 85000.0);

            ItemFactura i2 =
                    new ItemFactura("Mouse", 3500.0);

            // Primero persistimos los ítems.
            em.persist(i1);
            em.persist(i2);

            Factura f1 = new Factura("F-001");

            f1.getItems().add(i1);
            f1.getItems().add(i2);

            // Después persistimos la factura.
            em.persist(f1);

            // FACTURA F-002

            ItemFactura i3 =
                    new ItemFactura("Monitor", 45000.0);

            em.persist(i3);

            Factura f2 = new Factura("F-002");

            f2.getItems().add(i3);

            em.persist(f2);

            // FACTURA F-003

            ItemFactura i4 =
                    new ItemFactura("Teclado mecánico", 12000.0);

            ItemFactura i5 =
                    new ItemFactura("Auriculares", 8000.0);

            em.persist(i4);
            em.persist(i5);

            Factura f3 = new Factura("F-003");

            f3.getItems().add(i4);
            f3.getItems().add(i5);

            em.persist(f3);

            em.getTransaction().commit();

            // RECUPERAR Y MOSTRAR LAS FACTURAS

            Factura factura1 =
                    em.find(Factura.class, f1.getId());

            Factura factura2 =
                    em.find(Factura.class, f2.getId());

            Factura factura3 =
                    em.find(Factura.class, f3.getId());

            mostrarFactura(factura1);
            mostrarFactura(factura2);
            mostrarFactura(factura3);

        } finally {
            em.close();
            emf.close();
        }
    }

    public static void mostrarFactura(Factura factura) {

        System.out.println("\nFactura " + factura.getNumero() + ":");

        for (ItemFactura item : factura.getItems()) {

            System.out.println("  - "
                    + item.getDescripcion()
                    + ": $"
                    + item.getPrecio());
        }
    }
}