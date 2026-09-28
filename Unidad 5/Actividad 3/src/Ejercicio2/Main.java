package Ejercicio2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Producto p1 = new Producto("P001", "Teclado", 10000);
        Producto p2 = new Producto("P001", "Mouse", 5000);

        Set<Producto> productos = new HashSet<>();

        productos.add(p1);

        System.out.println("¿p2 ya existe en el Set? " + productos.contains(p2));

        productos.add(p2);

        System.out.println("Cantidad de productos en el Set: " + productos.size());

        Map<Producto, Integer> stock = new HashMap<>();

        stock.put(p1, 10);

        System.out.println("Stock buscando con p2: " + stock.get(p2));
    }
}