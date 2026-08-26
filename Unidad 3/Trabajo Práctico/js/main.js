// Cargar las categorías en el HTML
const cargarCategorias = () => {

    const listaCategorias = document.getElementById("lista-categorias");

    categorias.forEach((categoria) => {
        const li = document.createElement("li");
        li.innerHTML = `<a href="#">${categoria}</a>`;
        listaCategorias.appendChild(li);
    });
};


// Cargar los productos en el HTML
const cargarProductos = () => {

    const contenedorProductos = document.getElementById("contenedor-productos");

    productos.forEach((producto) => {
        const article = document.createElement("article");
        article.innerHTML = `
            <img
                src="${producto.imagen}"
                alt="${producto.alt}"
                width="${producto.ancho}"
                height="${producto.alto}"
            >
            <h3>${producto.nombre}</h3>
            <p>${producto.descripcion}</p>
            <p>
                Precio:
                <strong>$${producto.precio.toFixed(2)}</strong>
            </p>
            <button
                type="button"
                onclick="agregarAlCarrito('${producto.nombre}')"
            >
                Agregar al Carrito
            </button>
        `;
        contenedorProductos.appendChild(article);
    });
};


// Mostrar un mensaje al agregar un producto
const agregarAlCarrito = (nombreProducto) => {alert(`Agregaste "${nombreProducto}" al carrito.`);};

// Ejecutar las funciones
cargarCategorias();
cargarProductos();