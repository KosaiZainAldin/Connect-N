/**
* Represents the game board and provides board-related utilities.
* @author Archange DESTINE
* @author Kosai Zain Aldin
*/

public class Board {

    private int rows;
    private int cols;
    private int connectN;
    private int[][] boardState; // 0=empty, 1=Player1(Red), 2=Player2(Yellow)

    // TO-DO
    // Validation rules:
    // - connectN must be > 2
    // - connectN cannot be greater than row or column.
    // if the arguments rows, cols and connectN are not valid, throw IllegalArgumentException
    // Note: Initialize the field connectN first, then initialize the other fields.
    public Board(int rows, int cols, int connectN) throws IllegalArgumentException {
        if (connectN > rows || connectN > cols || connectN <= 2 ){
            throw new IllegalArgumentException("Invalid input");
        }
    this.connectN = connectN;
    this.rows = rows;
    this.cols = cols;
    this.boardState = new int[rows][cols];

}

// TO-DO
// - This constructor will use 4 as default value for connectN.
public Board(int rows, int cols) {
    this(rows, cols, 4);
}

// TO-DO
// This constructor will use those defaults values:
// - rows: 6
// - cols: 7
// - connectN: 4
public Board() {
    this(6, 7, 4);
}

// TO-DO
//- if rows is less than connectN, throw IllegalArgumentException
public void setRows(int rows) {
    if (rows < connectN){
        throw new IllegalArgumentException("Invalid input");
    }
this.rows = rows;
}

// TO-DO
// - if cols is less than connectN, throw IllegalArgumentException
public void setCols(int cols) {
    if (cols < connectN){
        throw new IllegalArgumentException("Invalid input");
    }
this.cols = cols;
}

// TO-DO
// ConnectN value must be at least 3
// And If boardState have already been initialized (is not null), connectN should be greater or equals to rows AND greater or equals to cols.
// Otherwise throws IllegalArgumentException
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

//TO-DO
// isValidMove() checks if a move is valid for a given board state (not necessarily the current one)
// col is the index of the column where the user want to drop the disk.
// a move is valid if col is a valid index considering the dimension of the boardState (provided as argument)
// you would also consider the move invalid if the user want to drop a disk in a full column.
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

//TO-DO
//return a deep copy of any given board state (not necessarily the current one)
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

// TO-DO
// dropPieceSimulated simulates dropping a disk (piece) in a given board state (not necessarily the current one)
// return the row index the disk ends up being.
// return -1 if it was not possible to drop the disk in the specified column.
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

// TO-DO
// isBoardFullForBoard checks if the board is full for a given board state (not necessarily the current one)
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

public int[][] getArrayDeepCopy() {
    return copyAGivenBoardState(this.boardState);
}

public int getRows() {
    return this.rows;
}

public int getCols() {
    return this.cols;
}

public int[][] getArray() {
    return this.boardState;
}

public boolean isBoardFull() {
    return isBoardFullForBoard(this.boardState);
}

}
