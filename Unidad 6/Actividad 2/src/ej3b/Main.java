package ej3b;

public class Main {

    public static void main(String[] args) {

        RegistroRequestDTO solicitud = new RegistroRequestDTO(
                "Carlos Ruiz",
                "cruiz@mail.com",
                "Secr3t!"
        );

        UsuarioResponseDTO respuesta = new UsuarioResponseDTO(
                1L,
                "Carlos Ruiz",
                "cruiz@mail.com",
                "2024-05-15"
        );

        System.out.println("[Solicitud de registro recibida]");
        System.out.println(solicitud);

        System.out.println();
        System.out.println("[Respuesta enviada al cliente]");
        System.out.println(respuesta);
    }

    /*
     * Al separar los DTOs se evita enviar información sensible en
     * la respuesta. En este caso, UsuarioResponseDTO no contiene
     * password.
     *
     * RegistroRequestDTO no incluye id ni fechaCreacion porque esos
     * datos son generados por el servidor y no deben ser enviados
     * por el cliente durante el registro.
     *
     * UsuarioResponseDTO no incluye password porque es un dato
     * sensible que no debe exponerse al cliente.
     *
     * Si el servidor incorpora el atributo apellido en la información
     * del perfil que devuelve al cliente, se debería modificar
     * UsuarioResponseDTO porque representa los datos de salida que
     * el servidor devuelve al cliente.
     */
}