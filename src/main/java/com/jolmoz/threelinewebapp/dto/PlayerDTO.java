package com.jolmoz.threelinewebapp.dto;

import com.jolmoz.threelinewebapp.model.Player;
import com.jolmoz.threelinewebapp.model.Player.PlayerType;

public class PlayerDTO {
    private long id;
    private String playerName;
    private PlayerType playerType;

    public PlayerDTO() {
    }

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.playerName = player.getPlayerName();
        this.playerType = player.getPlayerType();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

}
