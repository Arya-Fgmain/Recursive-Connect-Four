public class Slot {
  
  public final static int VAL_RED = 0;
  public final static int VAL_BLK = 1;
  
  private int val;
  
  public Slot(int val) {
    this.val = val;
  }
  
  public int GetVal() {
    return this.val;
  }
  
}