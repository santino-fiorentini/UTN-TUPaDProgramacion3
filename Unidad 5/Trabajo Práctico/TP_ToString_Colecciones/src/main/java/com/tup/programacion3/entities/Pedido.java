package com.tup.programacion3.entities;

import com.tup.programacion3.enums.Estado;
import com.tup.programacion3.enums.FormaPago;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pedido extends Base implements Calculable {
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Set<DetallePedido> detalles;

    public Pedido(LocalDate fecha, Estado estado, FormaPago formaPago) {
        super();
        this.fecha = fecha;
        this.estado = estado;
        this.formaPago = formaPago;
        this.total = 0.0;
        this.detalles = new HashSet<>();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public Double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public Set<DetallePedido> getDetalles() {
        return detalles;
    }

    public void addDetallePedido(Producto producto) {
        if (producto != null) {
            DetallePedido detalleExistente = findDetallePedidoByProducto(producto);

            if (detalleExistente == null) {
                detalles.add(new DetallePedido(producto, 1));
            }
        }

        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }

        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalle = findDetallePedidoByProducto(producto);

        if (detalle != null) {
            detalles.remove(detalle);
        }

        calcularTotal();
    }

    @Override
    public void calcularTotal() {
        total = 0.0;

        for (DetallePedido detalle : detalles) {
            total += detalle.getSubtotal();
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + getId() +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", total=" + total +
                ", formaPago=" + formaPago +
                ", detalles=" + detalles +
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

        Pedido pedido = (Pedido) o;
        return Objects.equals(getId(), pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
