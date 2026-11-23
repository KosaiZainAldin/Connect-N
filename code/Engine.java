/**
* The game engine that manages turns, validates moves, and implements the AI logic.
* @author Kosai Zain Aldin
*/

public class Engine {

    private Player player1;
    private Player player2;
    private Board board;
    private boolean gameOver;
    private int connectN;
    private Player currentPlayer;
    private DifficultyLevel difficultyLevel;
    private long movesAnalized;


    public Engine(Player player1, Player player2, int rows, int cols, int connectN, DifficultyLevel difficultyLevel) {
        if (player1 == null || player2 == null || difficultyLevel == null) {
            throw new IllegalArgumentException("invalid input");
        }

    if (player1.getClass() == player2.getClass()){
        throw new IllegalArgumentException("invalid input");
    }

if (!(player1.getClass() == HumanPlayer.class || player2.getClass() == HumanPlayer.class)){
    throw new IllegalArgumentException("invalid input");
}
if (!(player1.getClass() == ComputerPlayer.class  || player2.getClass() == ComputerPlayer.class)){
    throw new IllegalArgumentException("invalid input");
}
this.player1 = player1;
this.player2 = player2;
this.currentPlayer = player1;
this.difficultyLevel = difficultyLevel;
this.connectN = connectN;
this.board = new Board(rows, cols, connectN);
}


public Engine(Player player1, Player player2, int rows, int cols, int connectN) {
    this(player1, player2, rows, cols, connectN, DifficultyLevel.MEDIUM);
}

public Engine(Player player1, Player player2, int rows, int cols) {
    this(player1, player2, rows, cols, 4);
}

public Engine(Player player1, Player player2) {
    this(player1, player2, 6, 7, 4);
}

// Professor-provided getCurrentPlayer implementation removed.


// Professor-provided getCurrentPlayerPieceColor implementation removed.


// Professor-provided getHumanPlayerPieceColor implementation removed.


// Professor-provided getComputerPlayerPieceColor implementation removed.


// Professor-provided isGameOver implementation removed.


// Professor-provided setGameOver implementation removed.


// Professor-provided endCurrentPlayerTurn implementation removed.



public int dropPiece(int col, int piece) {
    int[][] b = board.getArray();
    return Board.dropPieceSimulated(b, col, piece);
}

// Professor-provided isTerminal implementation removed.


// Professor-provided getBoard implementation removed.



private boolean checkWinForBoardState(int[][] b, Player player) {
    int piece = (player instanceof HumanPlayer) ? 1 : 2;
    int picinline = 0;

    //Honrizontal
    for (int r =0; r < b.length; r++){
        picinline = 0;
        for (int c =0; c< b[r].length; c++){
            if (b[r][c] == piece){
                picinline++;
            }
        else{
            picinline = 0;
        }
    if(picinline == this.connectN) return true;
}
}
picinline = 0;

//Vertical
for (int c =0; c < b[0].length; c++){
    picinline = 0;
    for (int r =0; r< b.length; r++){
        if (b[r][c] == piece){
            picinline++;
        }
    else{
        picinline = 0;
    }
if(picinline == this.connectN) return true;
}
}
picinline = 0;

//Diogonal down right
for (int r =0; r < b.length; r++){
    for (int c =0; c< b[r].length; c++){
        picinline = 0;
        int i = r;
        int j = c;

        while (i < b.length && j < b[0].length) {
            if (b[i][j] == piece) {
                picinline++;
                if (picinline == connectN) return true;
            } else {
            picinline = 0;
        }
    i++;
    j++;
}
}

}
picinline = 0;

//Diogonal down left
for (int r =0; r < b.length; r++){
    for (int c = b[r].length - 1; c >= 0; c--){
        picinline = 0;
        int i = r;
        int j = c;

        while (i < b.length && j >= 0) {
            if (b[i][j] == piece) {
                picinline++;
                if (picinline == connectN) return true;
            } else {
            picinline = 0;
        }
    i++;
    j--;
}
}
}
picinline = 0;

return false;
}

// Logic for the Computer Player move.
public void aiMove() {

    int AIPiece = (player1 instanceof ComputerPlayer) ? 1 : 2;
    Player humanPlayer = (player1 instanceof ComputerPlayer) ? player2 : player1;
    Player aiPlayer = (player1 instanceof ComputerPlayer) ? player1 : player2;

    if (gameOver)
    return;

    // Recursive Minimax (depth 5 is decent, 6+ is stronger but slower)
    int depth = switch (difficultyLevel) {
        //EASY, MEDIUM, HARD, IMPOSSIBLE
        case EASY -> 2;
        case MEDIUM -> 3;
        case HARD -> 4;
        case IMPOSSIBLE -> 5;
    };

movesAnalized = 0;
System.out.println("Move analized reset : " + movesAnalized);
int bestCol = findBestMove(board.getArray(), depth, true)[0];
System.out.println("Move analized : " + movesAnalized);

if (bestCol != -1) {
    dropPiece(bestCol, AIPiece);

    if (checkWin(aiPlayer)) {
        gameOver = true;
    } else if (board.isBoardFull()) {
    gameOver = true;
} else {
    currentPlayer = humanPlayer;
}
}

}

// Professor-provided checkWin implementation removed.



public int[] findBestMove(int[][] boardState, int depth, boolean maximizing) {
    movesAnalized += 1;

    int AI = (player1 instanceof ComputerPlayer) ? 1 : 2;
    int PLAYER = (player1 instanceof ComputerPlayer) ? 2 : 1;

    if (depth == 0 || isTerminal(boardState)) {
        return new int[]{-1, evaluateBoardState(boardState)};
    }

int bestCol = -1;

if (maximizing) { // AI's turn
    int maxEval = Integer.MIN_VALUE;
    for (int col = 0; col < board.getCols(); col++) {
        if (Board.isValidMove(boardState, col)) {
            int[][] tempBoard = Board.copyAGivenBoardState(boardState);
            Board.dropPieceSimulated(tempBoard, col, AI);
            int eval = findBestMove(tempBoard, depth - 1, false)[1];

            if (eval > maxEval) {
                maxEval = eval;
                bestCol = col;
            }

    }
}
return new int[]{bestCol, maxEval};

} else { // Player's turn
    int minEval = Integer.MAX_VALUE;
    for (int col = 0; col < board.getCols(); col++) {
        if (Board.isValidMove(boardState, col)) {

            int[][] tempBoard = Board.copyAGivenBoardState(boardState);
            Board.dropPieceSimulated(tempBoard, col, PLAYER);
            int eval = findBestMove(tempBoard, depth - 1, true)[1];

            if (eval < minEval) {
                minEval = eval;
                bestCol = col;

            }
    }
}
return new int[]{bestCol, minEval};
}
}




// heuristic evaluation of a given board state from the perspective of the
// specified piece color.
// Professor-provided scoreWindows implementation removed.


// Local herustic evaluation of a n-cell line for the specified piece color.
// n is typically the connectN.
// It returns a positive score if favorable for the piece, negative if favorable
// for opponent.
// Professor-provided evaluateLine implementation removed.


// Professor-provided evaluateBoardState implementation removed.


}
