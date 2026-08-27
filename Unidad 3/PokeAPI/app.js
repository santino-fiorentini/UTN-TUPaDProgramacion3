const contenedorPokemones = document.getElementById("contenedor-pokemones")
const inputBuscar = document.getElementById("buscar")
const limit = 151
let todos = []

const fecthPokemons = async() => {

    try {
        const response = await fetch(`https://pokeapi.co/api/v2/pokemon?limit=${limit}`)
        const data = await response.json()

        todos = data.results.map((pokemon) => {
            const id = pokemon.url.split("/")[6];
            const url = `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png`
            return {
                nombre: pokemon.name,
                imagen: url,
            };
        });
        mostrarPokemones(todos)

    } catch (error) {
        console.log(error)
    }

}

const mostrarPokemones = (pokemones =[]) => {
    contenedorPokemones.innerHTML = ""
    if (pokemones.length > 0) {

        pokemones.forEach((pokemon) => {
        const tarjeta = document.createElement("article")
        tarjeta.classList.add("tarjeta")
        tarjeta.innerHTML = `<img src="${pokemon.imagen}" alt="${pokemon.nombre}"/> <h3>${pokemon.nombre}</h3>`
        contenedorPokemones.appendChild(tarjeta);
    });
    contenedorPokemones.classList.remove("contenedor--pokemones--flex")
    } else {
        const mensajeError = document.createElement("h2")
        mensajeError.textContent = "No se encontraron pokémones"
        contenedorPokemones.appendChild(mensajeError)
        contenedorPokemones.classList.add("contenedor--pokemones--flex")
    }
}

inputBuscar.addEventListener("input", (e) => {
    const busqueda = e.target.value.toLowerCase();
    const resultado = todos.filter((pokemon) => {
        return pokemon.nombre.toLowerCase().includes(busqueda);
    });
    mostrarPokemones(resultado)
});

fecthPokemons();