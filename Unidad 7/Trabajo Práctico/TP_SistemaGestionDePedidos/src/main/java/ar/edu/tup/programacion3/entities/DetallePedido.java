package ar.edu.tup.programacion3.entities;

public class DetallePedido {
    private int cantidad;
    private Double subtotal;
    private Producto producto;

    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public void calcularSubtotal() {
        if (producto == null) {
            subtotal = 0.0;
        } else {
            subtotal = producto.getPrecio() * cantidad;
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        calcularSubtotal();
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x " + cantidad + " = $"
                + String.format("%.2f", subtotal);
    }
}
