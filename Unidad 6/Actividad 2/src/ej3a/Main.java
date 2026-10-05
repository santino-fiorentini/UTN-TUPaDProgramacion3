package ej3a;

public class Main {

    public static void main(String[] args) {

        UsuarioDTO solicitud = new UsuarioDTO(
                null,
                "Carlos Ruiz",
                "cruiz@mail.com",
                "Secr3t!",
                null
        );

        UsuarioDTO respuesta = new UsuarioDTO(
                1L,
                "Carlos Ruiz",
                "cruiz@mail.com",
                "Secr3t!",
                "2024-05-15"
        );

        System.out.println("[Solicitud de registro recibida]");
        System.out.println(solicitud);

        System.out.println();
        System.out.println("[Respuesta enviada al cliente]");
        System.out.println(respuesta);
    }

    /*
     * El campo sensible que aparece en la respuesta y no debería
     * enviarse al cliente es password.
     *
     * En una solicitud de registro no tienen sentido id y
     * fechaCreacion porque son datos generados por el servidor.
     *
     * Utilizar una misma clase para solicitud y respuesta genera
     * problemas porque obliga a incluir campos que no corresponden
     * a cada operación y puede provocar que datos sensibles,
     * como password, sean enviados al cliente.
     */
}