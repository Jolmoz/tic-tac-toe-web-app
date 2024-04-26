package com.jolmoz.threelinewebapp.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jolmoz.threelinewebapp.dto.GameDTO;
import com.jolmoz.threelinewebapp.dto.PlayerDTO;
import com.jolmoz.threelinewebapp.model.Game;
import com.jolmoz.threelinewebapp.repository.GameRepository;
import com.jolmoz.threelinewebapp.repository.PlayerRepository;

@Service
public class ThreeLineControl {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    public GameDTO getGame(long gameId) {
        return new GameDTO(gameRepository.getReferenceById(gameId));
    }

    public List<GameDTO> getAllGames() {
        List<GameDTO> listOfGames = new ArrayList<>();
        List<Game> listOfEntityGames = gameRepository.findAll();
        for (Game game : listOfEntityGames) {
            listOfGames.add(new GameDTO(game));
        }
        return listOfGames;
    }

    public GameDTO saveGame(GameDTO game) {
        if (game.getId() == 0) {
            Game gameEntity = new Game(game);
            return new GameDTO(gameRepository.save(gameEntity));
        } else {
            Game gameEntity = gameRepository.getReferenceById(game.getId());
            gameEntity.setBoard(game.getBoard());
            gameEntity.setBoardState(game.getBoardState());
            gameEntity.setLastSavedDate(new Date());
            return new GameDTO(gameRepository.save(gameEntity));
        }
    }

    public GameDTO getNewGame(PlayerDTO playerX, PlayerDTO playerO, String gameName) {
        return new GameDTO(playerX, playerO, gameName);
    }
}
