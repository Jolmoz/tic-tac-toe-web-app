import { PlayerDTO } from "./playerDTO";
export class GameDTO {
    constructor(id, gameName, board, playerX, playerO, playerWinner, boardState, lastSavedDate) {
        this.id = id;
        this.gameName = gameName;
        this.board = board;
        this.playerX = new PlayerDTO(playerX.id, playerX.playerName, playerX.playerType);
        this.playerO = new PlayerDTO(playerO.id, playerO.playerName, playerO.playerType);
        this.playerWinner = new PlayerDTO(playerWinner.id, playerWinner.playerName, playerWinner.playerType);
        this.boardState = boardState;
        this.lastSavedDate = lastSavedDate;
    }
}