/* Developed By: Mohammadarya Faghihy
 * Date: Jan 9, 2022
 * Version     : 1.8 */
public class Workshop {
  
  public static void main(String[] args) {
    
    /*int[] arr = {0, 9, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1};
     int count = 0;
     for (int i = 0; i < arr.length; i++) {
     if (i + 3 >= arr.length) {
     break;
     } else {
     //System.out.println( arr[0] == arr[1] && arr[1] == arr[2] && arr[2] == arr[3] );
     count++;
     }
     }
     
     System.out.println(count);
     
     Workshop w = new Workshop();
     System.out.println( w.Checker(arr, 0) );*/
    
    int[][] arr = {
      {1, 9, 1, 9, 1, 9, 8},
      {3, 1, 9, 2, 2, 3, 2},
      {2, 2, 1, 9, 3, 2, 8},
      {1, 2, 3, 2, 9, 0, 8},
      {1, 1, 3, 1, 2, 2, 2},
      {1, 1, 3, 0, 2, 2, 0}
    };
    Workshop w = new Workshop();
    //System.out.println( w.RightrowChecker(arr, 5, 3) );
    //System.out.println( w.LeftrowChecker(arr, 4, 6) );
    //System.out.println(w.CheckRightDig(arr, 0, 0));
    //System.out.println(w.CheckLeftDig(arr, 0, 6));
    //System.out.println(w.rowChecker(arr, 0, 0));
    //System.out.println(w.CheckRow(arr,0));
    //System.out.println(w.colChecker(arr, 0, 5));
    //System.out.println(w.CheckCol(arr, 0));
  }
  
  public boolean Checker(int[] arr, int start) { // Row Checker
    if (start + 3 >= arr.length) {
      return false;
    }
    if (arr[start] == arr[start + 1] && arr[start + 1] == arr[start + 2] && arr[start + 2] == arr[start + 3]) {
      return true;
    } else {
      return Checker(arr, start + 1);
    }    
  }
  
  private boolean rightDigCheck(int[][] arr, int startR, int startC) { // Row Checker
    if (startR - 3 < 0 || startC + 3 >= arr[0].length) {
      return false;
    }
    if (arr[startR][startC] == arr[startR - 1][startC+1] && arr[startR - 1][startC + 1] == arr[startR - 2][startC + 2] && arr[startR - 2][startC + 2] == arr[startR - 3][startC + 3]) {
      return true;
    } else {
      return this.rightDigCheck(arr, startR - 1, startC + 1);
    }    
  }
  
  private boolean leftDigCheck(int[][] arr, int startR, int startC) { // Row Checker
    
    if (startR - 3 < 0 || startC - 3 < 0) {
      return false;
    }
    if (arr[startR][startC] == arr[startR - 1][startC-1] && arr[startR - 1][startC - 1] == arr[startR - 2][startC - 2] && arr[startR - 2][startC - 2] == arr[startR - 3][startC - 3]) {
      return true;
    } else {
      return this.leftDigCheck(arr, startR - 1, startC - 1);
    }    
  }
  
  public boolean rowChecker(int[][] arr, int R, int startC) {
    
    if (startC + 3 >= arr[R].length) {
      return false;
    }
    
    if (arr[R][startC] == arr[R][startC + 1] && arr[R][startC + 1] == arr[R][startC + 2] && arr[R][startC + 2] == arr[R][startC + 3]) {
      return true;
    } else {
      return rowChecker(arr, R, startC + 1);
    }
    
  }
  
  public boolean colChecker(int[][] arr, int startR, int C) {
    
    if (startR + 3 >= arr.length) {
      return false;
    }
    
    if (arr[startR][C] == arr[startR + 1][C] && arr[startR + 1][C] == arr[startR + 2][C] && arr[startR + 2][C] == arr[startR + 3][C]) {
      return true;
    } else {
      return colChecker(arr, startR + 1, C);
    }
    
  }
  
  public boolean CheckRow(int[][] arr, int startR) {
    System.out.println("Row: " + startR);
    if (startR >= arr.length || startR < 0) {
      return false;
    }
    
    if (this.rowChecker(arr, startR, 0)) {
      return true;
    } else {
      return CheckRow(arr, startR + 1);
    }
    
  }
  
  
  public boolean CheckCol(int[][] arr, int startC) {
    System.out.println("Col: " + startC);
    if (startC >= arr[0].length || startC < 0) {
      return false;
    }
    
    if (this.colChecker(arr, 0, startC)) {
      return true;
    } else {
      return CheckCol(arr, startC + 1);
    }
    
  }
  
  public boolean CheckRightDig(int[][] arr, int startR, int startC) { // Upright second half ***
    
    System.out.println("Dimensions: " + startR + " - " + startC);
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC + 3 >= arr[0].length ) {
      if (startR == arr.length - 1) {
        return CheckRightDig(arr, startR, startC+1);
      } else {
        return CheckRightDig(arr, startR + 1, startC);
      }
    } else {
      if (this.rightDigCheck(arr, startR, startC)) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return CheckRightDig(arr, startR, startC+1);
        } else {
          return CheckRightDig(arr, startR + 1, startC);
        }
      }
    }
    
  }
  
  public boolean CheckLeftDig(int[][] arr, int startR, int startC) { // Upright second half
    
    System.out.println("Dimensions: " + startR + " - " + startC);
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC - 3 < 0 ) {
      if (startR == arr.length - 1) {
        return CheckLeftDig(arr, startR, startC-1);
      } else {
        return CheckLeftDig(arr, startR + 1, startC);
      }
    } else {
      if (this.leftDigCheck(arr, startR, startC)) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return CheckLeftDig(arr, startR, startC-1);
        } else {
          return CheckLeftDig(arr, startR + 1, startC);
        }
      }
    }
    
  }
  
  
  /*public boolean CheckRightDig(int[][] arr, int startR, int startC) { // Upright second half ***
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC + 3 >= arr[0].length ) {
      if (startR == arr.length - 1) {
        return CheckRightDig(arr, startR, startC+1);
      } else {
        return CheckRightDig(arr, startR + 1, startC);
      }
    } else {
      if (arr[startR][startC] == arr[startR - 1][startC + 1] && arr[startR - 1][startC + 1] == arr[startR - 2][startC + 2] && arr[startR - 2][startC + 2] == arr[startR - 3][startC + 3]) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return CheckRightDig(arr, startR, startC+1);
        } else {
          return CheckRightDig(arr, startR + 1, startC);
        }
      }
    }
    
  }
  
  public boolean CheckLeftDig(int[][] arr, int startR, int startC) { // Upright second half
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC - 3 < 0 ) {
      if (startR == arr.length - 1) {
        return CheckLeftDig(arr, startR, startC-1);
      } else {
        return CheckLeftDig(arr, startR + 1, startC);
      }
    } else {
      if (arr[startR][startC] == arr[startR - 1][startC - 1] && arr[startR - 1][startC - 1] == arr[startR - 2][startC - 2] && arr[startR - 2][startC - 2] == arr[startR - 3][startC - 3]) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return CheckLeftDig(arr, startR, startC-1);
        } else {
          return CheckLeftDig(arr, startR + 1, startC);
        }
      }
    }
    
  }*/
  
}