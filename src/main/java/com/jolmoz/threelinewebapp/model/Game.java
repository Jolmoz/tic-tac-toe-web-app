package com.jolmoz.threelinewebapp.model;

import java.util.Date;

import com.jolmoz.threelinewebapp.dto.GameDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String gameName;

    @Column(nullable = false)
    private String board;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_x_id")
    private Player playerX;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_o_id")
    private Player playerO;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_winner_id", nullable = true)
    private Player playerWinner;

    @Column(nullable = false)
    private BoardState boardState;

    @Column(nullable = false)
    private Date lastSavedDate;

    public Game() {

    }

    public Game(GameDTO gameDTO) {
        this.gameName = gameDTO.getGameName();
        this.board = gameDTO.getBoard();
        this.boardState = gameDTO.getBoardState();
        this.lastSavedDate = new Date();
        this.playerO = new Player(gameDTO.getPlayerO());
        this.playerX = new Player(gameDTO.getPlayerX());
        if (gameDTO.getPlayerWinner() != null) {
            this.playerWinner = new Player(gameDTO.getPlayerWinner());
        }

    }

    public enum BoardState {
        PLAYING, FINISHED
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

    public Player getPlayerX() {
        return playerX;
    }

    public void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public void setPlayerO(Player playerO) {
        this.playerO = playerO;
    }

    public Player getPlayerWinner() {
        return playerWinner;
    }

    public void setPlayerWinner(Player playerWinner) {
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
