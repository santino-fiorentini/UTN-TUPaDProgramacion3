package Ejercicio3.ParteB;

import java.util.Objects;

public class ProductoCorregido {

    private String codigo;
    private String nombre;
    private double precio;

    public ProductoCorregido(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductoCorregido producto = (ProductoCorregido) o;

        return Objects.equals(codigo, producto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}