package ej3b;

import jakarta.persistence.*;

@Entity(name = "ArticuloB")
@Table(name = "ARTICULOS")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigoInterno;

    @Column(name = "DESCRIPCION", length = 200)
    private String descripcionLarga;

    @Column(name = "PRECIO", nullable = false)
    private double precioVenta;

    public Articulo() {
    }

    public Articulo(String codigoInterno, String descripcionLarga,
                    double precioVenta) {
        this.codigoInterno = codigoInterno;
        this.descripcionLarga = descripcionLarga;
        this.precioVenta = precioVenta;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
}