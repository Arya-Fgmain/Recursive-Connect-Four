/* Developed By: Mohammadarya Faghihy
 * Date: Jan 9, 2022
 * Version     : 2.3 */
public class Player {
  
  public final static int ID_PLAYER0 = 0;
  public final static int ID_PLAYER1 = 1;
  
  private int id;
  
  public Player(int id) {
    this.id = id;
  }
  
  public int GetId() {
    return this.id;
  }
  
}