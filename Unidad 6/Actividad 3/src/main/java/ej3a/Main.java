package ej3a;

public class Main {

    public static void main(String[] args) {

        Usuario usuario1 = new Usuario.Builder()
                .nombre("Juan Pérez")
                .apellido("García")
                .email("juan@mail.com")
                .telefono("1155443322")
                .build();

        Usuario usuario2 = new Usuario.Builder()
                .nombre("María")
                .email("maria@empresa.com")
                .build();

        System.out.println(usuario1);
        System.out.println(usuario2);
    }

    /*
     * 1. La clase interna Builder ocupa aproximadamente 40 líneas
     *    contando atributos, métodos y build().
     *
     * 2. Si agregáramos un atributo llamado direccion, habría que
     *    modificar Usuario, el Builder y el constructor de Usuario
     *    que recibe el Builder.
     *
     * 3. Los cambios se realizarían en tres lugares principales:
     *    en los atributos de Usuario, en los atributos y métodos
     *    del Builder y en el constructor que copia los datos.
     */
}
