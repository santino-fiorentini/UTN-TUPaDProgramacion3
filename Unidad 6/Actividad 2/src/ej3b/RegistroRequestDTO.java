package ej3b;

public class RegistroRequestDTO {

    private String nombre;
    private String email;
    private String password;

    public RegistroRequestDTO(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistroRequestDTO{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}