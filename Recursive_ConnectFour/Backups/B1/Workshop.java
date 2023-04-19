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
      {1, 2, 1, 1, 2, 1, 3},
      {3, 1, 1, 2, 2, 3, 5},
      {2, 3, 1, 1, 1, 7, 1},
      {1, 2, 3, 3, 9, 0, 1},
      {1, 2, 2, 3, 1, 2, 2},
      {1, 1, 3, 1, 2, 1, 0}
    };
    Workshop w = new Workshop();
    System.out.println( w.RightRowEqualizer(arr, 5, 3) );
    //System.out.println(w.RightDigCheck(arr, 0, 0));
    //System.out.println(w.LeftDigCheck(arr, 0, 6));
    
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
  
  public boolean RightRowEqualizer(int[][] arr, int startR, int startC) { // Row Checker
    if (startR - 3 < 0 || startC + 3 >= arr[0].length) {
      return false;
    }
    if (arr[startR][startC] == arr[startR - 1][startC+1] && arr[startR - 1][startC + 1] == arr[startR - 2][startC + 2] && arr[startR - 2][startC + 2] == arr[startR - 3][startC + 3]) {
      return true;
    } else {
      return RightRowEqualizer(arr, startR - 1, startC + 1);
    }    
  }
  
  /**public boolean DiagonalCheck(int[][] arr, int startR, int startC) { // Upright first half
    * 
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
    return false;
    }
    
    if ( startR - 3 < 0 || startC + 3 >= arr[0].length ) {
    return DiagonalCheck(arr, startR + 1, startC);
    } else {
    if (arr[startR][startC] == arr[startR - 1][startC + 1] && arr[startR - 1][startC + 1] == arr[startR - 2][startC + 2] && arr[startR - 2][startC + 2] == arr[startR - 3][startC + 3]) {
    return true;
    } else {
    return DiagonalCheck(arr, startR + 1, startC);
    }
    }
    
    }*/
  
  public boolean RightDigCheck(int[][] arr, int startR, int startC) { // Upright second half ***
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC + 3 >= arr[0].length ) {
      if (startR == arr.length - 1) {
        return RightDigCheck(arr, startR, startC+1);
      } else {
        return RightDigCheck(arr, startR + 1, startC);
      }
    } else {
      if (arr[startR][startC] == arr[startR - 1][startC + 1] && arr[startR - 1][startC + 1] == arr[startR - 2][startC + 2] && arr[startR - 2][startC + 2] == arr[startR - 3][startC + 3]) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return RightDigCheck(arr, startR, startC+1);
        } else {
          return RightDigCheck(arr, startR + 1, startC);
        }
      }
    }
    
  }
  
  public boolean LeftDigCheck(int[][] arr, int startR, int startC) { // Upright second half
    
    if ( (startR >= arr.length || startR < 0) || ( (startC >= arr[0].length || startC < 0) ) ) {
      return false;
    }
    
    if ( startR - 3 < 0 || startC - 3 < 0 ) {
      if (startR == arr.length - 1) {
        return LeftDigCheck(arr, startR, startC-1);
      } else {
        return LeftDigCheck(arr, startR + 1, startC);
      }
    } else {
      if (arr[startR][startC] == arr[startR - 1][startC - 1] && arr[startR - 1][startC - 1] == arr[startR - 2][startC - 2] && arr[startR - 2][startC - 2] == arr[startR - 3][startC - 3]) {
        return true;
      } else {
        if (startR == arr.length - 1) {
          return LeftDigCheck(arr, startR, startC-1);
        } else {
          return LeftDigCheck(arr, startR + 1, startC);
        }
      }
    }
    
  }
  
}