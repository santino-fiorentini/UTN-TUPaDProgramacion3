package ej2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Calculadora {

    private Subtractor restador;
    private Multiplier multiplicador;
    private Divider divisor;

    public Calculadora(Subtractor restador,
                       Multiplier multiplicador,
                       Divider divisor) {

        this.restador = restador;
        this.multiplicador = multiplicador;
        this.divisor = divisor;
    }

    public void mostrarResumen(int a, int b) {

        int resultadoResta = restador.subtract(a, b);
        int resultadoMultiplicacion = multiplicador.multiply(a, b);
        double resultadoDivision = divisor.divide(a, b);

        Function<Double, String> formatear =
                valor -> "Resultado: " + valor;

        List<String> resultados = new ArrayList<>();

        resultados.add(formatear.apply((double) resultadoResta));
        resultados.add(formatear.apply((double) resultadoMultiplicacion));
        resultados.add(formatear.apply(resultadoDivision));

        Consumer<String> imprimir =
                resultado -> System.out.println(resultado);

        resultados.stream().forEach(imprimir);
    }
}