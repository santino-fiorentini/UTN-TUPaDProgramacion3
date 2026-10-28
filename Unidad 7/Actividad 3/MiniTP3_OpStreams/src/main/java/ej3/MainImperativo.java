package ej3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainImperativo {
    public static void main(String[] args) {
        List<String> frases = List.of(
                "El perro corre en el parque",
                "El gato duerme en el sillón",
                "El perro y el gato juegan juntos"
        );

        List<String> palabrasUnicas = new ArrayList<>();

        // Recorrer las frases y procesar cada palabra
        for (String frase : frases) {
            String[] palabras = frase.split(" ");
            for (String palabra : palabras) {
                String palabraMinuscula = palabra.toLowerCase();
                // Verificación manual para evitar duplicados
                if (!palabrasUnicas.contains(palabraMinuscula)) {
                    palabrasUnicas.add(palabraMinuscula);
                }
            }
        }

        // Ordenar alfabéticamente
        Collections.sort(palabrasUnicas);

        // Mostrar resultado
        System.out.println(palabrasUnicas);

        /*
         * Para resolver este problema con un enfoque imperativo, necesité crear una lista
         * auxiliar mutable (`palabrasUnicas`) para ir almacenando los resultados paso a paso.
         * Además, tuve que implementar una verificación manual con `contains()` dentro de un
         * bucle anidado para asegurarme de que no se agregaran palabras repetidas, ya que las
         * listas tradicionales no filtran duplicados automáticamente. Finalmente, usé otra
         * instrucción externa (`Collections.sort`) para ordenar los resultados.
         */
    }
}