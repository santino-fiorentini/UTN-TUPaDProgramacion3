// Cargar las tareas guardadas en LocalStorage. Si no hay tareas guardadas, se utiliza un array vacío.
let todos = JSON.parse(localStorage.getItem("todos")) || [];


// Función para guardar el array de tareas en LocalStorage.
const guardarTodos = () => {
    localStorage.setItem("todos", JSON.stringify(todos));
};


// Obtener los elementos del HTML para poder trabajar con ellos desde JavaScript.
const form = document.getElementById("todo-form");
const input = document.getElementById("todo-input");
const lista = document.getElementById("todo-list");


// Función que muestra todas las tareas en la página.
const renderTodos = () => {

    // Limpiar la lista antes de volver a crearla.
    lista.innerHTML = "";

    // Recorrer todas las tareas del array.
    todos.forEach((todo) => {

        // Crear un elemento <li> para cada tarea.
        const li = document.createElement("li");

        // Crear un <span> para mostrar el texto de la tarea.
        const texto = document.createElement("span");
        texto.textContent = todo.texto;


        // Si la tarea está completada, tachar el texto.
        if (todo.completada) {
            texto.style.textDecoration = "line-through";
        }


        // Al hacer click sobre el texto, completar o descompletar la tarea.
        texto.addEventListener("click", () => {
            toggleTodo(todo.id);
        });


        // Crear el botón para eliminar la tarea.
        const botonEliminar = document.createElement("button");
        botonEliminar.textContent = "Eliminar";


        // Al hacer click en el botón, eliminar la tarea.
        botonEliminar.addEventListener("click", () => {
            eliminarTodo(todo.id);
        });


        // Agregar el texto y el botón dentro del <li>.
        li.appendChild(texto);
        li.appendChild(botonEliminar);

        // Agregar el <li> dentro de la lista <ul>.
        lista.appendChild(li);
    });
};


// Función para agregar una nueva tarea.
const agregarTodo = (texto) => {

    // Crear un objeto con los datos de la nueva tarea.
    const nuevaTarea = {
        id: Date.now(),       // Generar un ID único.
        texto: texto,         // Guardar el texto ingresado.
        completada: false     // La tarea comienza sin completar.
    };


    // Agregar la nueva tarea al array.
    todos.push(nuevaTarea);

    // Guardar los cambios en LocalStorage.
    guardarTodos();

    // Actualizar la lista que se muestra en pantalla.
    renderTodos();
};


// Función para eliminar una tarea.
const eliminarTodo = (id) => {

    // Crear un nuevo array sin la tarea que tenga ese ID.
    todos = todos.filter((todo) => todo.id !== id);

    // Guardar los cambios en LocalStorage.
    guardarTodos();

    // Actualizar la lista en pantalla.
    renderTodos();
};


// Función para completar o descompletar una tarea.
const toggleTodo = (id) => {

    // Buscar la tarea correspondiente al ID recibido.
    const todo = todos.find((todo) => todo.id === id);


    // Verificar que la tarea exista.
    if (todo) {

        // Invertir el estado de completada
        todo.completada = !todo.completada;
    }


    // Guardar el nuevo estado en LocalStorage.
    guardarTodos();

    // Actualizar la lista en pantalla.
    renderTodos();
};


// Detectar cuando se envía el formulario.
form.addEventListener("submit", (evento) => {

    // Evitar que el formulario recargue la página.
    evento.preventDefault();


    // Obtener el texto escrito y eliminar espacios innecesarios.
    const texto = input.value.trim();


    // Comprobar que el usuario haya escrito algo.
    if (texto !== "") {

        // Agregar la nueva tarea.
        agregarTodo(texto);

        // Limpiar el campo de texto.
        input.value = "";
    }
});


// Mostrar las tareas cuando se carga la página.
renderTodos();