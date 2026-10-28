package ej3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainFuncional {
    public static void main(String[] args) {
        List<String> frases = List.of(
                "El perro corre en el parque",
                "El gato duerme en el sillón",
                "El perro y el gato juegan juntos"
        );

        List<String> palabrasUnicas = frases.stream()
                .flatMap(frase -> Arrays.stream(frase.split(" "))) // Aplana los arrays a un solo stream de palabras
                .map(String::toLowerCase)                          // Pasa cada palabra a minúscula
                .distinct()                                        // Elimina los duplicados automáticamente
                .sorted()                                          // Ordena alfabéticamente de forma natural
                .collect(Collectors.toList());                     // Recolecta el resultado en una lista

        System.out.println(palabrasUnicas);

        /*
         * En lugar de decirle a Java "cómo" hacer las cosas paso a paso con bucles anidados,
         * utilizamos Streams para declarar "qué" queremos lograr. Usando `flatMap` aplanamos
         * las frases en palabras sueltas, y con `distinct` y `sorted` delegamos la responsabilidad
         * de filtrar duplicados y ordenar directamente al lenguaje. El código es más corto,
         * mucho más expresivo y fácil de leer porque cada línea representa una operación clara
         * de transformación sin necesidad de mantener estados externos o listas temporales.
         */
    }
}