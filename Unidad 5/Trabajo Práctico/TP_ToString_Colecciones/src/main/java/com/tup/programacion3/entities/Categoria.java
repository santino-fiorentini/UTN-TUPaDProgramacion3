package com.tup.programacion3.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Categoria extends Base {
    private String nombre;
    private String descripcion;
    private Set<Producto> productos;

    public Categoria(String nombre, String descripcion) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        if (producto != null) {
            productos.add(producto);
        }
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
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

        Categoria categoria = (Categoria) o;
        return Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
