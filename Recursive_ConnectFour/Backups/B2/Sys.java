public class Sys {
  
  private Slot[][] grid;
  private Player[] players;
  private Player currPlayer;
  
  public Sys() {
    this.grid = new Slot[6][7];
    this.players = new Player[2];
    this.players[0] = new Player(Player.ID_PLAYER0);
    this.players[1] = new Player(Player.ID_PLAYER1);
    this.currPlayer = players[0];
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
  
  private boolean rightDigCheck(int[][] arr, int startR, int startC) { // Row Checker
    if (startR - 3 < 0 || startC + 3 >= arr[0].length) {
      return false;
    }
    if (arr[startR][startC] == arr[startR - 1][startC+1] && arr[startR - 1][startC + 1] == arr[startR - 2][startC + 2] && arr[startR - 2][startC + 2] == arr[startR - 3][startC + 3]) {
      return true;
    } else {
      return this.rightDigCheck(arr, startR - 1, startC + 1);
    }    
  }
  
  private boolean leftDigCheck(int[][] arr, int startR, int startC) { // Row Checker
    
    if (startR - 3 < 0 || startC - 3 < 0) {
      return false;
    }
    if (arr[startR][startC] == arr[startR - 1][startC-1] && arr[startR - 1][startC - 1] == arr[startR - 2][startC - 2] && arr[startR - 2][startC - 2] == arr[startR - 3][startC - 3]) {
      return true;
    } else {
      return this.leftDigCheck(arr, startR - 1, startC - 1);
    }    
  }
  
  public boolean rowChecker(int[][] arr, int R, int startC) {
    
    if (startC + 3 >= arr[R].length) {
      return false;
    }
    
    if (arr[R][startC] == arr[R][startC + 1] && arr[R][startC + 1] == arr[R][startC + 2] && arr[R][startC + 2] == arr[R][startC + 3]) {
      return true;
    } else {
      return rowChecker(arr, R, startC + 1);
    }
    
  }
  
  public boolean colChecker(int[][] arr, int startR, int C) {
    
    if (startR + 3 >= arr.length) {
      return false;
    }
    
    if (arr[startR][C] == arr[startR + 1][C] && arr[startR + 1][C] == arr[startR + 2][C] && arr[startR + 2][C] == arr[startR + 3][C]) {
      return true;
    } else {
      return colChecker(arr, startR + 1, C);
    }
    
  }
  
  public boolean CheckRow(int[][] arr, int startR) {
    System.out.println("Row: " + startR);
    if (startR >= arr.length || startR < 0) {
      return false;
    }
    
    if (this.rowChecker(arr, startR, 0)) {
      return true;
    } else {
      return CheckRow(arr, startR + 1);
    }
    
  }
  
  
  public boolean CheckCol(int[][] arr, int startC) {
    System.out.println("Col: " + startC);
    if (startC >= arr[0].length || startC < 0) {
      return false;
    }
    
    if (this.colChecker(arr, 0, startC)) {
      return true;
    } else {
      return CheckCol(arr, startC + 1);
    }
    
  }
  
  public boolean CheckRightDig(int[][] arr, int startR, int startC) { // Upright second half ***
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC + 3 >= arr[0].length ) {
      if (startR == arr.length - 1) {
        return CheckRightDig(arr, startR, startC+1);
      } else {
        return CheckRightDig(arr, startR + 1, startC);
      }
    } else {
      if (this.rightDigCheck(arr, startR, startC)) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return CheckRightDig(arr, startR, startC+1);
        } else {
          return CheckRightDig(arr, startR + 1, startC);
        }
      }
    }
    
  }
  
  public boolean CheckLeftDig(int[][] arr, int startR, int startC) { // Upright second half
    
    System.out.println("Dimensions: " + startR + " - " + startC);
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC - 3 < 0 ) {
      if (startR == arr.length - 1) {
        return CheckLeftDig(arr, startR, startC-1);
      } else {
        return CheckLeftDig(arr, startR + 1, startC);
      }
    } else {
      if (this.leftDigCheck(arr, startR, startC)) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return CheckLeftDig(arr, startR, startC-1);
        } else {
          return CheckLeftDig(arr, startR + 1, startC);
        }
      }
    }
    
  }
  
}