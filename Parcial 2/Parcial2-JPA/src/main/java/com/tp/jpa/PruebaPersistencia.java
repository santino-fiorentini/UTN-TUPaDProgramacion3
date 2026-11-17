package com.tp.jpa;

import com.tp.jpa.model.*;
import com.tp.jpa.model.enums.*;

import com.tp.jpa.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PruebaPersistencia {

    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Producto> productos = new ArrayList<>();
    private static final List<Categoria> categorias = new ArrayList<>();

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            cargarDatos(em);

            realizarOperaciones(em);

        } finally {

            em.close();
            JPAUtil.close();

        }
    }

    private static void cargarDatos(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Crear usuarios
        Usuario usuario1 = Usuario.builder()
                .nombre("Santino")
                .apellido("Fiorentini")
                .mail("santino@mail.com")
                .celular("3464000000")
                .contrasena("123456")
                .rol(Rol.ADMIN)
                .build();

        Usuario usuario2 = Usuario.builder()
                .nombre("Lionel")
                .apellido("Messi")
                .mail("messi@mail.com")
                .celular("3410000000")
                .contrasena("654321")
                .rol(Rol.USUARIO)
                .build();

        em.persist(usuario1);
        em.persist(usuario2);

        System.out.println("Usuarios persistidos correctamente.");

        // Guardar referencias para utilizarlas después
        usuarios.add(usuario1);
        usuarios.add(usuario2);


        // Crear categorias
        Categoria categoria1 = Categoria.builder()
                .nombre("Computacion")
                .descripcion("Computadoras y monitores")
                .build();

        Categoria categoria2 = Categoria.builder()
                .nombre("Perifericos")
                .descripcion("Accesorios informaticos")
                .build();

        Categoria categoria3 = Categoria.builder()
                .nombre("Celulares")
                .descripcion("Telefonos y dispositivos moviles")
                .build();

        em.persist(categoria1);
        em.persist(categoria2);
        em.persist(categoria3);

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);

        System.out.println("Categorias persistidas correctamente.");


        // Crear productos

        Producto p1 = Producto.builder()
                .nombre("Notebook Lenovo")
                .precio(850000.0)
                .descripcion("Notebook para uso general")
                .stock(10)
                .imagen("notebook.jpg")
                .disponible(true)
                .build();

        Producto p2 = Producto.builder()
                .nombre("PC Gamer")
                .precio(1500000.0)
                .descripcion("Computadora para videojuegos")
                .stock(5)
                .imagen("pc.jpg")
                .disponible(true)
                .build();

        Producto p3 = Producto.builder()
                .nombre("Monitor Samsung")
                .precio(350000.0)
                .descripcion("Monitor LED de 24 pulgadas")
                .stock(8)
                .imagen("monitor.jpg")
                .disponible(true)
                .build();

        Producto p4 = Producto.builder()
                .nombre("Teclado Logitech")
                .precio(45000.0)
                .descripcion("Teclado inalambrico")
                .stock(20)
                .imagen("teclado.jpg")
                .disponible(true)
                .build();

        Producto p5 = Producto.builder()
                .nombre("Mouse Logitech")
                .precio(25000.0)
                .descripcion("Mouse optico")
                .stock(30)
                .imagen("mouse.jpg")
                .disponible(true)
                .build();

        Producto p6 = Producto.builder()
                .nombre("Auriculares")
                .precio(60000.0)
                .descripcion("Auriculares con microfono")
                .stock(15)
                .imagen("auriculares.jpg")
                .disponible(true)
                .build();

        Producto p7 = Producto.builder()
                .nombre("Samsung Galaxy")
                .precio(700000.0)
                .descripcion("Telefono inteligente")
                .stock(12)
                .imagen("samsung.jpg")
                .disponible(true)
                .build();

        Producto p8 = Producto.builder()
                .nombre("iPhone")
                .precio(1800000.0)
                .descripcion("Telefono Apple")
                .stock(6)
                .imagen("iphone.jpg")
                .disponible(true)
                .build();

        Producto p9 = Producto.builder()
                .nombre("Tablet")
                .precio(400000.0)
                .descripcion("Tablet de uso general")
                .stock(9)
                .imagen("tablet.jpg")
                .disponible(true)
                .build();

        Producto p10 = Producto.builder()
                .nombre("Webcam")
                .precio(55000.0)
                .descripcion("Camara web HD")
                .stock(18)
                .imagen("webcam.jpg")
                .disponible(true)
                .build();

        // Agregar productos a la lista
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

        // Persistir productos
        for (Producto producto : productos) {
            em.persist(producto);
        }

        System.out.println("10 productos persistidos correctamente.");


        // Asociar productos a categorias

        Categoria computacion = em.find(
                Categoria.class, categoria1.getId());

        Categoria perifericos = em.find(
                Categoria.class, categoria2.getId());

        Categoria celulares = em.find(
                Categoria.class, categoria3.getId());

        // Computacion
        computacion.addProducto(p1);
        computacion.addProducto(p2);
        computacion.addProducto(p3);

        // Perifericos
        perifericos.addProducto(p4);
        perifericos.addProducto(p5);
        perifericos.addProducto(p6);
        perifericos.addProducto(p10);

        // Celulares
        celulares.addProducto(p7);
        celulares.addProducto(p8);
        celulares.addProducto(p9);

        System.out.println("Productos asociados a sus categorias.");


        // Crear pedidos

        Pedido pedido1 = Pedido.builder()
                .fecha(LocalDate.now())
                .estado(Estado.CONFIRMADO)
                .total(0.0)
                .formaPago(FormaPago.TARJETA)
                .build();

        Pedido pedido2 = Pedido.builder()
                .fecha(LocalDate.now())
                .estado(Estado.PENDIENTE)
                .total(0.0)
                .formaPago(FormaPago.TRANSFERENCIA)
                .build();

        Pedido pedido3 = Pedido.builder()
                .fecha(LocalDate.now())
                .estado(Estado.TERMINADO)
                .total(0.0)
                .formaPago(FormaPago.EFECTIVO)
                .build();


        // Asociar pedidos a usuarios

        usuarios.get(0).addPedido(pedido1);
        usuarios.get(0).addPedido(pedido2);

        usuarios.get(1).addPedido(pedido3);


        // Detalles del pedido 1
        pedido1.addDetallePedido(1, p1);
        pedido1.addDetallePedido(2, p5);

        // Detalles del pedido 2
        pedido2.addDetallePedido(1, p2);
        pedido2.addDetallePedido(1, p4);

        // Detalles del pedido 3
        pedido3.addDetallePedido(1, p7);
        pedido3.addDetallePedido(2, p9);

        // Calcular los totales
        pedido1.calcularTotal();
        pedido2.calcularTotal();
        pedido3.calcularTotal();

        // Persistir pedidos
        em.persist(pedido1);
        em.persist(pedido2);
        em.persist(pedido3);

        System.out.println("3 pedidos persistidos correctamente.");
        System.out.println("Cada pedido contiene dos detalles.");

        tx.commit();
    }

    private static void realizarOperaciones(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Actualizar producto 1
        Producto producto1 = em.find(
                Producto.class, productos.get(0).getId());

        producto1.setPrecio(900000.0);
        producto1.setStock(15);

        // Actualizar producto 2
        Producto producto2 = em.find(
                Producto.class, productos.get(1).getId());

        producto2.setPrecio(1600000.0);
        producto2.setStock(8);

        System.out.println("Dos productos actualizados.");

        // Buscar usuario por ID
        Usuario usuarioPorId = em.find(
                Usuario.class, usuarios.get(0).getId());

        if (usuarioPorId != null) {
            System.out.println("Usuario encontrado por ID:");
            System.out.println(usuarioPorId.getNombre());
            System.out.println(usuarioPorId.getMail());
        }

        // Buscar usuario por mail
        Usuario usuarioPorMail = em.createQuery(
                        "SELECT u FROM Usuario u WHERE u.mail = :mail",
                        Usuario.class)
                .setParameter("mail", "messi@mail.com")
                .getSingleResult();

        System.out.println("Usuario encontrado por mail:");
        System.out.println(usuarioPorMail.getNombre());
        System.out.println(usuarioPorMail.getMail());

        // Baja logica de un producto
        Producto productoEliminar = em.find(
                Producto.class, productos.get(9).getId());

        productoEliminar.setEliminado(true);

        System.out.println("Producto eliminado logicamente.");

        tx.commit();

        System.out.println("Operaciones finalizadas correctamente.");
    }
}