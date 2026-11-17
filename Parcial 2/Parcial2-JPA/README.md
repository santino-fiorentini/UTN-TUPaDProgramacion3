# Sistema de Gestión de Categorías y Productos

**Estudiante:** Santino Fiorentini

## Descripción del proyecto

Este proyecto consiste en un sistema de gestión desarrollado en Java que permite administrar categorías y productos mediante un menú interactivo por consola.

La aplicación utiliza JPA con Hibernate para realizar la persistencia de datos en una base de datos H2.

Sus principales funcionalidades son:

- Registrar, modificar, listar y dar de baja lógicamente categorías.
- Registrar, modificar, listar y dar de baja lógicamente productos.
- Asociar productos con sus respectivas categorías.
- Consultar los productos pertenecientes a una categoría.
- Generar reportes desde la consola.

La estructura del proyecto utiliza el patrón Repository para separar las operaciones de acceso a datos de la lógica de la aplicación.

## Tecnologías utilizadas

- Java
- Gradle
- JPA
- Hibernate 6.4.4
- Lombok
- H2 Database

## Instrucciones para ejecutar el proyecto

### Requisitos previos

- Tener instalado un JDK compatible con el proyecto.
- Tener instalado IntelliJ IDEA.
- Contar con conexión a Internet para que Gradle pueda descargar las dependencias durante la primera sincronización.

### Ejecución

1. Descargar o clonar el proyecto.
2. Abrir la carpeta del proyecto desde IntelliJ IDEA.
3. Esperar a que IntelliJ IDEA termine de sincronizar y configurar las dependencias de Gradle.
4. Abrir la clase `Main.java`, ubicada en `com.tp.jpa`.
5. Ejecutar el método `main()`.

La aplicación iniciará mostrando un menú principal desde el cual se podrán gestionar las categorías, los productos y consultar los reportes.

### Base de datos

La aplicación utiliza H2 como base de datos local. La conexión está configurada en `persistence.xml` mediante la siguiente URL:

`jdbc:h2:file:./data/jpa_db`

Hibernate administra la persistencia y actualiza el esquema de la base de datos automáticamente mediante la configuración `hibernate.hbm2ddl.auto = update`.

Los datos quedan almacenados localmente y se conservan entre ejecuciones.