package com.utn.entities;

import com.utn.enums.Estado;
import com.utn.enums.FormaPago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Pedido extends Base implements Calculable {

    private LocalDateTime fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<DetallePedido> detalles = new LinkedHashSet<>();

    public void addDetallePedido(int cantidad, Producto producto) {

        if (producto == null || cantidad <= 0) {
            return;
        }

        DetallePedido detalle = DetallePedido.builder()
                .cantidad(cantidad)
                .producto(producto)
                .subtotal(cantidad * producto.getPrecio())
                .build();

        detalles.add(detalle);

        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {

        if (producto == null) {
            return null;
        }

        for (DetallePedido detalle : detalles) {

            if (producto.equals(detalle.getProducto())) {
                return detalle;
            }
        }

        return null;
    }

    public void deleteDetallePedido(Producto producto) {

        if (producto == null) {
            return;
        }

        Iterator<DetallePedido> iterator = detalles.iterator();

        while (iterator.hasNext()) {

            DetallePedido detalle = iterator.next();

            if (producto.equals(detalle.getProducto())) {
                iterator.remove();
            }
        }

        calcularTotal();
    }

    @Override
    public void calcularTotal() {

        double suma = 0.0;

        for (DetallePedido detalle : detalles) {

            if (detalle != null && detalle.getSubtotal() != null) {
                suma += detalle.getSubtotal();
            }
        }

        total = suma;
    }
}