package ar.edu.tup.programacion3.entities;

import ar.edu.tup.programacion3.enums.Estado;
import ar.edu.tup.programacion3.enums.FormaPago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Pedido extends Base implements Calculable {
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Usuario usuario;
    private List<DetallePedido> detalles;

    public Pedido(Long id, LocalDate fecha, Estado estado, FormaPago formaPago, Usuario usuario) {
        super(id);
        this.fecha = fecha;
        this.estado = estado;
        this.formaPago = formaPago;
        this.usuario = usuario;
        this.total = 0.0;
        this.detalles = new ArrayList<>();
    }

    @Override
    public void calcularTotal() {
        total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    public void addDetallePedido(Producto producto) {
        addDetallePedido(producto, 1);
    }

    public void addDetallePedido(Producto producto, int cantidad) {
        Optional<DetallePedido> detalleExistente = findDetallePedidoByProducto(producto);

        if (detalleExistente.isPresent()) {
            DetallePedido detalle = detalleExistente.get();
            detalle.setCantidad(detalle.getCantidad() + cantidad);
        } else {
            detalles.add(new DetallePedido(producto, cantidad));
        }
        calcularTotal();
    }

    public Optional<DetallePedido> findDetallePedidoByProducto(Producto producto) {
        return detalles.stream()
                .filter(detalle -> detalle.getProducto().getId().equals(producto.getId()))
                .findFirst();
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        detalles = detalles.stream()
                .filter(detalle -> !detalle.getProducto().getId().equals(producto.getId()))
                .collect(Collectors.toList());
        calcularTotal();
    }

    public int calcularCantidadItems() {
        return detalles.stream()
                .mapToInt(DetallePedido::getCantidad)
                .sum();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
        calcularTotal();
    }

    @Override
    public String toString() {
        return "Pedido #" + getId() + " - fecha: " + fecha
                + " - estado: " + estado + " - total: $"
                + String.format("%.2f", total);
    }
}
