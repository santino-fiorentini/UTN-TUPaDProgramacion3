package ar.edu.tup.programacion3;

import ar.edu.tup.programacion3.entities.Categoria;
import ar.edu.tup.programacion3.entities.Pedido;
import ar.edu.tup.programacion3.entities.Producto;
import ar.edu.tup.programacion3.entities.Usuario;
import ar.edu.tup.programacion3.enums.Estado;
import ar.edu.tup.programacion3.enums.FormaPago;
import ar.edu.tup.programacion3.enums.Rol;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Categoria comidas = new Categoria(1L, "Comidas", "Comidas rápidas");
        Categoria bebidas = new Categoria(2L, "Bebidas", "Bebidas frías");

        Producto pancho = new Producto(
                1L, "Pancho", 2000.0, "Pancho clásico", 10, "pancho.jpg", true, comidas);
        Producto hamburguesa = new Producto(
                2L, "Hamburguesa", 3500.0, "Hamburguesa completa", 3, "hamburguesa.jpg", true, comidas);
        Producto gaseosa = new Producto(
                3L, "Gaseosa", 1000.0, "Bebida de 500 ml", 2, "gaseosa.jpg", true, bebidas);
        Producto agua = new Producto(
                4L, "Agua", 800.0, "Agua mineral", 0, "agua.jpg", false, bebidas);
        Producto papas = new Producto(
                5L, "Papas fritas", 1500.0, "Porción de papas", 4, "papas.jpg", true, comidas);

        List<Producto> productos = List.of(pancho, hamburguesa, gaseosa, agua, papas);

        Usuario usuario = new Usuario(
                1L, "Santino", "Fiorentini", "santino@email.com",
                "3464-000000", "clave123", Rol.USUARIO);

        Pedido pedido = new Pedido(
                1L, LocalDate.now(), Estado.PENDIENTE, FormaPago.EFECTIVO, usuario);

        pedido.addDetallePedido(pancho, 2);
        pedido.addDetallePedido(gaseosa, 2);

        System.out.println("===== SISTEMA DE GESTIÓN DE PEDIDOS =====");

        System.out.println("\n1) PRODUCTOS DISPONIBLES");
        productos.stream()
                .filter(Producto::isDisponible)
                .forEach(System.out::println);

        System.out.println("\n2) CANTIDAD TOTAL DE ÍTEMS DEL PEDIDO");
        System.out.println("Cantidad de ítems: " + pedido.calcularCantidadItems());

        System.out.println("\n3) PRODUCTOS CON STOCK MENOR A 5");
        productos.stream()
                .filter(producto -> producto.getStock() < 5)
                .forEach(System.out::println);

        System.out.println("\n4) TOTAL DEL PEDIDO");
        pedido.calcularTotal();
        pedido.getDetalles().stream()
                .forEach(System.out::println);
        System.out.println("Total del pedido: $" + String.format("%.2f", pedido.getTotal()));
    }
}
