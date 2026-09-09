import './style.css';
import { obtenerPersonajes } from './api/personajes';
import type { Personaje } from './types/personaje';

const URL_IMAGENES = 'https://cdn.thesimpsonsapi.com/500';

const contenedor = document.querySelector<HTMLDivElement>('#personajes')!;

const crearTarjeta = (personaje: Personaje): HTMLDivElement => {
    const tarjeta = document.createElement('div');
    tarjeta.classList.add('personaje-card');

    const imagen = document.createElement('img');
    imagen.src = `${URL_IMAGENES}${personaje.portrait_path}`;
    imagen.alt = personaje.name;

    const nombre = document.createElement('h2');
    nombre.textContent = personaje.name;

    const ocupacion = document.createElement('p');
    ocupacion.textContent = `Ocupación: ${personaje.occupation}`;

    const estado = document.createElement('p');
    estado.textContent = `Estado: ${personaje.status}`;

    const edad = document.createElement('p');
    edad.textContent = `Edad: ${personaje.age}`;

    tarjeta.appendChild(imagen);
    tarjeta.appendChild(nombre);
    tarjeta.appendChild(ocupacion);
    tarjeta.appendChild(estado);
    tarjeta.appendChild(edad);

    return tarjeta;
};

const mostrarPersonajes = (personajes: Personaje[]): void => {
    personajes.forEach((personaje) => {
        const tarjeta = crearTarjeta(personaje);
        contenedor.appendChild(tarjeta);
    });
};

const iniciar = async (): Promise<void> => {
    const personajes = await obtenerPersonajes();
    mostrarPersonajes(personajes);
};

iniciar();