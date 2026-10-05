package ej1;

public class Main {

    public static void main(String[] args) {

        ProductoRecordDto producto = new ProductoRecordDto(
                1L,
                "Auriculares Bluetooth",
                45000.0
        );

        System.out.println("Nombre: " + producto.nombre());
        System.out.println("Precio: " + producto.precio());
        System.out.println("DTO completo: " + producto);
    }

    /*
     * 1. Los cuatro elementos principales que el compilador genera
     *    automáticamente son el constructor, los accessors, equals()
     *    y hashCode(). También genera toString().
     *
     * 2. El accessor se llama nombre() porque los records utilizan
     *    directamente el nombre del componente, sin el prefijo get.
     *
     * 3. El toString() de un record utiliza corchetes [] y muestra
     *    los nombres de los componentes. Un POJO puede tener un
     *    formato definido manualmente mediante @Override.
     */
}
