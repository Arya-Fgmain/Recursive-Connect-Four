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
  
  private boolean isSlotFull(int row, int col) {
    if (this.grid[row][col] != null) {
      return true;
    }
    return false;
  }
  
  // -1 if invalid directions, 0 if successful, and -2 if selected slot is full
  public int insertSlot(int startR, int c, Slot newSlot) {
    int res = -1;
    if ( !( (startR >= this.grid.length || startR < 0) || ( c >= this.grid[0].length || c < 0 ) ) ) {
      if ( !this.isSlotFull(startR, c) ) { // this.grid[startR][c] == null
        res = 0;
        this.grid[startR][c] = newSlot;
        if (startR + 1 < this.grid.length) {
          int nextInsertResult = this.insertSlot(startR+1, c, newSlot);
          if (nextInsertResult == 0) {
            this.grid[startR][c] = null;
          }
        }
      } else {
        res = -2;
      }
    }   
    return res;
  }
  
  public boolean rightDigCheck(Slot[][] arr, int startR, int startC) { // Row Checker
    if (startR - 3 < 0 || startC + 3 >= arr[0].length) {
      return false;
    }
    
    if (arr[startR][startC] != null && arr[startR - 1][startC+1] != null && arr[startR - 2][startC + 2] != null && arr[startR - 3][startC + 3] != null) {
      int val1 = arr[startR][startC].GetVal();
      int val2 = arr[startR - 1][startC+1].GetVal();
      int val3 = arr[startR - 2][startC + 2].GetVal();
      int val4 = arr[startR - 3][startC + 3].GetVal();
      boolean isEqual = val1 == val2 && val2 == val3 && val3 == val4;
      if (isEqual) {
        return true;
      }
    }
    return this.rightDigCheck(arr, startR - 1, startC + 1);
  }
  
  public boolean leftDigCheck(Slot[][] arr, int startR, int startC) { // Row Checker
    
    if (startR - 3 < 0 || startC - 3 < 0) {
      return false;
    }
    
    if (arr[startR][startC] != null && arr[startR - 1][startC-1] != null && arr[startR - 2][startC - 2] != null && arr[startR - 3][startC - 3] != null) {
      int val1 = arr[startR][startC].GetVal();
      int val2 = arr[startR - 1][startC-1].GetVal();
      int val3 = arr[startR - 2][startC - 2].GetVal();
      int val4 = arr[startR - 3][startC - 3].GetVal();
      boolean isEqual = val1 == val2 && val2 == val3 && val3 == val4;
      if (isEqual) {
        return true;
      }
    }
    return this.leftDigCheck(arr, startR - 1, startC - 1);
  }
  
  public boolean rowCheck(Slot[][] arr, int R, int startC) {
    
    if (startC + 3 >= arr[R].length) {
      return false;
    }
    
    if (arr[R][startC] != null && arr[R][startC + 1] != null && arr[R][startC + 2] != null && arr[R][startC + 3] != null) {
      int val1 = arr[R][startC].GetVal();
      int val2 = arr[R][startC + 1].GetVal();
      int val3 = arr[R][startC + 2].GetVal();
      int val4 = arr[R][startC + 3].GetVal();
      boolean isEqual = val1 == val2 && val2 == val3 && val3 == val4;
      if (isEqual) {
        return true;
      }
    }
    return this.rowCheck(arr, R, startC + 1);
  }
  
  public boolean colCheck(Slot[][] arr, int startR, int C) {
    
    if (startR + 3 >= arr.length) {
      return false;
    }
    
    if (arr[startR][C] != null && arr[startR + 1][C] != null && arr[startR + 2][C] != null && arr[startR + 3][C] != null) {
      int val1 = arr[startR][C].GetVal();
      int val2 = arr[startR + 1][C].GetVal();
      int val3 = arr[startR + 2][C].GetVal();
      int val4 = arr[startR + 3][C].GetVal();
      boolean isEqual = val1 == val2 && val2 == val3 && val3 == val4;
      if (isEqual) {
        return true;
      }
    }
    return this.colCheck(arr, startR + 1, C);
    
  }
  
  public boolean CheckRightDig(Slot[][] arr, int startR, int startC) { // Upright second half ***
    
    //System.out.println("Dimensions: " + startR + " - " + startC);
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC + 3 >= arr[0].length ) {
      if (startR == arr.length - 1) {
        return this.CheckRightDig(arr, startR, startC+1);
      } else {
        return this.CheckRightDig(arr, startR + 1, startC);
      }
    } else {
      if (this.rightDigCheck(arr, startR, startC)) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return this.CheckRightDig(arr, startR, startC+1);
        } else {
          return this.CheckRightDig(arr, startR + 1, startC);
        }
      }
    }
    
  }
  
  public boolean CheckLeftDig(Slot[][] arr, int startR, int startC) { // Upright second half
    
    //System.out.println("Dimensions: " + startR + " - " + startC);
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC - 3 < 0 ) {
      if (startR == arr.length - 1) {
        return this.CheckLeftDig(arr, startR, startC-1);
      } else {
        return this.CheckLeftDig(arr, startR + 1, startC);
      }
    } else {
      if (this.leftDigCheck(arr, startR, startC)) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return this.CheckLeftDig(arr, startR, startC-1);
        } else {
          return this.CheckLeftDig(arr, startR + 1, startC);
        }
      }
    }
    
  }
  
  
  public boolean CheckRow(Slot[][] arr, int startR) {
    //System.out.println("Row: " + startR);
    if (startR >= arr.length || startR < 0) {
      return false;
    }
    
    if (this.rowCheck(arr, startR, 0)) {
      return true;
    } else {
      return this.CheckRow(arr, startR + 1);
    }
    
  }
  
  
  public boolean CheckCol(Slot[][] arr, int startC) {
    //System.out.println("Col: " + startC);
    if (startC >= arr[0].length || startC < 0) {
      return false;
    }
    
    if (this.colCheck(arr, 0, startC)) {
      return true;
    } else {
      return this.CheckCol(arr, startC + 1);
    }
    
  }
  
  public boolean CheckWinner() {
    boolean diagonalWin = this.CheckRightDig(this.grid, 0, 0) || this.CheckLeftDig(this.grid, 0, 6);
    boolean rowWin      = this.CheckRow(this.grid, 0);
    boolean colWin      = this.CheckCol(this.grid, 0);
    if (diagonalWin || rowWin || colWin) {
      return true;
    }
    return false;
  }
  
}