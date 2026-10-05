package ej3a;

public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    private Usuario(Builder builder) {
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.email = builder.email;
        this.telefono = builder.telefono;
    }

    @Override
    public String toString() {
        return "Usuario(" +
                "nombre=" + nombre +
                ", apellido=" + apellido +
                ", email=" + email +
                ", telefono=" + telefono +
                ")";
    }

    public static class Builder {

        private String nombre;
        private String apellido;
        private String email;
        private String telefono;

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }
}
