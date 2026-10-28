# TP Sistema de Gestión de Pedidos - Programación III

**Alumno:** Santino Fiorentini  
**Tema:** Programación funcional con Streams en Java.

## Requisitos
- JDK 26.
- Gradle (o abrir el proyecto desde IntelliJ IDEA y dejar que importe Gradle).

## Cómo ejecutar
Ejecutar la clase `ar.edu.tup.programacion3.Main`.

## Qué prueba el programa
1. Calcula el total de un pedido mediante Streams.
2. Muestra los productos disponibles.
3. Suma las cantidades de los detalles para obtener el total de ítems.
4. Muestra los productos cuyo stock es menor que 5.

## Streams utilizados
- `mapToDouble()` y `sum()` para calcular importes.
- `mapToInt()` y `sum()` para sumar cantidades.
- `filter()` para seleccionar productos.
- `forEach()` para mostrar resultados.

No se utilizan bucles tradicionales `for` ni `while` en el código Java.
