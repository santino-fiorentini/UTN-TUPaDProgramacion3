package ej2;

public class Empleado {

    private String nombre;
    private int antiguedad;

    public Empleado(String nombre, int antiguedad) {
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
