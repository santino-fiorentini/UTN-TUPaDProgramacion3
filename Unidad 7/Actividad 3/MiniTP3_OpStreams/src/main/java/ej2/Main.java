package ej2;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> nombres = List.of("Bruno", "Camila", "Rodrigo", "Sol", "Ignacio", "Carla");

        // Caso 1: Cantidad total
        long cantidadTotal = nombres.stream().count();
        System.out.println("Total de nombres: " + cantidadTotal);

        // Caso 2: Cantidad que empiezan con "C"
        long empiezanConC = nombres.stream()
                .filter(n -> n.startsWith("C"))
                .count();
        System.out.println("Empiezan con C: " + empiezanConC);

        // Caso 3: Nombres en mayúsculas
        List<String> nombresMayusculas = nombres.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(nombresMayusculas);

        // Caso 4: Nombres concatenados
        String nombresConcatenados = nombres.stream()
                .collect(Collectors.joining(", "));
        System.out.println(nombresConcatenados);
    }
}