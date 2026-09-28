package com.tup.programacion3.entities;

import com.tup.programacion3.enums.Rol;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Usuario extends Base {
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contraseña;
    private Rol rol;
    private Set<Pedido> pedidos;

    public Usuario(String nombre, String apellido, String mail,
                   String celular, String contraseña, Rol rol) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contraseña = contraseña;
        this.rol = rol;
        this.pedidos = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public String getCelular() {
        return celular;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void agregarPedido(Pedido pedido) {
        if (pedido != null) {
            pedidos.add(pedido);
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", celular='" + celular + '\'' +
                ", rol=" + rol +
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

        Usuario usuario = (Usuario) o;
        return Objects.equals(mail, usuario.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}
