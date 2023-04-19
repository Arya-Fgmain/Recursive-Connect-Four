import java.util.Scanner;

public class Main {
  
  public static void main(String[] args) {
    
    /*
     * TODO: (1) Add ownership comments, (2) Check player switch issue, (3) Remove grid from Sys methods, refine all
     * */
    Scanner input = new Scanner(System.in);
    Sys sys = new Sys();
    Displayer disp = new Displayer();
    Slot[][] grid = sys.GetGrid();
    int userOption;
    Player currP = sys.GetCurrPlayer();
    int[] directions = new int[2];
    int currPId = -1; // dummy value
    disp.DisplayHeader();
    System.out.println("\nHello user, what would you like to do today?\n1. Play\n2. Exit");
    userOption = input.nextInt();
    while (userOption < 1 || userOption > 2) {
      System.out.println("Invalid option! Choose again: ");
      userOption = input.nextInt();
    }
    
    if (userOption == 1) {
      System.out.println("Alright!\n");
      disp.DisplayGuides();
      while ( true ) {
        disp.DisplayGrid(grid);
        currP = sys.GetCurrPlayer();
        boolean isWin = sys.CheckWinner();
        if (isWin) {
          System.out.println("PLAYER " + currPId + " HAS WON!");
          break;
        }
        sys.SwitchPlayer();
        currPId = currP.GetId();
        System.out.println("PLAYER " + currPId + "'s turn.");
        while (true) {
          System.out.println("ROW #: ");
          directions[0] = input.nextInt();
          System.out.println("COL #: ");
          directions[1] = input.nextInt();
          if ((directions[0] < 0 || directions[0] > 5) || (directions[1] < 0 || directions[1] > 6)) {
            System.out.println("Invalid directions! Let's try again.");
          } else {
            break; 
          }
        }
        int move = sys.insertSlot(directions[0], directions[1], new Slot(currPId)); 
        while (move == -2) {
          System.out.println("This slot is filled! Let's try again.");
          System.out.println("ROW #: ");
          directions[0] = input.nextInt();
          System.out.println("COL #: ");
          directions[1] = input.nextInt();
          move = sys.insertSlot(directions[0], directions[1], new Slot(currPId)); 
        }
      }
    } else {
      System.out.println("Sayonara!");
    }
    
  }
  
}