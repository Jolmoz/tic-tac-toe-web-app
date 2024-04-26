import { dibujarTablero } from '../view/game/game'
/**
 * Esta función por medio de un consumo al web service del backend envia la solicitud para resolver el puzzle
 * @param {*} values 
 * @returns 
 */
export async function realizarPeticion(values) {

    // Mostrar el overlay oscuro y el spinner
    document.getElementById("overlay").style.display = "block";
    document.querySelector(".spinner-container").style.display = "block";

    // Configurar la solicitud POST
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(values)
    };

    try {
        const response = await fetch(url, requestOptions);
        if (!response.ok) {
            throw new Error('Error al enviar los datos');
        }
        const data = await response.json();
        console.log('Datos enviados correctamente:', data);
        if (data.states == undefined) {
            document.getElementById("overlay").style.display = "none";
            document.querySelector(".spinner-container").style.display = "none";
            alert(data.msg)
            return
        }

        if (data.states.length == 0) {
            document.getElementById("overlay").style.display = "none";
            document.querySelector(".spinner-container").style.display = "none";
            alert('No se encontró solución')
            return
        }

        if (data.states.length > 0) {
            await dibujarTablero(data.states, data.actions);
        }

        document.getElementById("overlay").style.display = "none";
        document.querySelector(".spinner-container").style.display = "none";

    } catch (error) {
        console.error('Hubo un error:', error);
        return;
    }

}