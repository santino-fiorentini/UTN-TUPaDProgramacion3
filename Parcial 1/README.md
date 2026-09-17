# Parcial 1 - Programación 3

Aplicación frontend de Food Store desarrollada con HTML5, CSS3, JavaScript y TypeScript.

## Video explicativo

[YouTube](https://youtu.be/JhZb9_jSur0)

## Funcionalidades

- Catálogo de productos cargado desde `src/data/data.ts`.
- Búsqueda de productos por nombre.
- Filtro por categoría.
- Carrito de compras persistente con `localStorage`.
- Agregado de productos sin duplicar el ítem.
- Actualización de cantidades.
- Eliminación de productos del carrito.
- Cálculo del total.
- Navegación entre catálogo y carrito.

## Ejecución

1. Instalar dependencias:
   `pnpm install`
2. Ejecutar:
   `pnpm dev`
3. Abrir la dirección indicada por Vite, normalmente `http://localhost:5173`.

## Estructura principal

```text
src/
├──assets/
├── data/
│   └── data.ts
├── pages/
│   └── client/
│       ├── home/
│       │   ├── home.html
│       │   ├── home.css
│       │   └── home.ts
│       └── cart/
│           ├── cart.html
│           ├── cart.css
│           └── cart.ts
├── types/
│   ├── product.ts
│   └── categoria.ts
└── utils/
    └── cart.ts
```
