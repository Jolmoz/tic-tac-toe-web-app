package com.jolmoz.threelinewebapp.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jolmoz.threelinewebapp.dto.GameDTO;
import com.jolmoz.threelinewebapp.dto.PlayerDTO;
import com.jolmoz.threelinewebapp.model.Game;
import com.jolmoz.threelinewebapp.model.Player.PlayerType;
import com.jolmoz.threelinewebapp.repository.GameRepository;
import com.jolmoz.threelinewebapp.repository.PlayerRepository;

import aima.core.environment.tictactoe.TicTacToeGame;
import aima.core.environment.tictactoe.TicTacToeState;
import aima.core.search.adversarial.AdversarialSearch;
import aima.core.search.adversarial.MinimaxSearch;
import aima.core.util.datastructure.XYLocation;

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

    public GameDTO newGame(PlayerDTO playerX, PlayerDTO playerO, String gameName) {
        TicTacToeGame game = new TicTacToeGame();
        TicTacToeState currState = game.getInitialState();
        GameDTO gameDTO = new GameDTO(playerX, playerO, gameName);
        gameDTO.setBoard(currState.toString().replaceAll("\\s+", ""));
        return gameDTO;
    }

    public GameDTO makeMove(GameDTO gameDTO, int x, int y) {
        TicTacToeGame game = new TicTacToeGame();
        char[] gameStateArray = gameDTO.getBoard().toCharArray();
        TicTacToeState gameState = this.loadBoard(gameStateArray);
        gameState.mark(x, y);
        String nextPlayerInGame = game.getPlayer(gameState);
        PlayerType nextPlayerType = null;
        switch (nextPlayerInGame) {
            case "X":
                nextPlayerType = gameDTO.getPlayerX().getPlayerType();
                break;

            case "O":
                nextPlayerType = gameDTO.getPlayerO().getPlayerType();
                break;
            default:
                break;
        }

        if (nextPlayerType.equals(PlayerType.IA)) {
            AdversarialSearch<TicTacToeState, XYLocation> search = MinimaxSearch
                    .createFor(game);
            XYLocation action = search.makeDecision(gameState);
            gameState = game.getResult(gameState, action);
        }

        gameDTO.setBoard(gameState.toString().replaceAll("\\s+", ""));
        return gameDTO;
    }

    private TicTacToeState loadBoard(char[] gameStateArray) {
        TicTacToeState gameState = new TicTacToeState();

        // Asigna el estado de la partida basado en el arreglo de caracteres
        List<Integer[]> xMoves = new ArrayList<>();
        List<Integer[]> oMoves = new ArrayList<>();
        for (int i = 0; i < gameStateArray.length; i++) {

            int x = i % 3;
            int y = Math.floorDiv(i, 3);

            char symbol = gameStateArray[i];
            if (symbol == 'X') {
                Integer xy[] = { x, y };
                xMoves.add(xy);
            } else if (symbol == 'O') {
                Integer xy[] = { x, y };
                oMoves.add(xy);
            }
        }
        for (int i = 0; i < 6; i++) {
            if (i < xMoves.size()) {
                gameState.mark(xMoves.get(i)[0], xMoves.get(i)[1]);
            }
            if (i < oMoves.size()) {
                gameState.mark(oMoves.get(i)[0], oMoves.get(i)[1]);
            }
        }
        return gameState;
    }
}
