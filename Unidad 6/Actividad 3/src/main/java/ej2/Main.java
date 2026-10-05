package ej2;

public class Main {

    public static void main(String[] args) {

        Persona persona1 = new Persona(
                "Ana", "García", "ana@mail.com", "Rivadavia", 1234
        );

        DomicilioDto domicilio1 = new DomicilioDto(
                persona1.getCalle(), persona1.getNumero()
        );

        PersonaDto personaDto1 = new PersonaDto(
                persona1.getNombre(), domicilio1
        );

        PersonaRecordDto personaRecordDto1 = new PersonaRecordDto(
                persona1.getNombre(), domicilio1
        );

        System.out.println("--- Persona 1 ---");
        System.out.println("[DTO clásico] " + personaDto1);
        System.out.println("[Record DTO]   " + personaRecordDto1);


        Persona persona2 = new Persona(
                "Carlos", "Ruiz", "carlos@mail.com", "Corrientes", 567
        );

        DomicilioDto domicilio2 = new DomicilioDto(
                persona2.getCalle(), persona2.getNumero()
        );

        PersonaDto personaDto2 = new PersonaDto(
                persona2.getNombre(), domicilio2
        );

        PersonaRecordDto personaRecordDto2 = new PersonaRecordDto(
                persona2.getNombre(), domicilio2
        );

        System.out.println();
        System.out.println("--- Persona 2 ---");
        System.out.println("[DTO clásico] " + personaDto2);
        System.out.println("[Record DTO]   " + personaRecordDto2);


        Persona persona3 = new Persona(
                "María", "Torres", "maria@mail.com", "Belgrano", 890
        );

        DomicilioDto domicilio3 = new DomicilioDto(
                persona3.getCalle(), persona3.getNumero()
        );

        PersonaDto personaDto3 = new PersonaDto(
                persona3.getNombre(), domicilio3
        );

        PersonaRecordDto personaRecordDto3 = new PersonaRecordDto(
                persona3.getNombre(), domicilio3
        );

        System.out.println();
        System.out.println("--- Persona 3 ---");
        System.out.println("[DTO clásico] " + personaDto3);
        System.out.println("[Record DTO]   " + personaRecordDto3);
    }

    /*
     * Diferencias entre PersonaDto y PersonaRecordDto:
     *
     * 1. toString():
     *    PersonaDto utiliza un toString() escrito manualmente y usa
     *    llaves {}. El record genera automáticamente un toString()
     *    que utiliza corchetes [].
     *
     * 2. Acceso al nombre:
     *    En PersonaDto se utiliza getNombre().
     *    En PersonaRecordDto se utiliza nombre().
     *
     * 3. Cantidad de código:
     *    PersonaDto necesita atributos, constructor, getters y
     *    toString(). PersonaRecordDto necesita solamente declarar
     *    sus componentes, porque el compilador genera automáticamente
     *    gran parte del código.
     */
}
