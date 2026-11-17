package ej1;

import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ej1PU");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Crear y persistir domicilios
            Domicilio d1 = new Domicilio(
                    "Corrientes 1234", "Buenos Aires");

            Domicilio d2 = new Domicilio(
                    "San Martín 567", "Córdoba");

            em.persist(d1);
            em.persist(d2);

            // Crear personas y asignar domicilios
            Persona ana = new Persona("Ana");
            ana.setDomicilio(d1);

            Persona luis = new Persona("Luis");
            luis.setDomicilio(d2);

            em.persist(ana);
            em.persist(luis);

            em.getTransaction().commit();

            // Recuperar personas
            Persona personaAna = em.find(Persona.class, ana.getId());
            Persona personaLuis = em.find(Persona.class, luis.getId());

            System.out.println("Persona: " +
                    personaAna.getNombre() +
                    " | Domicilio: " +
                    personaAna.getDomicilio().getCalle() +
                    ", " +
                    personaAna.getDomicilio().getCiudad());

            System.out.println("Persona: " +
                    personaLuis.getNombre() +
                    " | Domicilio: " +
                    personaLuis.getDomicilio().getCalle() +
                    ", " +
                    personaLuis.getDomicilio().getCiudad());

        } finally {
            em.close();
            emf.close();
        }
    }
}

/*
La clave foránea DOMICILIO_ID quedó almacenada en la tabla
PERSONAS porque Persona es el lado propietario de la relación.

El atributo unique = true garantiza que un domicilio no pueda
ser asignado a dos personas diferentes.

Si intentáramos utilizar el mismo domicilio para dos personas,
la base de datos rechazaría la operación por la restricción UNIQUE.

La relación es administrada por Persona, ya que es la entidad
que declara @OneToOne y @JoinColumn.

Una FK normal permite que varias filas apunten al mismo registro.
En cambio, una FK con UNIQUE impide que se repita el valor
referenciado en distintas filas.

En H2 se verificó que DOMICILIO_ID está en PERSONAS y que
DOMICILIOS no contiene una FK hacia PERSONAS.
*/