package ej3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void mostrarNombresConCImperativo(List<String> nombres) {

        List<String> nombresConC = new ArrayList<>();

        for (String nombre : nombres) {
            if (nombre.startsWith("C")) {
                nombresConC.add(nombre);
            }
        }

        Collections.sort(nombresConC);

        for (String nombre : nombresConC) {
            System.out.println(nombre);
        }

        /*
         * En esta solución necesité tres pasos separados:
         * primero filtrar los nombres con un for y una condición,
         * después ordenarlos con Collections.sort y finalmente
         * mostrarlos con otro for.
         *
         * Es un enfoque imperativo porque tengo que indicar
         * cómo realizar cada paso y utilizar una lista auxiliar
         * para guardar los nombres seleccionados.
         */
    }

    public static void mostrarNombresConCFuncional(List<String> nombres) {

        nombres.stream()
                .filter(nombre -> nombre.startsWith("C"))
                .sorted()
                .forEach(nombre -> System.out.println(nombre));

        /*
         * En la versión funcional puedo resolver el mismo problema
         * con una cadena de operaciones en lugar de utilizar una
         * lista auxiliar y dos bucles for.
         *
         * La solución resulta más breve porque no necesito crear
         * otra lista ni ordenar sus elementos manualmente.
         * Me concentro en qué quiero obtener: filtrar los nombres,
         * ordenarlos y mostrarlos. Stream organiza esas operaciones.
         */
    }

    public static void main(String[] args) {

        List<String> nombres = Arrays.asList(
                "Martina", "Bruno", "Camila", "Carla", "Cristian"
        );

        mostrarNombresConCImperativo(nombres);

        mostrarNombresConCFuncional(nombres);
    }
}