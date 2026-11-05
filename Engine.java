/**
 * The game engine that manages turns, validates moves, and implements the AI logic.
 * @author Archange DESTINE
 * @author YOUR_NAME_HERE
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

    //TO-DO
    // set the fields
    // 1 player must be Human Player
    // 1 player must be Computer Player
    // player1, player2 and difficultyLevel must not be null
    // currentPlayer, indicating the player who will play next, is set to player1.
    // For invalid argument, throw IllegalArgumentException
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

    //TO-DO
    // This constructor uses DifficultyLevel.MEDIUM as DifficultyLevel
    public Engine(Player player1, Player player2, int rows, int cols, int connectN) {
        this(player1, player2, rows, cols, connectN, DifficultyLevel.MEDIUM);
    }

    public Engine(Player player1, Player player2, int rows, int cols) {
        this(player1, player2, rows, cols, 4);
    }

    public Engine(Player player1, Player player2) {
        this(player1, player2, 6, 7, 4);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrentPlayerPieceColor() {
        return (currentPlayer == player1) ? 1 : 2;
    }

    public int getHumanPlayerPieceColor() {
        return (player1 instanceof HumanPlayer) ? 1 : 2;
    }

    public int getComputerPlayerPieceColor() {
        return (player1 instanceof HumanPlayer) ? 2 : 1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void endCurrentPlayerTurn() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    // TO-DO
    // Drop piece and return the row where it landed, or -1 if invalid
    public int dropPiece(int col, int piece) {
        int[][] b = board.getArray();
        return Board.dropPieceSimulated(b, col, piece);
    }

    private boolean isTerminal(int[][] b) {
        return checkWinForBoardState(b, player1) || checkWinForBoardState(b, player2) || Board.isBoardFullForBoard(b);
    }

    public Board getBoard() {
        return board;
    }

    // TO-DO
    // Check if the given player won the game
    // Win can be acheived differen ways:
    // - Honrizontal
    // - Vertical
    // - Diogonal / or \
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

    public boolean checkWin(Player player) {
        return checkWinForBoardState(board.getArray(), player);
    }

    //TO-DO
    // - Return the best move (col) and an evaluation score for that "best" move.
    // - Use recursion and implement a minimax algorithm.
    // - The first part (simulating AI next move) is provided. A good evaluation of that move needs to anticipate the player's next move. An evaluation of the oppoment next move neeed to take into account the Computer next move, and so one.
    // - Such process will happen for all possible moves.
    // - Since we cannot comptute too many steps ahead (risking stack over flow, one need to stop at some point (based on depth param)).
    // - If after a certain level, we do not have a terminal state (win, lost, draw), the Computer will rely on a heuristic evaluation of the position.
    // - This heuristic evaluation is already implemented (see evaluateBoardState(), scoreWindows() and evaluateLine()).
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


    //TO-DO: Optional BONUS (+5 points)
    //Optional: Edit this method so it can use an efficient version of mini-max algorithm
    // It will eliminated branches that cannot affect the final decision at some point. (read more about alpha-bete pruning for minimax algorithm.)
    // if you have no idea of how to implement this, you chan seek for help or just ignore this Bonus component.
    public int[] findBestMove(int[][] boardState, int depth, int alpha, int beta, boolean maximizing) {
        movesAnalized += 1;

        int AI = (player1 instanceof ComputerPlayer) ? 1 : 2;
        int PLAYER = (player1 instanceof ComputerPlayer) ? 2 : 1;

        if (depth == 0 || isTerminal(boardState)) {
            return new int[] { -1, evaluateBoardState(boardState) };
        }

        int bestCol = -1;

        if (maximizing) { // AI's turn
            int maxEval = Integer.MIN_VALUE;
            for (int col = 0; col < board.getCols(); col++) {
                if (Board.isValidMove(boardState, col)) {
                    int[][] tempBoard = Board.copyAGivenBoardState(boardState);
                    Board.dropPieceSimulated(tempBoard, col, AI);
                    int eval = findBestMove(tempBoard, depth - 1, alpha, beta, false)[1];

                    if (eval > maxEval) {
                        maxEval = eval;
                        bestCol = col;
                    }

                }
            }
            return new int[] { bestCol, maxEval };

        } else { // Player's turn
            int minEval = Integer.MAX_VALUE;
            for (int col = 0; col < board.getCols(); col++) {
                if (Board.isValidMove(boardState, col)) {
                    int[][] tempBoard = Board.copyAGivenBoardState(boardState);
                    Board.dropPieceSimulated(tempBoard, col, PLAYER);
                    int eval = findBestMove(tempBoard, depth - 1, alpha, beta, true)[1];

                    if (eval < minEval) {
                        minEval = eval;
                        bestCol = col;
                    }

                }
            }
            return new int[] { bestCol, minEval };
        }
    }

    // heuristic evaluation of a given board state from the perspective of the
    // specified piece color.
    private int scoreWindows(int[][] b, int piece) {

        int score = 0;
        int opp = (piece == 1) ? 2 : 1;

        // Horizontal, Vertical, Diagonals scoring
        // Horizontal
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols() - connectN + 1; c++) {
                int[] line = new int[connectN];
                for (int i = 0; i < connectN; i++) {
                    line[i] = b[r][c + i];
                }
                score += evaluateLine(line, piece, opp);
            }
        }
        // Vertical
        for (int c = 0; c < board.getCols(); c++) {
            for (int r = 0; r < board.getRows() - connectN + 1; r++) {
                int[] line = new int[connectN];
                for (int i = 0; i < connectN; i++) {
                    line[i] = b[r + i][c];
                }
                score += evaluateLine(line, piece, opp);
            }
        }
        // Diagonal /
        for (int r = 0; r < board.getRows() - connectN + 1; r++) {
            for (int c = 0; c < board.getCols() - connectN + 1; c++) {
                int[] line = new int[connectN];
                for (int i = 0; i < connectN; i++) {
                    line[i] = b[r + i][c + i];
                }
                score += evaluateLine(line, piece, opp);
            }
        }
        // Diagonal \
        for (int r = 0; r < board.getRows() - connectN + 1; r++) {
            for (int c = connectN - 1; c < board.getCols(); c++) {
                int[] line = new int[connectN];
                for (int i = 0; i < connectN; i++) {
                    line[i] = b[r + i][c - i];
                }
                score += evaluateLine(line, piece, opp);
            }
        }
        return score;
    }

    // Local herustic evaluation of a n-cell line for the specified piece color.
    // n is typically the connectN.
    // It returns a positive score if favorable for the piece, negative if favorable
    // for opponent.
    private long evaluateLine(int[] line, int piece, int opp) {
        long score = 0;
        int countPiece = 0;
        int countOpp = 0;
        int countEmpty = 0;
        for (int cell : line) {
            if (cell == piece)
                countPiece++;
            else if (cell == opp)
                countOpp++;
            else
                countEmpty++;
        }

        int diff = countPiece - countOpp;
        if (countOpp == 0)
            score += Math.pow(10, countPiece - countEmpty);

        else {
            if (countPiece > countOpp)
                score += Math.pow(10, diff);
            else if (countOpp > countPiece)
                score -= Math.pow(10, -diff);
        }

        return score;
    }

    private int evaluateBoardState(int[][] b) {
        // Simple heuristic: +score for Computer pieces, -score for Human Player pieces
        // More advanced heuristics can consider potential winning lines, blocking
        // opponent, etc.
        int computerPiece = player1 instanceof ComputerPlayer ? 1 : 2;
        int humanPiece = player1 instanceof ComputerPlayer ? 2 : 1;
        int score = 0;

        // Center preference: Generally better to control the center column
        for (int row = 0; row < board.getRows(); row++) {
            if (b[row][board.getCols() / 2] == computerPiece)
                score += 4;
        }

        // Score horizontal, vertical, and diagonal lines (just a simple count of pieces
        // in a row for now)
        score += scoreWindows(b, computerPiece) - scoreWindows(b, humanPiece);
        return score;
    }

}
