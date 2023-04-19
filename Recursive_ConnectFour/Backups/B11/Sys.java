/* Developed By: Mohammadarya Faghihy
 * Date: Jan 9, 2022
 * Version     : 1.8 */
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
  
  private boolean isDimensionsValid(int row, int col) {
    return (row >= GRID_ROW || row < 0) || ( (col >= GRID_COL || col < 0) );
  }
  
  private boolean isSlotsEqual(Slot slot1, Slot slot2, Slot slot3, Slot slot4) {
    return (slot1.GetVal() == slot2.GetVal() && slot2.GetVal() == slot3.GetVal() && slot3.GetVal() == slot4.GetVal());
  }
  
  /* Recursively checks a diagonal for matches (up-right direction) in 4-slot groups
   * @param startR - The starting row to check from
   * @param startC - The starting column to check from
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
   * @param startC - The starting column to check from
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
   * @param R      - The row from to check
   * @param startC - The column from which to begin checking
   * @return       - Returns true if a match has been made in that row,
   *               - Returns false if no matches have been made in the row
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
   * @param startR - The row from which to begin checking
   * @param C      - The column to check
   * @return       - Returns true if a match has been made in that column,
   *               - Returns false if no matches have been made in the column
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
  
  /* Recursively checks the grid for up-right diagonal matches (of 4 slots)
   * @param startR - The row from which to begin checking
   * @param startC - The column to check from
   * @return       - Returns true if a match has been made in the grid,
   *               - Returns false if no matches have been made in the grid
   */
  public boolean CheckRightDig(int startR, int startC) {
    
    if ( this.isDimensionsValid(startR, startC) ) { // Only checks if dimensions are valid
      return false;
    }
    
    if (this.rightDigCheck(startR, startC)) { // Helper method used to recursively check the diagonal
      return true;
    } else {
      if (startR == GRID_ROW - 1) { // If the last row is reached ([5][0]), the search is now made from columns in the last row
        // Ex. [5][0] to [5][1], then [5][2] & onwards
        return this.CheckRightDig(startR, startC+1);
      } else {
        return this.CheckRightDig(startR + 1, startC);
      }
    }
    
  }
  
  /* Recursively checks the grid for up-right diagonal matches (of 4 slots)
   * @param startR - The row from which to begin checking
   * @param startC - The column to check from
   * @return       - Returns true if a match has been made in the grid,
   *               - Returns false if no matches have been made in the grid
   */
  public boolean CheckLeftDig(int startR, int startC) { // Similar to CheckRightDig(), except it checks in the reverse direction,
    // with its own helper method
    if ( this.isDimensionsValid(startR, startC) ) {
      return false;
    }
    
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
  
  /* Recursively checks the grid for row matches (of 4 slots)
   * @param startR - The row from which to begin checking
   * @return       - Returns true if a match has been made in the grid,
   *               - Returns false if no matches have been made in the grid
   */
  private boolean CheckRow(int startR) {
    
    if (startR >= GRID_ROW || startR < 0) {
      return false;
    }
    
    if (this.rowCheck(startR, 0)) { // Starts checking the row from the first column
      return true;
    } else {
      return this.CheckRow(startR + 1); // Continues checking if no match has been found yet
    }
    
  }
  
  /* Recursively checks the grid for column matches (of 4 slots)
   * @param startC - The column from which to begin checking
   * @return       - Returns true if a match has been made in the grid,
   *               - Returns false if no matches have been made in the grid
   */
  private boolean CheckCol(int startC) {

    if (startC >= GRID_COL || startC < 0) {
      return false;
    }
    
    if (this.colCheck(0, startC)) { // Starts checking from the first row
      return true;
    } else {
      return this.CheckCol(startC + 1); // Checks further columns if no match has been found
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
  
  /* Traverses the grid to see if any match of 4-slots has been made
   * @return       - Returns true if a match has been found (a player has won the game),
   *               - Returns false if no match has been found (game should continue)
   */
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