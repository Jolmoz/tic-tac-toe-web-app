export async function openSpinner() {
    document.getElementById("overlay").style.display = "block";
    document.querySelector(".spinner-container").style.display = "block";
}

export async function closeSpinner() {
    document.getElementById("overlay").style.display = "none";
    document.querySelector(".spinner-container").style.display = "none";
}


export async function loadBoard(game, cells) {
    let board = game.board.split('')
    for (let i = 0; i < board.length; i++) {
        if (board[i] === '-') {
            cells[i].textContent = ""
            continue
        }
        cells[i].textContent = board[i];
    }
}

export async function loadPlayers(playersLabel, playerX, playerO) {
    playersLabel.textContent = ` ${playerX.playerName} (X) VS ${playerO.playerName} (O)`;
}

export async function loadTurns(turnIndicator, player) {
    turnIndicator.textContent = `Turno de: ${player.playerName}`;
}

export async function isXTurn(board) {
    let countX = 0
    let countO = 0
    for (let cell of board) {
        if (cell == 'X') {
            countX++
        } else if (cell == 'O') {
            countO++
        }
    }
    return (countX - countO) == 0
}