package ej2;

public class Main {

    public static void main(String[] args) {

        Pedido pedido1 = new Pedido(
                1L,
                "Compra de insumos",
                3200.0
        );

        Pedido pedido2 = new Pedido(
                1L,
                "Compra de insumos",
                3200.0
        );

        Pedido pedido3 = new Pedido(
                2L,
                "Mantenimiento",
                8750.0
        );

        System.out.println(pedido1);
        System.out.println(pedido2);
        System.out.println(pedido3);

        System.out.println(pedido1.equals(pedido2));
        System.out.println(pedido1.equals(pedido3));
    }

    /*
     * pedido1 == pedido2 daría false porque == compara referencias
     * de memoria. Aunque los objetos tengan los mismos datos,
     * son dos objetos diferentes.
     *
     * En cambio, equals() generado por @EqualsAndHashCode compara
     * el contenido de los atributos de los objetos.
     *
     * Por eso pedido1.equals(pedido2) da true.
     *
     * Si no utilizáramos @EqualsAndHashCode, Pedido heredaría
     * equals() de Object, que compara referencias. En ese caso
     * pedido1.equals(pedido2) también daría false.
     */
}