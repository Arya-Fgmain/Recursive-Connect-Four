/* Developed By: Mohammadarya Faghihy
 * Date: Jan 9, 2022
 * Version     : 2.2 */

import java.util.Scanner;

public class Main {
  
  public static void main(String[] args) {
    
    // TODO: (1) Add final static variables where possible
    // MAYBE add slot value checker & dimension checker & InsertSlot() ONLY NEEDS COLUMN
    Scanner input = new Scanner(System.in);
    Sys sys = new Sys();
    Displayer disp = new Displayer();
    Slot[][] grid = sys.GetGrid();
    int userOption;
    Player currP = sys.GetCurrPlayer();
      
    int direction;
    int currPId = -1; // dummy value
    int move = 1; // dummy value
    disp.DisplayHeader();
    System.out.println("\nHello user, what would you like to do today?\n1. Play\n2. Exit");
    userOption = input.nextInt();
    while (userOption < 1 || userOption > 2) { // Error prevention
      System.out.println("Invalid option! Choose again: ");
      userOption = input.nextInt();
    }
    
    if (userOption == 1) {
      System.out.println("Alright!\n");
      disp.DisplayGuides();
      while ( true ) {
        disp.DisplayGrid(grid);
        currP = sys.GetCurrPlayer();
        boolean isWin = sys.CheckWinner(); // Checking if a win has occured
        if (isWin) {
          System.out.println("PLAYER " + currPId + " HAS WON!");
          break;
        }
        sys.SwitchPlayer();
        currPId = currP.GetId();
        System.out.println("PLAYER " + currPId + "'s turn."); // Stating which player's turn it is
        while (move != Sys.MOVE_MADE) { // Loop to ensure a move is made
          if (move == Sys.INVALID_DIRECTION) {
            System.out.println("Invalid directions! Let's try again.");
          } else if (move == Sys.SLOT_FULL) {
            System.out.println("This slot is filled! Let's try again.");
          }
          System.out.print("COL #: ");
          direction = input.nextInt() - 1;
          move = sys.InsertSlot(0, direction, new Slot(currPId)); 
        }
        move = 1; // resetting dummy value
      }
    } else {
      System.out.println("Sayonara!");
    } 
  }
  
}
