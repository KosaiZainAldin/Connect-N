/**
* Represents the game board and provides board-related utilities.
* @author Kosai Zain Aldin
*/

public class Board {

    private int rows;
    private int cols;
    private int connectN;
    private int[][] boardState; // 0=empty, 1=Player1(Red), 2=Player2(Yellow)


    public Board(int rows, int cols, int connectN) throws IllegalArgumentException {
        if (connectN > rows || connectN > cols || connectN <= 2 ){
            throw new IllegalArgumentException("Invalid input");
        }
    this.connectN = connectN;
    this.rows = rows;
    this.cols = cols;
    this.boardState = new int[rows][cols];

}


public Board(int rows, int cols) {
    this(rows, cols, 4);
}


public Board() {
    this(6, 7, 4);
}


public void setRows(int rows) {
    if (rows < connectN){
        throw new IllegalArgumentException("Invalid input");
    }
this.rows = rows;
}


public void setCols(int cols) {
    if (cols < connectN){
        throw new IllegalArgumentException("Invalid input");
    }
this.cols = cols;
}


public void setConnectN(int connectN) {
    if (boardState == null){
        if (connectN <= 2){
            throw new IllegalArgumentException("Invalid input");
        }

}
else{
    if (connectN > rows || connectN > cols || connectN <= 2 ){
        throw new IllegalArgumentException("Invalid input");
    }}
this.connectN = connectN;
}


public static boolean isValidMove(int[][] boardState, int col) {

    int cols = boardState[0].length;
    if (col >= cols || col < 0){
        return false;
    }
if (boardState[0][col] != 0 ){
    return false;
}
return true;
}


public static int[][] copyAGivenBoardState(int[][] original) {
    int rows = original.length;
    int cols = original[0].length;
    int[][] copy = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            copy[i][j] = original[i][j];
        }
}
return copy;
}


public static int dropPieceSimulated(int[][] b, int col, int piece) {
    if (isValidMove(b, col)){
        for (int i = b.length - 1; i >= 0; i--){
            if (b[i][col] == 0){
                b[i][col] = piece;
                return i;
            }
    }
}

return -1;

}


public static boolean isBoardFullForBoard(int[][] b) {
    for (int i = 0; i < b.length; i++){
        for(int j = 0; j< b[0].length; j++){
            if(b[i][j] == 0){
                return false;
            }
    }
}
return true;
}

// Professor-provided getArrayDeepCopy implementation removed.


// Professor-provided getRows implementation removed.


// Professor-provided getCols implementation removed.


// Professor-provided getArray implementation removed.


// Professor-provided isBoardFull implementation removed.


}
