package ej2;

import java.util.Arrays;
import java.util.List;

public class GestorDeNombres {

    private List<String> nombres;

    public GestorDeNombres() {
        nombres = Arrays.asList(
                "Bruno", "Ana", "Carla", "Alberto", "Diana", "Andrea"
        );
    }

    public void mostrarNombresConA() {
        nombres.stream()
                .filter(nombre -> nombre.startsWith("A"))
                .sorted()
                .forEach(nombre -> System.out.println(nombre));
    }

    public void mostrarNombresEnMayusculas() {
        nombres.stream()
                .map(nombre -> nombre.toUpperCase())
                .forEach(nombre -> System.out.println(nombre));
    }

    public void contarNombresConA() {
        long cantidad = nombres.stream()
                .filter(nombre -> nombre.startsWith("A"))
                .count();

        System.out.println("Cantidad de nombres que empiezan con A: " + cantidad);
    }
}