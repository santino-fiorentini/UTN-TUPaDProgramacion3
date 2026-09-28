package Ejercicio3.ParteA;

public class ProductoRoto {

    private String codigo;
    private String nombre;
    private double precio;

    public ProductoRoto(String codigo, String nombre, double precio) {
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

        ProductoRoto producto = (ProductoRoto) o;

        return codigo.equals(producto.codigo);
    }
}