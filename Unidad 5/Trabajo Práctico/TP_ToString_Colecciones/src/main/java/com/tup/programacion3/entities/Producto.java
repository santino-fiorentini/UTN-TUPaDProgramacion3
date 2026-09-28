package com.tup.programacion3.entities;

import java.util.Objects;

public class Producto extends Base {
    private String nombre;
    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private Boolean disponible;
    private Categoria categoria;

    public Producto(String nombre, Double precio, String descripcion,
                    int stock, String imagen, Boolean disponible,
                    Categoria categoria) {
        super();
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public String getImagen() {
        return imagen;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        String nombreCategoria = categoria != null ? categoria.getNombre() : "Sin categoria";

        return "Producto{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", imagen='" + imagen + '\'' +
                ", disponible=" + disponible +
                ", categoria='" + nombreCategoria + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Producto producto = (Producto) o;
        return Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
