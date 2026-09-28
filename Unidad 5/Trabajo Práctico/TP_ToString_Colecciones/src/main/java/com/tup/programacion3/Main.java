package com.tup.programacion3;

import com.tup.programacion3.entities.Categoria;
import com.tup.programacion3.entities.Pedido;
import com.tup.programacion3.entities.Producto;
import com.tup.programacion3.entities.Usuario;
import com.tup.programacion3.enums.Estado;
import com.tup.programacion3.enums.FormaPago;
import com.tup.programacion3.enums.Rol;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // CREAR CATEGORIAS

        Categoria electronica = new Categoria(
                "Electronica",
                "Productos electronicos"
        );

        Categoria hogar = new Categoria(
                "Hogar",
                "Productos para el hogar"
        );

        Categoria oficina = new Categoria(
                "Oficina",
                "Productos para estudio y trabajo"
        );

        // CREAR 10 PRODUCTOS

        Set<Producto> productos = new HashSet<>();

        Producto p1 = new Producto("Notebook Lenovo", 850000.0,
                "Notebook para estudio", 10, "notebook.jpg", true, electronica);

        Producto p2 = new Producto("Mouse Logitech", 25000.0,
                "Mouse inalambrico", 20, "mouse.jpg", true, electronica);

        Producto p3 = new Producto("Teclado Redragon", 45000.0,
                "Teclado mecanico", 15, "teclado.jpg", true, electronica);

        Producto p4 = new Producto("Monitor Samsung", 220000.0,
                "Monitor de 24 pulgadas", 8, "monitor.jpg", true, electronica);

        Producto p5 = new Producto("Auriculares Sony", 70000.0,
                "Auriculares inalambricos", 12, "auriculares.jpg", true, electronica);

        Producto p6 = new Producto("Lampara LED", 18000.0,
                "Lampara de escritorio", 25, "lampara.jpg", true, hogar);

        Producto p7 = new Producto("Silla de escritorio", 160000.0,
                "Silla ergonomica", 5, "silla.jpg", true, oficina);

        Producto p8 = new Producto("Escritorio", 250000.0,
                "Escritorio para PC", 4, "escritorio.jpg", true, oficina);

        Producto p9 = new Producto("Cuaderno A4", 7000.0,
                "Cuaderno universitario", 50, "cuaderno.jpg", true, oficina);

        Producto p10 = new Producto("Mochila", 55000.0,
                "Mochila para notebook", 18, "mochila.jpg", true, oficina);

        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);
        productos.add(p6);
        productos.add(p7);
        productos.add(p8);
        productos.add(p9);
        productos.add(p10);

        // Relacion Categoria -> Productos
        electronica.agregarProducto(p1);
        electronica.agregarProducto(p2);
        electronica.agregarProducto(p3);
        electronica.agregarProducto(p4);
        electronica.agregarProducto(p5);

        hogar.agregarProducto(p6);

        oficina.agregarProducto(p7);
        oficina.agregarProducto(p8);
        oficina.agregarProducto(p9);
        oficina.agregarProducto(p10);

        // CREAR 2 USUARIOS

        Usuario usuario1 = new Usuario(
                "Santino",
                "Fiorentini",
                "sfiorentini@mail.com",
                "3415551111",
                "1234",
                Rol.USUARIO
        );

        Usuario usuario2 = new Usuario(
                "Lionel",
                "Messi",
                "lmessi@mail.com",
                "3415552222",
                "5678",
                Rol.ADMIN
        );

        // CREAR 3 PEDIDOS

        Pedido pedido1 = new Pedido(
                LocalDate.of(2026, 9, 20),
                Estado.CONFIRMADO,
                FormaPago.TARJETA
        );

        Pedido pedido2 = new Pedido(
                LocalDate.of(2026, 9, 21),
                Estado.PENDIENTE,
                FormaPago.TRANSFERENCIA
        );

        Pedido pedido3 = new Pedido(
                LocalDate.of(2026, 9, 22),
                Estado.TERMINADO,
                FormaPago.EFECTIVO
        );

        // Cada pedido tiene al menos 2 detalles
        pedido1.addDetallePedido(p1);
        pedido1.addDetallePedido(p2);

        pedido2.addDetallePedido(p3);
        pedido2.addDetallePedido(p6);

        pedido3.addDetallePedido(p7);
        pedido3.addDetallePedido(p9);

        // Usuario 1 tiene 2 pedidos.
        usuario1.agregarPedido(pedido1);
        usuario1.agregarPedido(pedido2);

        // Usuario 2 tiene 1 pedido.
        usuario2.agregarPedido(pedido3);

        // MOSTRAR UN PRODUCTO

        System.out.println("========== PRODUCTO 1 ==========");
        System.out.println(p1);

        // MOSTRAR TODOS LOS PRODUCTOS

        System.out.println("\n========== LISTADO DE PRODUCTOS ==========");

        for (Producto producto : productos) {
            System.out.println(producto);
        }

        // USUARIO CON MAS PEDIDOS

        Usuario usuarioConMasPedidos = usuario1;

        if (usuario2.getPedidos().size() > usuario1.getPedidos().size()) {
            usuarioConMasPedidos = usuario2;
        }

        System.out.println("\n========== USUARIO CON MAS PEDIDOS ==========");
        System.out.println(usuarioConMasPedidos);

        System.out.println("\nPedidos:");

        for (Pedido pedido : usuarioConMasPedidos.getPedidos()) {
            System.out.println(pedido);
        }

        // PRODUCTO DUPLICADO

        Producto productoDuplicado = new Producto(
                "Mouse Logitech",
                99999.0,
                "Otro mouse con el mismo nombre",
                1,
                "otro.jpg",
                false,
                electronica
        );

        System.out.println("\n========== COMPARACION DE PRODUCTO DUPLICADO ==========");
        System.out.println("Producto original: " + p2);
        System.out.println("Producto nuevo: " + productoDuplicado);
        System.out.println("equals: " + p2.equals(productoDuplicado));
        System.out.println("Mismo hashCode: " +
                (p2.hashCode() == productoDuplicado.hashCode()));
        System.out.println("La coleccion contiene al duplicado: " +
                productos.contains(productoDuplicado));

        boolean agregado = productos.add(productoDuplicado);

        System.out.println("¿Se pudo agregar a Set?: " + agregado);
        System.out.println("Cantidad de productos luego del intento: " + productos.size());
    }
}
