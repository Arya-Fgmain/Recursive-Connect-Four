/* Developed By: Arya Faghihy
 * Date: Jan 9, 2022
 * Version     : 2.3 */
public class Displayer {
  
  // HELPER METHODS
  
  /* Recursively traverses & prints the grid
   * @param grid - The 2D array to print
   * @param row  - The row to print
   * @param col  - The column to print
   */
  private void displayer(Slot[][] grid, int row, int col) {
    
    if (row < grid.length && col < grid[0].length) { // Only prints if dimensions are valid
      if (col == 0) { // A beginning | is printed for the first column
        System.out.print("|");
      }
      Slot thisSlot = grid[row][col];
      String[] signs = { "(R)", "(B)" }; // Signs for either Red or Black slots
      String sign;
      if (grid[row][col] == null) {
        sign = "   "; // Sign for no slot
      } else {
        sign = signs[thisSlot.GetVal()];
      }
      System.out.print(sign + "|");
      col++; // Moving to next column
      this.displayer(grid, row, col); // Recursive call for next column
      if (col == grid[0].length - 1) {
        row++; // Move on to next row if last column is printed
        col = 0;
        System.out.println();
        this.displayer(grid, row, col); // Recursive call for next row
      }
    }
    
  }
  
  private String playerGuide() {
    
    return "********** PLAYERS **********\n      PLAYER 0:     RED\n      PLAYER 0:   BLACK\n*****************************\n";
    
  }
  
  // PUBLIC METHODS
  
  public void DisplayGrid(Slot[][] grid) {
   
   System.out.println("  1   2   3   4   5   6   7"); // Column Numbers for easier reference
   this.displayer(grid, 0, 0);
   System.out.println();
    
  }
  
  
  public void DisplayHeader() {
    
    System.out.println(" ________  ________  ________   ________   _______   ________ _________        ___   ___     ");
    System.out.println("|\\   ____\\|\\   __  \\|\\   ___  \\|\\   ___  \\|\\  ___ \\ |\\   ____\\\\___   ___\\     |\\  \\ |\\  \\    ");
    System.out.println("\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\\\ \\  \\ \\   __/|\\ \\  \\___\\|___ \\  \\_|     \\ \\  \\\\_\\  \\   ");
    System.out.println(" \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\_|/_\\ \\  \\       \\ \\  \\       \\ \\______  \\  ");
    System.out.println("  \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\____   \\ \\  \\       \\|_____|\\  \\ ");
    System.out.println("   \\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\  \\ \\__\\             \\ \\__\\");
    System.out.println("    \\|_______|\\|_______|\\|__| \\|__|\\|__| \\|__|\\|_______|\\|_______|   \\|__|              \\|__|");
    
  }
  
  public void DisplayGuides() {
    
    System.out.println(this.playerGuide());
    
  }
  
}