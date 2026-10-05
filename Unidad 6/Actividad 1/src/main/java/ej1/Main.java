package ej1;

public class Main {

    public static void main(String[] args) {

        Producto producto1 = new Producto();

        producto1.setId(1L);
        producto1.setNombre("Notebook");
        producto1.setPrecio(1500.0);

        producto1.setNombre("Auriculares Bluetooth");

        System.out.println(producto1);

        Producto producto2 = new Producto(
                2L,
                "Mouse",
                800.0
        );

        System.out.println(producto2);
    }

    /*
     * @NoArgsConstructor genera un constructor sin parámetros.
     * @AllArgsConstructor genera un constructor con todos los atributos.
     * @Getter genera los métodos get de los atributos.
     * @Setter genera los métodos set de los atributos.
     * @ToString genera automáticamente el método toString().
     *
     * La diferencia entre @NoArgsConstructor y @AllArgsConstructor
     * es que el primero permite crear un objeto sin proporcionar valores,
     * mientras que el segundo requiere un valor para cada atributo.
     *
     * Si elimináramos @ToString, System.out.println(producto1)
     * utilizaría el toString() heredado de Object y mostraría algo
     * similar a Producto@1a2b3c4d en lugar de mostrar los atributos.
     */
}