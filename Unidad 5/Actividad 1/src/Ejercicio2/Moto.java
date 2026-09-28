package Ejercicio2;

public class Moto extends Vehiculo {

    private int cilindrada;

    public Moto(String marca, int anio, int cilindrada) {
        super(marca, anio);
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        return super.toString() + ", Moto[cilindrada=" + cilindrada + "]";
    }
}