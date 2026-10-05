package ej1;

public class Main {

    public static void main(String[] args) {

        Libro libro = new Libro(
                1L,
                "El Alquimista",
                "Paulo Coelho",
                "978-0062315007",
                1500.0,
                42
        );

        LibroDTO libroDTO = new LibroDTO(libro);

        System.out.println("=== Entidad completa ===");
        System.out.println(libro);
        System.out.println();
        System.out.println("=== DTO expuesto ===");
        System.out.println(libroDTO);
    }

    /*
     * Un DTO es un objeto que permite transportar únicamente los datos
     * necesarios entre las diferentes capas de una aplicación.
     *
     * LibroDTO no incluye costoAdquisicion ni stockInterno porque son
     * datos internos de la gestión de la biblioteca que no necesitan
     * ser expuestos al cliente.
     *
     * Si se expusiera directamente la entidad Libro mediante una API,
     * podrían mostrarse datos internos o sensibles que no deberían
     * estar disponibles para el usuario externo.
     */
}