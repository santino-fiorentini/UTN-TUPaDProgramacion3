package com.utn.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Categoria extends Base {

    private String nombre;
    private String descripcion;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Producto> productos = new LinkedHashSet<>();

    public void agregarProducto(Producto producto) {
        if (producto != null) {
            productos.add(producto);
        }
    }
}