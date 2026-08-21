// Ejercicio 1

let nombre = "Santino";
let edad = 19;
let estudiante = true;

let materias = ["Programación", "Bases de Datos", "Inglés"];

let persona = {
    nombre: "Santino",
    edad: 19
};

console.log("Ejercicio 1:");
console.log(nombre);
console.log(edad);
console.log(estudiante);
console.log(materias);
console.log(persona);


// Ejercicio 2

console.log("Ejercicio 2:");
console.log(typeof nombre);
console.log(typeof edad);
console.log(typeof estudiante);
console.log(typeof materias);
console.log(typeof persona);


// Ejercicio 3

const pais = "Argentina";

console.log("Ejercicio 3:");
console.log(pais);

// Una variable declarada con const no puede ser reasignada.
// La siguiente línea produciría un error si se ejecutara:
// pais = "Brasil";


// Ejercicio 4

let a = 10;
let b = 3;

console.log("Ejercicio 4:");

console.log("Suma:", a + b);
console.log("Resta:", a - b);
console.log("Multiplicación:", a * b);
console.log("División:", a / b);
console.log("Módulo:", a % b);


// Ejercicio 5

console.log("Ejercicio 5:");

console.log('"5" == 5:', "5" == 5);
console.log('"5" === 5:', "5" === 5);

console.log('0 == false:', 0 == false);
console.log('0 === false:', 0 === false);


// Ejercicio 6

let numero = 7;

console.log("Ejercicio 6:");

if (numero % 2 === 0) {
    console.log("El número", numero, "es par");
} else {
    console.log("El número", numero, "es impar");
}


// Ejercicio 7

console.log("Ejercicio 7:");

for (let i = 0; i < materias.length; i++) {
    console.log("Índice:", i, "- Elemento:", materias[i]);
}


// Ejercicio 8

console.log("Ejercicio 8:");

let contador = 1;

while (contador <= 5) {
    console.log(contador);
    contador++;
}