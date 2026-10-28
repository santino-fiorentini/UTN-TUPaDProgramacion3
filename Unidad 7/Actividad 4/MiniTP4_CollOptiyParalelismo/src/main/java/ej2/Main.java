package ej2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Empleado> empleados = List.of(
                new Empleado("Valentina", 3),
                new Empleado("Nicolás", 7),
                new Empleado("Tomás", 3),
                new Empleado("Julieta", 10),
                new Empleado("Franco", 7),
                new Empleado("Agustina", 1),
                new Empleado("Matías", 10),
                new Empleado("Rocío", 5)
        );

        // Caso 1: agrupar empleados por antigüedad.
        Map<Integer, List<Empleado>> porAntiguedad =
                empleados.stream()
                        .collect(Collectors.groupingBy(
                                Empleado::getAntiguedad
                        ));

        System.out.println(porAntiguedad);

        // Caso 2: separar empleados con menos de 5 años
        // de los empleados con 5 años o más.
        Map<Boolean, List<Empleado>> seniorYJunior =
                empleados.stream()
                        .collect(Collectors.partitioningBy(
                                e -> e.getAntiguedad() >= 5
                        ));

        System.out.println(seniorYJunior);

        // Caso 3: unir los nombres en un solo String.
        String nombres = empleados.stream()
                .map(Empleado::getNombre)
                .collect(Collectors.joining(", "));

        System.out.println(nombres);
    }
}
