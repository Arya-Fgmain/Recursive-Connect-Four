public class Main {
  
  public static void main(String[] args) {
    
    Sys sys = new Sys();
    Displayer disp = new Displayer();
    Slot[][] grid = sys.GetGrid();
    //disp.DisplayGrid(grid);
    //System.out.println( sys.rowChecker(grid, 4, 0) );
    //System.out.println( sys.colChecker(grid, 0, 0) );
    //System.out.println( sys.CheckRow(grid, 0) );
    //System.out.println( sys.CheckCol(grid, 0) );
    //System.out.println( sys.leftDigCheck(grid, 5, 6) );
    //System.out.println( sys.rightDigCheck(grid, 5, 0) );
    //System.out.println( sys.CheckRightDig(grid, 0, 0) );
    //System.out.println( sys.CheckLeftDig(grid, 0, 6) );
    System.out.println( sys.insertSlot(0, 6, new Slot(0)) );
    disp.DisplayGrid(grid);
  }
  
}