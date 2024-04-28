export class PlayerDTO {
  constructor(id, playerName, playerType) {
    /** @type {number} */
    this.id = id;
    /** @type {string} */
    this.playerName = playerName;
    /** @type {string} */
    this.playerType = playerType;
  }
}