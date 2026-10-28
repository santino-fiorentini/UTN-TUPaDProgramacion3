package ej1;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Function<Integer, Integer> duplicar = n -> n * 2;

        Function<Integer, Integer> triplicar = n -> n * 3;

        System.out.println(duplicar.apply(5));
        System.out.println(duplicar.apply(8));

        System.out.println(triplicar.apply(4));
        System.out.println(triplicar.apply(7));
    }
}

/*
Una expresión lambda permite definir una función sin tener que
crear una clase o un método con nombre para esa operación.

Tiene tres partes: los parámetros que recibe, la flecha ->
que separa los parámetros del cuerpo, y el cuerpo, que indica
qué operación se realiza.

Podemos tratar el comportamiento como un dato porque se puede
guardar en una variable y ejecutar cuando lo necesitamos.
En este ejemplo, duplicar y triplicar guardan dos operaciones
diferentes que ejecutamos mediante apply().
*/