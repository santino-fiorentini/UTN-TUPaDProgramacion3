package ej1;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej1PU");

        Long domicilioId;
        Long personaId;

        // TRANSACCION 1: CASCADE PERSIST

        EntityManager em1 = emf.createEntityManager();

        try {
            em1.getTransaction().begin();

            Domicilio d = new Domicilio(
                    "Av. Brasil 123",
                    "Buenos Aires"
            );

            Persona p = new Persona("Dori");
            p.setDomicilio(d);

            // Solo persistimos Persona.
            // El domicilio se guarda por CascadeType.PERSIST.
            em1.persist(p);

            em1.getTransaction().commit();

            domicilioId = d.getId();
            personaId = p.getId();

            Persona personaBuscada =
                    em1.find(Persona.class, personaId);

            System.out.println("-- Transacción 1");
            System.out.println("Persona guardada: "
                    + personaBuscada.getNombre()
                    + " (ID: " + personaBuscada.getId() + ")");

            System.out.println("Domicilio guardado por cascade: "
                    + personaBuscada.getDomicilio()
                    + " (ID: " + domicilioId + ")");

        } finally {
            em1.close();
        }

        // TRANSACCION 2: ORPHAN REMOVAL

        EntityManager em2 = emf.createEntityManager();

        try {
            em2.getTransaction().begin();

            Persona persona =
                    em2.find(Persona.class, personaId);

            persona.setDomicilio(null);

            // Se incluye merge como solicita el enunciado.
            em2.merge(persona);

            em2.getTransaction().commit();

            Domicilio domicilioBuscado =
                    em2.find(Domicilio.class, domicilioId);

            System.out.println("\n-- Transacción 2");

            System.out.println("Domicilio buscado: "
                    + domicilioBuscado);

            if (domicilioBuscado == null) {
                System.out.println("El domicilio fue eliminado automáticamente.");
            }

        } finally {
            em2.close();
            emf.close();
        }

        /*
         CascadeType.REMOVE elimina las entidades relacionadas
         cuando se elimina la entidad principal mediante remove().

         En cambio, orphanRemoval elimina una entidad cuando deja
         de estar relacionada con su entidad principal, incluso
         si no eliminamos la entidad principal.

         CascadeType.ALL puede ser peligroso porque incluye REMOVE.
         Si una entidad relacionada es compartida por varias
         entidades principales, podríamos eliminar información
         que todavía está siendo utilizada.

         Conviene configurar cascade en el lado propietario,
         que es el que tiene @JoinColumn, para controlar correctamente
         las operaciones sobre la relación.

         CascadeType.REMOVE tiene sentido cuando queremos eliminar
         la entidad relacionada al eliminar la principal, pero no
         necesariamente cuando quitamos su referencia.

         orphanRemoval tiene sentido cuando la entidad relacionada
         depende exclusivamente de la principal y debe eliminarse
         cuando deja de pertenecerle.
        */
    }
}