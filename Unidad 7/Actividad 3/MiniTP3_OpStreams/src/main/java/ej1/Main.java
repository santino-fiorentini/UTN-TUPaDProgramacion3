package ej1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(15, 22, 33, 40, 51, 68, 77, 84);

        // Armamos el stream, filtramos los pares y los imprimimos.
        numeros.stream()
                .filter(n -> n % 2 == 0) // Operación intermedia: se queda con los pares
                .forEach(System.out::println); // Operación terminal: imprime cada número

        /*
         * La operación `filter` es una operación intermedia. Por sí sola, solo devuelve otro
         * Stream y no ejecuta ninguna lógica debido a la evaluación perezosa (lazy evaluation) de Java.
         * Es necesario invocar una operación terminal como `forEach` para que el flujo completo se
         * dispare, procese los datos y realmente ejecute el filtro para producir el resultado visible.
         */
    }
}