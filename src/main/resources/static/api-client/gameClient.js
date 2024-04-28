import { GameDTO } from '/dtos/gameDTO.js';

// URL del backend
const urlNewGame = 'http://localhost:8080/api/ThreeLineService/newGame';
const urlMakeMove = 'http://localhost:8080/api/ThreeLineService/makeMove';
const urlsaveGame = 'http://localhost:8080/api/ThreeLineService/saveGame';
const urlgetAllGames = 'http://localhost:8080/api/ThreeLineService/getAllGames';
const urlgetGame = 'http://localhost:8080/api/ThreeLineService/getGame';

/**
 * @returns {GameDTO[]}
 */
export async function getAllGames() {

    // Configurar la solicitud POST
    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    };

    try {
        const response = await fetch(urlgetAllGames, requestOptions);
        if (!response.ok) {
            throw new Error('Error al enviar los datos');
        }

        /** @type {GameDTO[]} */
        const games = await response.json();
        return games

    } catch (error) {
        console.error('Hubo un error:', error);
        return;
    }

}

/**
 * @param {number} gameId  
 * @returns {GameDTO}
 */
export async function getGame(gameId) {

    // Configurar la solicitud POST
    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    };

    try {
        const response = await fetch(urlgetGame + "?gameId=" + gameId, requestOptions);
        if (!response.ok) {
            throw new Error('Error al enviar los datos');
        }

        /** @type {GameDTO[]} */
        const games = await response.json();
        return games

    } catch (error) {
        console.error('Hubo un error:', error);
        return;
    }

}

/**
 * @param {string} gameName 
 * @returns {GameDTO}
 */
export async function newGame(players, gameName) {

    // Configurar la solicitud POST
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(players)
    };

    try {
        const response = await fetch(urlNewGame + "?gameName=" + gameName, requestOptions);
        if (!response.ok) {
            throw new Error('Error al enviar los datos');
        }

        /** @type {GameDTO} */
        const game = await response.json();
        return game

    } catch (error) {
        console.error('Hubo un error:', error);
        return;
    }

}

/**
 * @param {number} index 
 * @returns {GameDTO}
 */
export async function makeMove(game, index) {

    // Configurar la solicitud POST
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(game)
    };

    try {
        const response = await fetch(urlMakeMove + "?index=" + index, requestOptions);
        if (!response.ok) {
            throw new Error('Error al enviar los datos');
        }

        /** @type {GameDTO} */
        const game = await response.json();
        return game

    } catch (error) {
        console.error('Hubo un error:', error);
        return;
    }

}

/**
 * @param {GameDTO} game 
 * @returns {GameDTO}
 */
export async function saveGame(game) {

    // Configurar la solicitud POST
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(game)
    };

    try {
        const response = await fetch(urlsaveGame, requestOptions);
        if (!response.ok) {
            throw new Error('Error al enviar los datos');
        }

        /** @type {GameDTO} */
        const game = await response.json();
        return game

    } catch (error) {
        console.error('Hubo un error:', error);
        return;
    }

}