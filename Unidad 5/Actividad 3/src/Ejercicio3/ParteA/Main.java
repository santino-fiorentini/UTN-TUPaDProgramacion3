package Ejercicio3.ParteA;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ProductoRoto p1 =
                new ProductoRoto("P001", "Teclado", 10000);

        ProductoRoto p2 =
                new ProductoRoto("P001", "Mouse", 5000);

        System.out.println("p1.equals(p2): " + p1.equals(p2));

        System.out.println("hashCode de p1: " + p1.hashCode());
        System.out.println("hashCode de p2: " + p2.hashCode());

        System.out.println();

        Set<ProductoRoto> productos = new HashSet<>();

        productos.add(p1);

        System.out.println("¿p2 existe en el Set? " + productos.contains(p2));

        productos.add(p2);

        System.out.println("Cantidad de elementos en el Set: " + productos.size());

        System.out.println();

        Map<ProductoRoto, Integer> stock = new HashMap<>();

        stock.put(p1, 10);

        System.out.println("Stock buscando con p2: " + stock.get(p2));
    }
}