package ej1;

public class Main {

    public static void main(String[] args) {

        Adder adder = (a, b) -> a + b;

        System.out.println(adder.add(5, 3));
        System.out.println(adder.add(10, 7));

        /*
         * Una interfaz funcional es una interfaz que tiene un solo
         * método abstracto. Esto permite utilizar una lambda para
         * implementar ese método sin crear otra clase.
         *
         * La anotación @FunctionalInterface sirve para que el
         * compilador compruebe que la interfaz cumpla esa condición.
         *
         * En este caso, la lambda (a, b) -> a + b implementa
         * el método add de la interfaz Adder y realiza la suma
         * de los dos números recibidos.
         */
    }
}