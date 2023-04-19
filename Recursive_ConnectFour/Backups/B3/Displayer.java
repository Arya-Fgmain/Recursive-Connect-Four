public class Displayer {
  
  public void DisplayGrid(Slot[][] grid) {
    
   this.displayer(grid, 0, 0);
    
  }
  
  private void displayer(Slot[][] grid, int row, int col) {
    
    if (row < grid.length && col < grid[0].length) {
      if (col == 0) System.out.print("|");
      Slot thisSlot = grid[row][col];
      String[] signs = { "(R)", "(B)" };
      String sign;
      if (grid[row][col] == null) {
        sign = "   ";
      } else {
        sign = signs[thisSlot.GetVal()];
      }
      System.out.print(sign + "|");
      col++;
      this.displayer(grid, row, col);
      if (col == grid[0].length - 1) {
        row++;
        col = 0;
        System.out.println();
        this.displayer(grid, row, col);
      }
    }
    
  }
  
}