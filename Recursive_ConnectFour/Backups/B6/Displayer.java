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
    System.out.println(this.gridGuide());
    
  }
  
  private String playerGuide() {
    
   return "**** PLAYERS ****\nPLAYER 0:     RED\nPLAYER 0:   BLACK\n*****************";
    
  }
  
  private String gridGuide() {
    String part1 = "\n************** THE GRID **************\nROW # 0: |   |   |   |   |   |   |   |\nROW # 1: |   |   |   |   |   |   |   |\nROW # 2: |   |   |   |   |   |   |   |";
    String part2 = "\nROW # 3: |   |   |   |   |   |   |   |\nROW # 4: |   |   |   |   |   |   |   |\nROW # 5: |   |   |   |   |   |   |   |";
    String part3 = "\nCOLUMNS:   0   1   2   3   4   5   6  \n**************************************\n";
    
    return part1 + part2 + part3;
    
  }
  
}