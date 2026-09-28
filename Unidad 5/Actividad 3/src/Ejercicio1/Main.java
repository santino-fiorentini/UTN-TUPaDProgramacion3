package Ejercicio1;

public class Main {

    public static void main(String[] args) {

        Producto p1 = new Producto("P001", "Teclado", 10000);
        Producto p2 = new Producto("P001", "Mouse", 5000);
        Producto p3 = new Producto("P002", "Monitor", 50000);

        System.out.println("p1 == p2: " + (p1 == p2));
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("p1.equals(p3): " + p1.equals(p3));

        System.out.println();

        System.out.println("hashCode de p1: " + p1.hashCode());
        System.out.println("hashCode de p2: " + p2.hashCode());
        System.out.println("hashCode de p3: " + p3.hashCode());
    }
}