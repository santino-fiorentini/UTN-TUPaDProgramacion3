package com.tup.programacion3.entities;

import java.util.Objects;

public class DetallePedido extends Base {
    private int cantidad;
    private Double subtotal;
    private Producto producto;

    public DetallePedido(Producto producto, int cantidad) {
        super();
        this.producto = producto;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    private void calcularSubtotal() {
        if (producto != null) {
            subtotal = cantidad * producto.getPrecio();
        } else {
            subtotal = 0.0;
        }
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "id=" + getId() +
                ", producto='" + (producto != null ? producto.getNombre() : "Sin producto") + '\'' +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DetallePedido detalle = (DetallePedido) o;
        return Objects.equals(producto, detalle.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto);
    }
}
