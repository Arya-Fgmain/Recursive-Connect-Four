/* Developed By: Mohammadarya Faghihy
 * Date: Jan 9, 2022
 * Version     : 1.0 */
public class Sys {
  
  private Slot[][] grid;
  private Player[] players;
  private Player currPlayer;
  
  public final static int MOVE_MADE         =  0;
  public final static int INVALID_DIRECTION = -1;
  public final static int SLOT_FULL         = -2;
  public final static int GRID_ROW          =  6;
  public final static int GRID_COL          =  7;
  
  public Sys() {
    this.grid = new Slot[6][7];
    this.players = new Player[2];
    this.players[0] = new Player(Player.ID_PLAYER0);
    this.players[1] = new Player(Player.ID_PLAYER1);
    this.currPlayer = players[0];
  }
  
  // HELPER METHODS
  
  /* Recursively checks a diagonal for matches (up-right direction) in 4-slot groups
   * @param startR - The starting row to check from
   * @param c      - The starting column to check from
   * @return       - Returns true if a match has been made,
   *               - Returns false if no matches have been made
   */
  private boolean rightDigCheck(int startR, int startC) {
    if (startR - 3 < 0 || startC + 3 >= GRID_COL) { // Checking validity of dimensions
      return false;
    }
    // If any slot out of 4 is null, no checking is done
    if (this.grid[startR][startC] != null && this.grid[startR - 1][startC+1] != null && this.grid[startR - 2][startC + 2] != null && this.grid[startR - 3][startC + 3] != null) {
      // Storing values of slots to check them
      int val1 = this.grid[startR][startC].GetVal();
      int val2 = this.grid[startR - 1][startC+1].GetVal();
      int val3 = this.grid[startR - 2][startC + 2].GetVal();
      int val4 = this.grid[startR - 3][startC + 3].GetVal();
      boolean isEqual = val1 == val2 && val2 == val3 && val3 == val4;
      if (isEqual) {
        return true;
      }
    }
    return this.rightDigCheck(startR - 1, startC + 1); // Recursive call to continue the check for later blocks in the diagonal
  }
  
  /* Recursively checks a (down-right direction) in 4-slot groups
   * @param startR - The starting row to check from
   * @param c      - The starting column to check from
   * @return       - Returns true if a match has been made,
   *               - Returns false if no matches have been made
   */
  private boolean leftDigCheck(int startR, int startC) {
    // Code is in similar structure to rightDigCheck(), except for the order in which it checks
    if (startR - 3 < 0 || startC - 3 < 0) {
      return false;
    }
    
    if (this.grid[startR][startC] != null && this.grid[startR - 1][startC-1] != null && this.grid[startR - 2][startC - 2] != null && this.grid[startR - 3][startC - 3] != null) {
      int val1 = this.grid[startR][startC].GetVal();
      int val2 = this.grid[startR - 1][startC-1].GetVal();
      int val3 = this.grid[startR - 2][startC - 2].GetVal();
      int val4 = this.grid[startR - 3][startC - 3].GetVal();
      boolean isEqual = val1 == val2 && val2 == val3 && val3 == val4;
      if (isEqual) {
        return true;
      }
    }
    return this.leftDigCheck(startR - 1, startC - 1);
  }
  
  /* Recursively checks a row for matches in 4-slot groups
   * @param R           - The row from to check
   * @param startc      - The column from which to begin checking
   * @return            - Returns true if a match has been made in that row,
   *                    - Returns false if no matches have been made in the row
   */
  private boolean rowCheck(int R, int startC) {
    
    if (startC + 3 >= this.grid[R].length) { // Checking validity of dimensions
      return false;
    }
    // Only checks if the slots are full
    if (this.grid[R][startC] != null && this.grid[R][startC + 1] != null && this.grid[R][startC + 2] != null && this.grid[R][startC + 3] != null) {
      // Checks the given column
      int val1 = this.grid[R][startC].GetVal(); // Checks the given column
      // and the three coming after it
      int val2 = this.grid[R][startC + 1].GetVal();
      int val3 = this.grid[R][startC + 2].GetVal();
      int val4 = this.grid[R][startC + 3].GetVal();
      boolean isEqual = val1 == val2 && val2 == val3 && val3 == val4;
      if (isEqual) {
        return true;
      }
    }
    return this.rowCheck(R, startC + 1); // Recursive call to continue searching the row
  }
  
  
  /* Recursively checks a column for matches in 4-slot groups
   * @param R           - The row from which to begin checking
   * @param startc      - The column to check
   * @return            - Returns true if a match has been made in that column,
   *                    - Returns false if no matches have been made in the column
   */
  private boolean colCheck(int startR, int C) {
    
    if (startR + 3 >= GRID_ROW) { // Only checks for groups of four
      return false;
    }
    // Below: Only checks if slots are available
    if (this.grid[startR][C] != null && this.grid[startR + 1][C] != null && this.grid[startR + 2][C] != null && this.grid[startR + 3][C] != null) {
      int val1 = this.grid[startR][C].GetVal();
      int val2 = this.grid[startR + 1][C].GetVal();
      int val3 = this.grid[startR + 2][C].GetVal();
      int val4 = this.grid[startR + 3][C].GetVal();
      boolean isEqual = val1 == val2 && val2 == val3 && val3 == val4;
      if (isEqual) {
        return true;
      }
    }
    return this.colCheck(startR + 1, C); // Recursive call to check from further rows
    
  }
  
