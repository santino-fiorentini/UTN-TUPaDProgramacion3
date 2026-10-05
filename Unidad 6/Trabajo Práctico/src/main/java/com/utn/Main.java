package com.utn;

import com.utn.dtos.UsuarioDTO;
import com.utn.entities.Categoria;
import com.utn.entities.Pedido;
import com.utn.entities.Producto;
import com.utn.entities.Usuario;
import com.utn.enums.Estado;
import com.utn.enums.FormaPago;
import com.utn.enums.Rol;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {


        // 1. CATEGORIAS

        Categoria tecnologia = Categoria.builder()
                .id(1L)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .nombre("Tecnología")
                .descripcion("Productos tecnológicos")
                .build();

        Categoria hogar = Categoria.builder()
                .id(2L)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .nombre("Hogar")
                .descripcion("Productos para el hogar")
                .build();

        Categoria deportes = Categoria.builder()
                .id(3L)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .nombre("Deportes")
                .descripcion("Productos deportivos")
                .build();


        // 2. PRODUCTOS

        Producto p1 = producto(
                1L,
                "Notebook Lenovo",
                850000.0,
                5,
                tecnologia
        );

        Producto p2 = producto(
                2L,
                "Mouse Logitech",
                35000.0,
                20,
                tecnologia
        );

        Producto p3 = producto(
                3L,
                "Teclado Mecánico",
                70000.0,
                15,
                tecnologia
        );

        Producto p4 = producto(
                4L,
                "Monitor 24 pulgadas",
                180000.0,
                8,
                tecnologia
        );

        Producto p5 = producto(
                5L,
                "Silla de escritorio",
                150000.0,
                10,
                hogar
        );

        Producto p6 = producto(
                6L,
                "Lámpara LED",
                30000.0,
                25,
                hogar
        );

        Producto p7 = producto(
                7L,
                "Auriculares",
                60000.0,
                12,
                tecnologia
        );

        Producto p8 = producto(
                8L,
                "Pelota de fútbol",
                25000.0,
                30,
                deportes
        );

        Producto p9 = producto(
                9L,
                "Mancuernas",
                90000.0,
                6,
                deportes
        );

        Producto p10 = producto(
                10L,
                "Botella deportiva",
                18000.0,
                40,
                deportes
        );


        // Asociamos productos con categorías

        tecnologia.agregarProducto(p1);
        tecnologia.agregarProducto(p2);
        tecnologia.agregarProducto(p3);
        tecnologia.agregarProducto(p4);
        tecnologia.agregarProducto(p7);

        hogar.agregarProducto(p5);
        hogar.agregarProducto(p6);

        deportes.agregarProducto(p8);
        deportes.agregarProducto(p9);
        deportes.agregarProducto(p10);


        // 3. USUARIOS

        Usuario usuario1 = Usuario.builder()
                .id(1L)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .nombre("Santino")
                .apellido("Fiorentini")
                .mail("sfiorentini@gmail.com")
                .celular("3464000000")
                .contraseña("123456")
                .rol(Rol.USUARIO)
                .build();

        Usuario usuario2 = Usuario.builder()
                .id(2L)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .nombre("Lionel")
                .apellido("Messi")
                .mail("lmessi@gmail.com")
                .celular("3410000000")
                .contraseña("abcdef")
                .rol(Rol.USUARIO)
                .build();


        // 4. PEDIDO 1

        Pedido pedido1 = Pedido.builder()
                .id(1L)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .fecha(LocalDateTime.now())
                .estado(Estado.CONFIRMADO)
                .formaPago(FormaPago.TARJETA)
                .build();

        pedido1.addDetallePedido(1, p1);
        pedido1.addDetallePedido(2, p2);


        // 5. PEDIDO 2

        Pedido pedido2 = Pedido.builder()
                .id(2L)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .fecha(LocalDateTime.now())
                .estado(Estado.PENDIENTE)
                .formaPago(FormaPago.TRANSFERENCIA)
                .build();

        pedido2.addDetallePedido(1, p3);
        pedido2.addDetallePedido(1, p5);


        // 6. PEDIDO 3

        Pedido pedido3 = Pedido.builder()
                .id(3L)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .fecha(LocalDateTime.now())
                .estado(Estado.TERMINADO)
                .formaPago(FormaPago.EFECTIVO)
                .build();

        pedido3.addDetallePedido(2, p8);
        pedido3.addDetallePedido(1, p10);


        // Asociamos pedidos a usuarios

        usuario1.agregarPedido(pedido1);
        usuario1.agregarPedido(pedido2);

        usuario2.agregarPedido(pedido3);


        // 7. MOSTRAR UN PRODUCTO

        System.out.println("===== PRODUCTO 1 =====");
        System.out.println(p1);


        // 8. LISTADO DE PRODUCTOS

        Set<Producto> productos = new LinkedHashSet<>();

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

        System.out.println("\n===== LISTADO DE PRODUCTOS =====");

        for (Producto producto : productos) {
            System.out.println(producto);
        }


        // 9. USUARIO CON MAS PEDIDOS

        Usuario usuarioConMasPedidos;

        if (usuario1.getPedidos().size() >= usuario2.getPedidos().size()) {
            usuarioConMasPedidos = usuario1;
        } else {
            usuarioConMasPedidos = usuario2;
        }

        System.out.println("\n===== USUARIO CON MÁS PEDIDOS =====");

        System.out.println(
                usuarioConMasPedidos.getNombre()
                        + " "
                        + usuarioConMasPedidos.getApellido()
        );

        for (Pedido pedido : usuarioConMasPedidos.getPedidos()) {
            System.out.println(pedido);
        }


        // 10. PRUEBA DE EQUALS

        Producto productoDuplicado = Producto.builder()
                .id(99L)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .nombre("Mouse Logitech")
                .precio(35000.0)
                .descripcion("Otro mouse para probar equals")
                .stock(50)
                .imagen("mouse2.jpg")
                .disponible(true)
                .categoria(tecnologia)
                .build();

        System.out.println("\n===== COMPARACIÓN CON EQUALS =====");

        for (Producto producto : productos) {

            System.out.println(
                    producto.getNombre()
                            + " == producto duplicado -> "
                            + producto.equals(productoDuplicado)
            );
        }


        // 11. DTO

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario1.getId(),
                usuario1.getNombre(),
                usuario1.getApellido(),
                usuario1.getMail(),
                usuario1.getCelular()
        );

        System.out.println("\n===== USUARIO DTO =====");
        System.out.println(usuarioDTO);
    }


    // Método auxiliar para crear productos

    private static Producto producto(
            Long id,
            String nombre,
            Double precio,
            Integer stock,
            Categoria categoria) {

        return Producto.builder()
                .id(id)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .nombre(nombre)
                .precio(precio)
                .descripcion("Descripción de " + nombre)
                .stock(stock)
                .imagen(
                        nombre.toLowerCase()
                                .replace(" ", "_")
                                + ".jpg"
                )
                .disponible(true)
                .categoria(categoria)
                .build();
    }
}