package ej3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        Predicate<Integer> esPositivo = numero -> numero > 0;

        System.out.println(esPositivo.test(5));
        System.out.println(esPositivo.test(-3));

        Supplier<Double> numeroAleatorio = () -> Math.random();

        System.out.println(numeroAleatorio.get());
        System.out.println(numeroAleatorio.get());

        List<String> nombres =
                Arrays.asList("Ana", "Pedro", "Juan", "Alberto");

        nombres.stream()
                .filter(nombre -> nombre.length() > 3)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        /*
         * La lambda de esPositivo implementa Predicate<Integer>,
         * porque evalúa si un número es mayor que cero y devuelve
         * true o false. Se utiliza el método test().
         *
         * La lambda de numeroAleatorio implementa Supplier<Double>,
         * porque genera un número sin recibir parámetros. Se utiliza
         * el método get().
         *
         * En el stream, la lambda de filter implementa Predicate,
         * porque decide qué nombres conservar.
         *
         * La referencia String::toUpperCase de map implementa
         * Function, porque transforma cada nombre a mayúsculas.
         *
         * La referencia System.out::println de forEach implementa
         * Consumer, porque recibe cada nombre y lo muestra por
         * pantalla sin devolver un resultado.
         *
         * Cada lambda o referencia a método se adapta al método
         * abstracto de la interfaz funcional que necesita la operación.
         */
    }
}