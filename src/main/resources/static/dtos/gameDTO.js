import { PlayerDTO } from "/dtos/playerDTO.js";
export class GameDTO {
    constructor(id, gameName, board, playerX, playerO, playerWinner, boardState, lastSavedDate) {
        /** @type {number} */
        this.id = id;
        /** @type {string} */
        this.gameName = gameName;
        /** @type {string} */
        this.board = board;
        /** @type {PlayerDTO} */
        this.playerX = new PlayerDTO(playerX.id, playerX.playerName, playerX.playerType);
        /** @type {PlayerDTO} */
        this.playerO = new PlayerDTO(playerO.id, playerO.playerName, playerO.playerType);
        /** @type {PlayerDTO} */
        this.playerWinner = new PlayerDTO(playerWinner.id, playerWinner.playerName, playerWinner.playerType);
        /** @type {string} */
        this.boardState = boardState;
        /** @type {Date} */
        this.lastSavedDate = lastSavedDate;
    }
}