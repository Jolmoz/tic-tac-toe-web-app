

/**
 * Función para dibujar el tablero en la página
 * @param {*} matrices 
 * @param {*} actions 
 */
export async function dibujarTablero(matrices, actions) {
    // Obtener el contenedor donde se agregarán las matrices
    var container = document.getElementById("matrices-container");
    // Limpiar el contenedor antes de agregar nuevas matrices
    container.innerHTML = "";

    // Recorrer cada matriz recibida
    matrices.forEach((matrix, index) => {
        // Crear un contenedor para la matriz
        var matrixWrapper = document.createElement("div");
        matrixWrapper.classList.add("matrix-wrapper");

        // Crear un subtítulo para la matriz
        var subtitle = document.createElement("h2");
        subtitle.textContent = "Paso " + (index + 1) + ", " + translateAction(actions[index]);
        matrixWrapper.appendChild(subtitle)

        // Crear un contenedor para la matriz
        var matrixContainer = document.createElement("div");
        matrixContainer.classList.add("matrix-container");

        // Crear una fila para cada fila de la matriz
        for (var i = 0; i < 3; i++) {
            var row = document.createElement("div");
            row.classList.add("matrix-row");

            // Crear una celda para cada elemento de la fila
            for (var j = 0; j < 3; j++) {
                var cell = document.createElement("div");
                cell.classList.add("matrix-cell");
                // Colocar el valor en la celda
                cell.textContent = matrix[i * 3 + j];
                row.appendChild(cell);
            }
            matrixContainer.appendChild(row);
        }

        // Agregar la matriz al contenedor
        matrixWrapper.appendChild(matrixContainer);
        container.appendChild(matrixWrapper);

        // Agregar una flecha entre matrices, excepto después de la última matriz
        if (index < matrices.length - 1) {
            var arrow = document.createElement("div");
            arrow.classList.add("arrow");
            container.appendChild(arrow);
        }
    });
}