public class Main {
  
  public static void main(String[] args) {
    
    Sys sys = new Sys();
    Displayer disp = new Displayer();
    Slot[][] grid = sys.GetGrid();
    disp.DisplayGrid(grid);
  }
  
}