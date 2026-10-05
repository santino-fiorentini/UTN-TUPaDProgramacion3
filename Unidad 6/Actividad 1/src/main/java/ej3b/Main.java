package ej3b;

public class Main {

    public static void main(String[] args) {

        Cliente cliente1 = new Cliente(
                1L,
                "Santino Fiorentini",
                "sfiorentini@mail.com",
                "123456"
        );

        Cliente cliente2 = new Cliente(
                1L,
                "Santino Fiorentini",
                "sf@empresa.com",
                "abcdef"
        );

        System.out.println(cliente1);
        System.out.println(cliente2);

        System.out.println("¿Son iguales? " + cliente1.equals(cliente2));
    }

    /*
     * La implementación con Lombok produce el mismo comportamiento
     * que la implementación manual, pero requiere muchas menos líneas
     * de código.
     *
     * @NoArgsConstructor genera el constructor vacío.
     * @AllArgsConstructor genera el constructor con todos los atributos.
     * @Getter y @Setter generan los métodos de acceso.
     * @ToString(exclude = "password") evita mostrar la contraseña.
     * @EqualsAndHashCode(of = "id") hace que la igualdad se base
     * solamente en el id.
     *
     * Lombok resulta útil cuando tenemos clases con mucho código
     * repetitivo porque reduce la cantidad de código que debemos
     * escribir y mantener.
     *
     * @EqualsAndHashCode puede utilizarse cuando todos los atributos
     * forman parte del criterio de igualdad.
     *
     * @EqualsAndHashCode(of = "id") conviene cuando existe un campo
     * que representa la identidad o clave de negocio del objeto.
     */
}