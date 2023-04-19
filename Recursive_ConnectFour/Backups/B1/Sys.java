public class Sys {
  
  private Slot[][] grid;
  private Player[] players;
  private Player currPlayer;
  
  public Sys() {
    this.grid = new Slot[6][7];
    this.players = new Player[2];
    this.players[0] = new Player(Player.ID_PLAYER0);
    this.players[1] = new Player(Player.ID_PLAYER1);
    this.currPlayer = players[1];
  }
  
  public Slot[][] GetGrid() {
    return this.grid;
  }
  
  public Player GetCurrPlayer() {
    return this.currPlayer;
  }
  
  public void SwitchPlayer() {
    if (this.currPlayer.GetId() == Player.ID_PLAYER0) {
      this.currPlayer = this.players[1];
    } else {
      this.currPlayer = this.players[0];
    }
  }
  
}