package ej1;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Optional<String> opt1 = Optional.of("Nightfox");

        Optional<String> opt2 = Optional.ofNullable(null);

        Optional<String> opt3 = Optional.empty();

        opt1.ifPresent(valor ->
                System.out.println("Alias: " + valor)
        );

        if (opt2.isPresent()) {
            System.out.println(opt2.get());
        } else {
            System.out.println("No hay alias registrado");
        }

        String alias = opt2.orElse("SinApodo");
        System.out.println(alias);

        // Un Optional vacío representa la ausencia de un valor.
        // of se utiliza cuando el valor no es null, mientras que
        // ofNullable permite recibir null y lo transforma en un
        // Optional vacío. empty crea directamente un Optional vacío.
        // De esta manera podemos comprobar si un valor existe antes
        // de utilizarlo y reducir el riesgo de un NullPointerException.
    }
}
