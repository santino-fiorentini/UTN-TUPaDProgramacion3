package Ejercicio2;

public class Auto extends Vehiculo {

    private int cantPuertas;

    public Auto(String marca, int anio, int cantPuertas) {
        super(marca, anio);
        this.cantPuertas = cantPuertas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Auto[cantPuertas=" + cantPuertas + "]";
    }
}