package ej3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainInseguro {

    public static void main(String[] args) {

        List<Integer> numeros = IntStream.rangeClosed(1, 50000)
                .boxed()
                .collect(Collectors.toList());

        int[] total = new int[1];

        numeros.parallelStream()
                .forEach(n -> total[0] += n);

        System.out.println(total[0]);

        // El resultado puede ser incorrecto porque varios hilos
        // modifican al mismo tiempo el mismo acumulador.
        // La operación de sumar no es atómica: dos hilos pueden
        // leer el mismo valor y sobrescribir sus actualizaciones.
        // Por eso pueden perderse sumas y el resultado puede variar
        // entre ejecuciones, aunque el código sea el mismo.
    }
}
