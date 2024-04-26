import { realizarPeticion } from '../../api-client/gameClient'
// URL del backend
const url = 'http://localhost:8080/api/solve';

/**
 * Esta funcion obtiene los valores de los input del HTML
 */
function jugar() {
    var values = []
    values.push(document.getElementById("input1").value)
    values.push(document.getElementById("input2").value)
    values.push(document.getElementById("input3").value)
    values.push(document.getElementById("input4").value)
    values.push(document.getElementById("input5").value)
    values.push(document.getElementById("input6").value)
    values.push(document.getElementById("input7").value)
    values.push(document.getElementById("input8").value)
    values.push(document.getElementById("input9").value)

    console.log("Valor del input:", values);
    realizarPeticion(values)
}



