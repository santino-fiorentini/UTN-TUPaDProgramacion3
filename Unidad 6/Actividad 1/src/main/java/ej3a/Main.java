package ej3a;

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
     * La implementación manual requiere muchas líneas de código
     * para crear constructores, getters, setters, toString(),
     * equals() y hashCode().
     *
     * El campo password no se incluye en toString() porque es
     * información sensible. Mostrarlo en consola podría exponer
     * accidentalmente una contraseña.
     *
     * equals() y hashCode() utilizan solamente el id porque el id
     * funciona como criterio de identidad del Cliente.
     */
}