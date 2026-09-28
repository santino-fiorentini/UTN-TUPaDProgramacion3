package Ejercicio3.ParteB;

public class Factura {

    private int numero;
    private double total;
    private Cliente cliente;

    public Factura(int numero, double total) {
        this.numero = numero;
        this.total = total;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura[numero=" + numero + ", total=" + total + "]";
    }
}