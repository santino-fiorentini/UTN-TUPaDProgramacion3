package com.utn.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Producto extends Base {

    @EqualsAndHashCode.Include
    private String nombre;

    @EqualsAndHashCode.Include
    private Double precio;

    private String descripcion;
    private Integer stock;
    private String imagen;
    private Boolean disponible;

    private Categoria categoria;
}