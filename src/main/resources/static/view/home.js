import { newGame, makeMove, saveGame, getAllGames, getGame } from "/api-client/gameClient.js";
import { PlayerDTO } from "/dtos/playerDTO.js";
import { loadBoard, loadPlayers, loadTurns, openSpinner, closeSpinner, isXTurn } from '/view/gameView.js'

document.addEventListener('DOMContentLoaded', async function () {

  const cells = document.querySelectorAll('.cell');
  const playersRadio = document.querySelectorAll('.players');
  const turnIndicator = document.getElementById('turn-indicator');
  const playersLabel = document.getElementById('players');
  const divListContainer = document.getElementById('list-container');
  let currentPlayer;
  let game;

  const newGameButton = document.getElementById("new-game-button");
  const loadGameButton = document.getElementById("load-game-button");
  const saveGameButton = document.getElementById("save-game-button");
  const gameContainer = document.getElementById("game-container");
  const radioOnePlayer = document.getElementById("radioOnePlayer");
  const playWithXRadio = document.getElementById("playWithXRadio");
  const playWithX = document.getElementById("playWithX");
  const playWithO = document.getElementById("playWithO");

  // MAKE MOVE
  async function handleCellClick(index) {
    openSpinner();
    if (game.board.split('')[index] !== '-') {
      closeSpinner();
      return;
    }

    game = await makeMove(game, index)
    await loadBoard(game, cells);
    console.log(await isXTurn(game.board.split('')));
    currentPlayer = await isXTurn(game.board.split('')) ? "X" : "O";

    if (game.boardState === 'FINISHED') {
      closeSpinner();
      if (game.playerWinner != undefined) {
        alert(`¡${game.playerWinner.playerName} ha ganado!`);
      } else {
        alert('¡Empate!');
      }

      return;
    }
    if (currentPlayer === 'X') {
      loadTurns(turnIndicator, game.playerX)
    } else {
      loadTurns(turnIndicator, game.playerO)
    }
    closeSpinner();
  }

  async function handleRadioClick() {
    gameContainer.classList.add("d-none");
    saveGameButton.classList.add("d-none");
    if (radioOnePlayer.checked) {
      playWithX.classList.remove("d-none");
      playWithO.classList.remove("d-none");
    } else {
      playWithX.classList.add("d-none");
      playWithO.classList.add("d-none");
    }
  }

  /**
   * NEW GAME BUTTON
   */
  newGameButton.addEventListener("click", async function () {

    let playerXName = ""
    let playerOName = ""
    let playerX
    let playerO

    if (radioOnePlayer.checked) {
      if (playWithXRadio.checked) {
        playerXName = prompt("Ingrese el nombre del jugador para X:");
        playerOName = "Agente IA"
        playerX = new PlayerDTO(0, playerXName, "PERSON")
        playerO = new PlayerDTO(0, playerOName, "IA")
      } else {
        playerXName = "Agente IA"
        playerOName = prompt("Ingrese el nombre del jugador para O:");
        playerX = new PlayerDTO(0, playerXName, "IA")
        playerO = new PlayerDTO(0, playerOName, "PERSON")
      }
    } else {
      playerXName = prompt("Ingrese el nombre del jugador para X:");
      playerOName = prompt("Ingrese el nombre del jugador para O:");
      playerX = new PlayerDTO(0, playerXName, "PERSON")
      playerO = new PlayerDTO(0, playerOName, "PERSON")
    }

    const gameName = prompt("Ingrese el nombre del juego:");

    if (playerXName !== "" && playerOName !== "" && gameName) {
      openSpinner();

      game = await newGame([playerX, playerO], gameName, cells)
      await loadBoard(game, cells);
      saveGameButton.classList.remove("d-none");
      currentPlayer = await isXTurn(game.board.split('')) ? "X" : "O";
      await loadPlayers(playersLabel, game.playerX, game.playerO)
      if (currentPlayer === 'X') {
        loadTurns(turnIndicator, game.playerX)
      } else {
        loadTurns(turnIndicator, game.playerO)
      }
      closeSpinner();
      gameContainer.classList.remove("d-none");
    } else {
      alert("Datos ingresados incorrectos")
    }
  });

  /**
   * LOAD BUTTON
   */
  loadGameButton.addEventListener("click", async function () {
    const listContainer = document.createElement('div');
    listContainer.classList.add('list-group');

    // Array con elementos de ejemplo
    const elements = await getAllGames();

    elements.forEach((element, index) => {
      const listItem = document.createElement('button');
      listItem.classList.add('list-group-item', 'list-group-item-action');
      listItem.textContent = "Juego: " + element.gameName + ", Jugadores: " + element.playerX.playerName + " VS " + element.playerO.playerName + ", Fecha: " + element.lastSavedDate;
      listItem.addEventListener('click', async function () {
        
        openSpinner()
        game = await getGame(element.id)
        loadBoard(game, cells)
        divListContainer.innerHTML = '';
        saveGameButton.classList.remove("d-none");
        gameContainer.classList.remove("d-none");
        currentPlayer = await isXTurn(game.board.split('')) ? "X" : "O";
        await loadPlayers(playersLabel, game.playerX, game.playerO)
        if (currentPlayer === 'X') {
          loadTurns(turnIndicator, game.playerX)
        } else {
          loadTurns(turnIndicator, game.playerO)
        }
        closeSpinner()
      });
      listContainer.appendChild(listItem);
    });

    gameContainer.classList.add("d-none");
    divListContainer.innerHTML = '';
    divListContainer.appendChild(listContainer);

  });

  /**
   * SAVE BUTTON
   */
  saveGameButton.addEventListener("click", async function () {
    game = await saveGame(game)
    alert("Juego guardado")
  });

  cells.forEach((cell, index) => {
    cell.addEventListener('click', () => handleCellClick(index));
  });

  playersRadio.forEach((radio, index) => {
    radio.addEventListener('click', () => handleRadioClick());
  });
  

});