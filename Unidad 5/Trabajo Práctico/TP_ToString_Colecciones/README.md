# Trabajo Práctico: ToString - Equals - HashCode y Colecciones

Proyecto Java basado en el UML del Trabajo Práctico de la Unidad 5 de Programación III.

## Contenido
- Herencia desde `Base`.
- `Set` para pedidos, detalles y productos por categoría.
- Sobreescritura de `toString()`, `equals()` y `hashCode()`.
- 2 usuarios, 3 pedidos, 3 categorías y 10 productos.
- Prueba de un producto duplicado mediante `equals()` y `Set.contains()`.

## Identidades utilizadas
- `Base`: id.
- `Usuario`: mail.
- `Categoria`: nombre.
- `Producto`: nombre.
- `DetallePedido`: producto.
- `Pedido`: id heredado de Base.

## Estructura del proyecto
```text
TP_ToString_Colecciones/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── tup/
│                   └── programacion3/
│                       │
│                       ├── Main.java
│                       │
│                       ├── entities/
│                       │   ├── Base.java
│                       │   ├── Calculable.java
│                       │   ├── Categoria.java
│                       │   ├── DetallePedido.java
│                       │   ├── Pedido.java
│                       │   ├── Producto.java
│                       │   └── Usuario.java
│                       │
│                       └── enums/
│                           ├── Estado.java
│                           ├── FormaPago.java
│                           └── Rol.java
│
├── build.gradle
├── settings.gradle
└── README.md
```