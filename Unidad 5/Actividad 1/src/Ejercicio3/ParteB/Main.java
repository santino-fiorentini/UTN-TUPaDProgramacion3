package Ejercicio3.ParteB;

public class Main {

    public static void main(String[] args) {

        Cliente cliente = new Cliente("Santino Fiorentini", "12345678");

        Factura factura = new Factura(1, 15000.0);

        cliente.setFactura(factura);
        factura.setCliente(cliente);

        System.out.println(cliente);
        System.out.println(factura);
    }
}