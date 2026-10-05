package ej3b;

public class Main {

    public static void main(String[] args) {

        UsuarioLombok usuario1 = UsuarioLombok.builder()
                .nombre("Juan Pérez")
                .apellido("García")
                .email("juan@mail.com")
                .telefono("1155443322")
                .build();

        UsuarioLombok usuario2 = UsuarioLombok.builder()
                .nombre("María")
                .email("maria@empresa.com")
                .build();

        System.out.println(usuario1);
        System.out.println(usuario2);
    }

    /*
     * 1. Usuario necesita muchas líneas para implementar manualmente
     *    el Builder, mientras que UsuarioLombok solamente necesita
     *    las anotaciones @Builder y @ToString.
     *
     * 2. @Builder genera automáticamente la clase Builder, sus
     *    atributos, los métodos fluidos y el método build().
     *
     * 3. Builder resulta conveniente cuando una clase tiene muchos
     *    atributos o cuando algunos atributos son opcionales.
     *    También resulta útil cuando queremos que la creación del
     *    objeto sea más clara y legible que un constructor con
     *    muchos parámetros.
     */
}