  /* Recursively checks the grid for diagonal matches (up-right direction)
   * @param R           - The row from which to check
   * @param startc      - The column to check from
   * @return            - Returns true if a match has been found in the grid,
   *                    - Returns false if no matches have been found
   */
  private boolean CheckRightDig(int startR, int startC) {
    
    if ( (startR >= GRID_ROW || startR < 0) || ( (startC >= GRID_COL || startC < 0) ) ) { // Only checks if dimensions are not placed outside the grid
      return false;
    }
    
    if ( startR - 3 < 0 || startC + 3 >= GRID_COL ) { // Checking is further refined: Only if 4 slots are available to check in the diagonal
      if (startR == GRID_ROW - 1) { // Once the last row's diagonal is reached (slot [5][0] to slot [0][6]), 
        // the checking is made horizontal (searches for diagonals from the fifth row, so [5][1], [5][2], etc
        return this.CheckRightDig(startR, startC+1);
      } else {
        return this.CheckRightDig(startR + 1, startC);
      }
    } else { // If three 
      if (this.rightDigCheck(startR, startC)) {
        return true;
      } else {
        if (startR == GRID_ROW - 1) {
          return this.CheckRightDig(startR, startC+1);
        } else {
          return this.CheckRightDig(startR + 1, startC);
        }
      }
    }
    
  }
  
  private boolean CheckLeftDig(int startR, int startC) { // Upright second half
    
    //System.out.println("Dimensions: " + startR + " - " + startC);
    
    if ( (startR >= GRID_ROW || startR < 0) || ( (startC >= GRID_COL || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC - 3 < 0 ) {
      if (startR == GRID_ROW - 1) {
        return this.CheckLeftDig(startR, startC-1);
      } else {
        return this.CheckLeftDig(startR + 1, startC);
      }
    } else {
      if (this.leftDigCheck(startR, startC)) {
        return true;
      } else {
        if (startR == GRID_ROW - 1) {
          return this.CheckLeftDig(startR, startC-1);
        } else {
          return this.CheckLeftDig(startR + 1, startC);
        }
      }
    }
    
  }
  
  
  private boolean CheckRow(int startR) {
    //System.out.println("Row: " + startR);
    if (startR >= GRID_ROW || startR < 0) {
      return false;
    }
    
    if (this.rowCheck(startR, 0)) {
      return true;
    } else {
      return this.CheckRow(startR + 1);
    }
    
  }
  
  
  private boolean CheckCol(int startC) {
    //System.out.println("Col: " + startC);
    if (startC >= GRID_COL || startC < 0) {
      return false;
    }
    
    if (this.colCheck(0, startC)) {
      return true;
    } else {
      return this.CheckCol(startC + 1);
    }
    
  }
  
  
  // PUBLIC METHODS
  
  
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
  
  /* Checks if the slot at the given position is full
   * @param row - The row in which the slot resides
   * @param col - The column of the slot in that row
   * @return    - Returns true if the slot is full,
   *            - Returns false if the slot if empty
   */
  private boolean isSlotFull(int row, int col) {
    if (this.grid[row][col] != null) {
      return true;
    }
    return false;
  }
  
  /* Recursively inserts a slot into the grid
   * @param startR - The starting row where the slot will be placed
   * @param c      - The column where the slot is to be placed
   * @return       - Returns 0 if the slot is inserted successfully,
   *               - Returns -1 if the given directions put the slot out of the grid,
   *               - Returns -2 if the given slot is full
   */
  public int InsertSlot(int startR, int c, Slot newSlot) {
    int res = INVALID_DIRECTION;
    if ( !( (startR >= GRID_ROW || startR < 0) || ( c >= GRID_COL || c < 0 ) ) ) { // Checking validity of dimensions
      if ( !this.isSlotFull(startR, c) ) { // Checking if slot is full
        res = MOVE_MADE;
        this.grid[startR][c] = newSlot;
        if (startR + 1 < GRID_ROW) { // Checking if the slot can go further
          int nextInsertResult = this.InsertSlot(startR+1, c, newSlot);
          if (nextInsertResult == MOVE_MADE) {
            this.grid[startR][c] = null; // Erasing slot from old position
          }
        }
      } else {
        res = SLOT_FULL;
      }
    }   
    return res;
  }
  
  public boolean CheckWinner() {
    boolean diagonalWin = this.CheckRightDig(0, 0) || this.CheckLeftDig(0, 6);
    boolean rowWin      = this.CheckRow(0);
    boolean colWin      = this.CheckCol(0);
    if (diagonalWin || rowWin || colWin) {
      return true;
    }
    return false;
  }
  
}