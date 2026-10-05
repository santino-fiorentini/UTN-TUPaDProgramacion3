package ej2;

public class ProductoDTO {

    private Long id;
    private String nombre;
    private double precio;
    private String categoria;

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
        this.categoria = producto.getCategoria();
    }

    public static ProductoDTO fromEntity(Producto producto) {
        return new ProductoDTO(producto);
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}