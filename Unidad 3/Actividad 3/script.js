const URL_API = "https://thesimpsonsapi.com/api/characters";
const URL_IMAGENES = "https://cdn.thesimpsonsapi.com/500";
const contenedor = document.getElementById("personajes");

// Actividad 1: Fetch de la API
const obtenerPersonajes = async () => {
    try {
        const respuesta = await fetch(URL_API); // Pedir los datos
        const datos = await respuesta.json(); // Esperar y convertir la respuesta a JSON
        
        console.log(datos.results); // Verificar por consola el array de personajes
        
        mostrarPersonajes(datos.results); // Pasar el array a la función de renderizado
    } catch (error) {
        console.error("Error al obtener los datos:", error);
    }
};

// Actividad 2: Mostrar los personajes en el DOM
const mostrarPersonajes = (personajes) => {
    personajes.forEach(personaje => {
        // Crear el contenedor de la tarjeta
        const card = document.createElement("div");
        card.classList.add("personaje-card");

        // Armar el contenido HTML
        card.innerHTML = `
            <img src="${URL_IMAGENES}${personaje.portrait_path}" alt="${personaje.name}">
            <h2>${personaje.name}</h2>
            <p><strong>Ocupación:</strong> ${personaje.occupation}</p>
            <p><strong>Estado:</strong> ${personaje.status}</p>
            <p><strong>Edad:</strong> ${personaje.age}</p>
        `;

        // Agregar la tarjeta al contenedor principal
        contenedor.appendChild(card);
    });
};

obtenerPersonajes();