package ej2;

public class Main {

    public static void main(String[] args) {

        Calculadora calculadora = new Calculadora(
                (a, b) -> a - b,
                (a, b) -> a * b,
                (a, b) -> (double) a / b
        );

        calculadora.mostrarResumen(10, 2);

        System.out.println();

        calculadora.mostrarResumen(9, 3);

        System.out.println();

        calculadora.mostrarResumen(7, 7);
    }
}