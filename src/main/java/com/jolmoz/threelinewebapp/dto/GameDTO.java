package com.jolmoz.threelinewebapp.dto;

import java.util.Date;

import com.jolmoz.threelinewebapp.model.Game;
import com.jolmoz.threelinewebapp.model.Game.BoardState;

public class GameDTO {
    private long id;
    private String gameName;
    private String board;
    private PlayerDTO playerX;
    private PlayerDTO playerO;
    private PlayerDTO playerWinner;
    private BoardState boardState;
    private Date lastSavedDate;

    public GameDTO() {

    }

    public GameDTO(PlayerDTO playerX, PlayerDTO playerO, String gameName) {
        this.boardState = BoardState.PLAYING;
        this.gameName = gameName;
        this.lastSavedDate = new Date();
        this.playerX = playerX;
        this.playerO = playerO;
    }

    public GameDTO(Game game) {
        if (game != null) {
            this.id = game.getId();
            this.gameName = game.getGameName();
            this.board = game.getBoard();
            this.playerX = new PlayerDTO(game.getPlayerX());
            this.playerO = new PlayerDTO(game.getPlayerO());

            if (game.getPlayerWinner() != null) {
                this.playerWinner = new PlayerDTO(game.getPlayerWinner());
            }

            this.boardState = game.getBoardState();
            this.lastSavedDate = game.getLastSavedDate();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public PlayerDTO getPlayerX() {
        return playerX;
    }

    public void setPlayerX(PlayerDTO playerX) {
        this.playerX = playerX;
    }

    public PlayerDTO getPlayerO() {
        return playerO;
    }

    public void setPlayerO(PlayerDTO playerO) {
        this.playerO = playerO;
    }

    public PlayerDTO getPlayerWinner() {
        return playerWinner;
    }

    public void setPlayerWinner(PlayerDTO playerWinner) {
        this.playerWinner = playerWinner;
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public void setBoardState(BoardState boardState) {
        this.boardState = boardState;
    }

    public Date getLastSavedDate() {
        return lastSavedDate;
    }

    public void setLastSavedDate(Date lastSavedDate) {
        this.lastSavedDate = lastSavedDate;
    }

}
