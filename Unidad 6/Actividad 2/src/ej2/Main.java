package ej2;

public class Main {

    public static void main(String[] args) {

        Producto producto1 = new Producto(
                1L,
                "Notebook Lenovo",
                850000.0,
                "Electrónica",
                600000.0,
                41.7
        );

        Producto producto2 = new Producto(
                2L,
                "Auriculares Sony",
                45000.0,
                "Electrónica",
                20000.0,
                125.0
        );

        Producto producto3 = new Producto(
                3L,
                "Silla Ergonómica",
                120000.0,
                "Muebles",
                75000.0,
                60.0
        );

        Producto producto4 = new Producto(
                4L,
                "Lámpara de escritorio",
                18500.0,
                "Iluminación",
                8000.0,
                131.25
        );

        ProductoDTO dto1 = ProductoDTO.fromEntity(producto1);
        ProductoDTO dto2 = ProductoDTO.fromEntity(producto2);
        ProductoDTO dto3 = ProductoDTO.fromEntity(producto3);
        ProductoDTO dto4 = ProductoDTO.fromEntity(producto4);

        System.out.println(dto1);
        System.out.println(dto2);
        System.out.println(dto3);
        System.out.println(dto4);
    }

    /*
     * Centralizar la conversión en fromEntity() permite reutilizar
     * la misma lógica cada vez que necesitamos convertir un Producto
     * en ProductoDTO. Esto evita repetir manualmente la asignación
     * de atributos en diferentes lugares del programa.
     *
     * costoProduccion y margenGanancia no se incluyen en la respuesta
     * pública porque son datos internos del negocio. Exponerlos podría
     * revelar información comercial que no debería conocer el cliente.
     */
}