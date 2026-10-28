package ej3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainSeguro {

    public static void main(String[] args) {

        List<Integer> numeros = IntStream.rangeClosed(1, 50000)
                .boxed()
                .collect(Collectors.toList());

        long total = numeros.parallelStream()
                .mapToLong(n -> n)
                .sum();

        System.out.println(total);

        // Esta versión calcula la suma mediante una operación
        // de reducción preparada para combinar resultados parciales.
        // Cada hilo procesa sus elementos y el stream combina
        // los resultados sin modificar un acumulador compartido
        // desde un forEach. Por eso obtenemos siempre la suma correcta.
    }
}
