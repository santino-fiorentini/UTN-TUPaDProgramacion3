package com.tp.jpa;

import com.tp.jpa.model.Categoria;
import com.tp.jpa.model.Producto;
import com.tp.jpa.repository.CategoriaRepository;
import com.tp.jpa.repository.ProductoRepository;
import com.tp.jpa.util.JPAUtil;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final CategoriaRepository categoriaRepo =
            new CategoriaRepository();

    private static final ProductoRepository productoRepo =
            new ProductoRepository();

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE GESTION =====");
            System.out.println("1. Gestionar categorias");
            System.out.println("2. Gestionar productos");
            System.out.println("3. Reportes");
            System.out.println("0. Salir");

            opcion = leerEntero("Seleccione una opcion: ");

            try {

                switch (opcion) {

                    case 1:
                        menuCategorias();
                        break;

                    case 2:
                        menuProductos();
                        break;

                    case 3:
                        menuReportes();
                        break;

                    case 0:
                        System.out.println("Cerrando aplicacion...");
                        JPAUtil.close();
                        break;

                    default:
                        System.out.println("Opcion incorrecta.");
                }

            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }


    // MENU DE CATEGORIAS

    private static void menuCategorias() {

        int opcion;

        do {
            System.out.println("\n===== GESTION DE CATEGORIAS =====");
            System.out.println("1. Alta");
            System.out.println("2. Modificar");
            System.out.println("3. Baja logica");
            System.out.println("4. Listar");
            System.out.println("0. Volver");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    altaCategoria();
                    break;

                case 2:
                    modificarCategoria();
                    break;

                case 3:
                    bajaCategoria();
                    break;

                case 4:
                    listarCategorias();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 0);
    }

    // Alta de categoria.
    private static void altaCategoria() {

        System.out.println("\n--- ALTA DE CATEGORIA ---");

        String nombre;

        do {
            nombre = leerTexto("Nombre: ");

            if (nombre.isBlank()) {
                System.out.println("El nombre es obligatorio.");
            }

        } while (nombre.isBlank());

        String descripcion = leerTexto("Descripcion: ");

        Categoria categoria = Categoria.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .build();

        Categoria guardada = categoriaRepo.guardar(categoria);

        System.out.println("Categoria creada correctamente.");
        System.out.println("ID generado: " + guardada.getId());
    }

    // Modificacion de categoria.
    private static void modificarCategoria() {

        System.out.println("\n--- MODIFICAR CATEGORIA ---");

        listarCategorias();

        Long id = leerLong("Ingrese el ID: ");

        Optional<Categoria> resultado = categoriaRepo.buscarPorId(id);

        if (resultado.isEmpty() || resultado.get().isEliminado()) {
            System.out.println("La categoria no existe o esta dada de baja.");
            return;
        }

        Categoria categoria = resultado.get();

        System.out.println("Nombre actual: " + categoria.getNombre());
        System.out.println("Descripcion actual: " + categoria.getDescripcion());

        String nombre = leerTexto("Nuevo nombre (Enter para conservar): ");
        String descripcion = leerTexto("Nueva descripcion (Enter para conservar): ");

        if (!nombre.isBlank()) {
            categoria.setNombre(nombre);
        }

        if (!descripcion.isBlank()) {
            categoria.setDescripcion(descripcion);
        }

        categoriaRepo.guardar(categoria);

        System.out.println("Categoria modificada correctamente.");
    }

    // Baja logica de categoria.
    private static void bajaCategoria() {

        System.out.println("\n--- BAJA DE CATEGORIA ---");

        listarCategorias();

        Long id = leerLong("Ingrese el ID: ");

        Optional<Categoria> resultado = categoriaRepo.buscarPorId(id);

        if (resultado.isEmpty() || resultado.get().isEliminado()) {
            System.out.println("La categoria no existe o ya esta dada de baja.");
            return;
        }

        Categoria categoria = resultado.get();

        List<Producto> productos =
                categoriaRepo.buscarProductosPorCategoria(id);

        if (!productos.isEmpty()) {
            System.out.println(
                    "No se puede eliminar la categoria. Tiene "
                            + productos.size()
                            + " productos activos."
            );
            System.out.println("Primero debe dar de baja sus productos.");
            return;
        }

        boolean eliminada = categoriaRepo.eliminarLogico(id);

        if (eliminada) {
            System.out.println(
                    "Categoria dada de baja: " + categoria.getNombre()
            );
        } else {
            System.out.println("No se pudo dar de baja la categoria.");
        }
    }

    // Listado de categorias activas.
    private static void listarCategorias() {

        System.out.println("\n--- CATEGORIAS ACTIVAS ---");

        List<Categoria> categorias = categoriaRepo.listarActivos();

        if (categorias.isEmpty()) {
            System.out.println("No hay categorias registradas.");
            return;
        }

        for (Categoria categoria : categorias) {
            System.out.println(
                    "ID: " + categoria.getId()
                            + " | Nombre: " + categoria.getNombre()
                            + " | Descripcion: " + categoria.getDescripcion()
            );
        }
    }


    // MENU DE PRODUCTOS

    private static void menuProductos() {

        int opcion;

        do {
            System.out.println("\n===== GESTION DE PRODUCTOS =====");
            System.out.println("1. Alta");
            System.out.println("2. Modificar");
            System.out.println("3. Baja logica");
            System.out.println("4. Listar");
            System.out.println("0. Volver");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    altaProducto();
                    break;

                case 2:
                    modificarProducto();
                    break;

                case 3:
                    bajaProducto();
                    break;

                case 4:
                    listarProductos();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 0);
    }

    // Alta de producto asociado a una categoria.
    private static void altaProducto() {

        System.out.println("\n--- ALTA DE PRODUCTO ---");

        List<Categoria> categorias = categoriaRepo.listarActivos();

        if (categorias.isEmpty()) {
            System.out.println("No hay categorias activas. Cree una primero.");
            return;
        }

        listarCategorias();

        Long idCategoria = leerLong("ID de la categoria: ");

        Optional<Categoria> categoria =
                categoriaRepo.buscarPorId(idCategoria);

        if (categoria.isEmpty() || categoria.get().isEliminado()) {
            System.out.println("La categoria seleccionada no es valida.");
            return;
        }

        String nombre;

        do {
            nombre = leerTexto("Nombre: ");

            if (nombre.isBlank()) {
                System.out.println("El nombre es obligatorio.");
            }

        } while (nombre.isBlank());

        String descripcion = leerTexto("Descripcion: ");

        double precio = leerDoublePositivo("Precio: ");

        int stock = leerEnteroNoNegativo("Stock: ");

        String imagen = leerTexto("Imagen (opcional): ");

        String respuesta;

        do {
            respuesta = leerTexto("Disponible (S/N, por defecto S): ");

            if (respuesta.isBlank()) {
                respuesta = "S";
            }

            respuesta = respuesta.toUpperCase();

            if (!respuesta.equals("S") && !respuesta.equals("N")) {
                System.out.println("Ingrese S o N.");
            }

        } while (!respuesta.equals("S") && !respuesta.equals("N"));

        boolean disponible = respuesta.equals("S");

        Producto producto = Producto.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .precio(precio)
                .stock(stock)
                .imagen(imagen)
                .disponible(disponible)
                .build();

        Producto guardado =
                categoriaRepo.agregarProducto(idCategoria, producto);

        System.out.println("Producto creado correctamente.");
        System.out.println("ID generado: " + guardado.getId());
        System.out.println(
                "Categoria asignada: " + categoria.get().getNombre()
        );
    }

    // Modificacion de producto.
    private static void modificarProducto() {

        System.out.println("\n--- MODIFICAR PRODUCTO ---");

        listarProductos();

        Long id = leerLong("Ingrese el ID: ");

        Optional<Producto> resultado = productoRepo.buscarPorId(id);

        if (resultado.isEmpty() || resultado.get().isEliminado()) {
            System.out.println("El producto no existe o esta dado de baja.");
            return;
        }

        Producto producto = resultado.get();

        System.out.println("Nombre actual: " + producto.getNombre());
        System.out.println("Precio actual: " + producto.getPrecio());
        System.out.println("Stock actual: " + producto.getStock());

        String nombre = leerTexto("Nuevo nombre (Enter para conservar): ");

        if (!nombre.isBlank()) {
            producto.setNombre(nombre);
        }

        String nuevoPrecio = leerTexto("Nuevo precio (Enter para conservar): ");

        if (!nuevoPrecio.isBlank()) {

            double precio;

            while (true) {
                try {
                    precio = Double.parseDouble(nuevoPrecio);

                    if (precio <= 0) {
                        System.out.println("El precio debe ser mayor a cero.");
                    } else {
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un precio valido.");
                }

                nuevoPrecio = leerTexto("Nuevo precio: ");
            }

            producto.setPrecio(precio);
        }

        String nuevoStock = leerTexto("Nuevo stock (Enter para conservar): ");

        if (!nuevoStock.isBlank()) {

            int stock;

            while (true) {
                try {
                    stock = Integer.parseInt(nuevoStock);

                    if (stock < 0) {
                        System.out.println("El stock no puede ser negativo.");
                    } else {
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un stock valido.");
                }

                nuevoStock = leerTexto("Nuevo stock: ");
            }

            producto.setStock(stock);
        }

        productoRepo.guardar(producto);

        System.out.println("Producto modificado correctamente.");
    }

    // Baja logica de producto.
    private static void bajaProducto() {

        System.out.println("\n--- BAJA DE PRODUCTO ---");

        listarProductos();

        Long id = leerLong("Ingrese el ID: ");

        Optional<Producto> resultado = productoRepo.buscarPorId(id);

        if (resultado.isEmpty() || resultado.get().isEliminado()) {
            System.out.println("El producto no existe o ya esta dado de baja.");
            return;
        }

        String nombre = resultado.get().getNombre();

        boolean eliminado = productoRepo.eliminarLogico(id);

        if (eliminado) {
            System.out.println("Producto dado de baja: " + nombre);
        } else {
            System.out.println("No se pudo dar de baja el producto.");
        }
    }

    // Listado de productos activos agrupados por categoria.
    private static void listarProductos() {

        System.out.println("\n--- PRODUCTOS ACTIVOS ---");

        List<Categoria> categorias = categoriaRepo.listarActivos();

        if (categorias.isEmpty()) {
            System.out.println("No hay categorias activas.");
            return;
        }

        boolean hayProductos = false;

        for (Categoria categoria : categorias) {

            List<Producto> productos =
                    categoriaRepo.buscarProductosPorCategoria(
                            categoria.getId()
                    );

            System.out.println("\nCategoria: " + categoria.getNombre());

            if (productos.isEmpty()) {
                System.out.println("Sin productos activos.");
                continue;
            }

            hayProductos = true;

            for (Producto producto : productos) {
                System.out.println(
                        "ID: " + producto.getId()
                                + " | Nombre: " + producto.getNombre()
                                + " | Precio: " + producto.getPrecio()
                                + " | Stock: " + producto.getStock()
                                + " | Disponible: " + producto.getDisponible()
                                + " | Categoria: " + categoria.getNombre()
                );
            }
        }

        if (!hayProductos) {
            System.out.println("No hay productos activos.");
        }
    }


    // MENU DE REPORTES

    private static void menuReportes() {

        int opcion;

        do {
            System.out.println("\n===== REPORTES =====");
            System.out.println("1. Productos por categoria");
            System.out.println("0. Volver");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    reporteProductosPorCategoria();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 0);
    }

    // Consulta JPQL de productos activos por categoria.
    private static void reporteProductosPorCategoria() {

        System.out.println("\n--- PRODUCTOS POR CATEGORIA ---");

        List<Categoria> categorias = categoriaRepo.listarActivos();

        if (categorias.isEmpty()) {
            System.out.println("No hay categorias activas.");
            return;
        }

        listarCategorias();

        Long id = leerLong("Seleccione el ID de la categoria: ");

        Optional<Categoria> resultado = categoriaRepo.buscarPorId(id);

        if (resultado.isEmpty() || resultado.get().isEliminado()) {
            System.out.println("La categoria no existe o esta dada de baja.");
            return;
        }

        List<Producto> productos =
                categoriaRepo.buscarProductosPorCategoria(id);

        if (productos.isEmpty()) {
            System.out.println("Esta categoria no tiene productos activos.");
            return;
        }

        System.out.println("\nProductos de " + resultado.get().getNombre());

        for (Producto producto : productos) {
            System.out.println(
                    "ID: " + producto.getId()
                            + " | Nombre: " + producto.getNombre()
                            + " | Precio: " + producto.getPrecio()
                            + " | Stock: " + producto.getStock()
            );
        }
    }


    // METODOS AUXILIARES

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {

        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static Long leerLong(String mensaje) {

        while (true) {
            try {
                return Long.parseLong(leerTexto(mensaje));

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un ID valido.");
            }
        }
    }

    private static double leerDoublePositivo(String mensaje) {

        while (true) {
            try {
                double valor = Double.parseDouble(leerTexto(mensaje));

                if (valor > 0) {
                    return valor;
                }

                System.out.println("El precio debe ser mayor a cero.");

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un precio valido.");
            }
        }
    }

    private static int leerEnteroNoNegativo(String mensaje) {

        while (true) {
            try {
                int valor = Integer.parseInt(leerTexto(mensaje));

                if (valor >= 0) {
                    return valor;
                }

                System.out.println("El stock no puede ser negativo.");

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }
}