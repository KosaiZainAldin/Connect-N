public class tester {
    int connectN = 4;

    // Returns true if the given player has a horizontal, vertical, or diagonal
    // line of connectN pieces on the board.
    private boolean checkWinForBoardState(int[][] b, Player player) {
        int piece = (player instanceof HumanPlayer) ? 1 : 2;
        int picinline = 0;

        // Horizontal
        for (int r = 0; r < b.length; r++) {
            picinline = 0;
            for (int c = 0; c < b[r].length; c++) {
                if (b[r][c] == piece) {
                    picinline++;
                } else {
                    picinline = 0;
                }
                if (picinline == this.connectN) {
                    return true;
                }
            }
        }

        picinline = 0;

        // Vertical
        for (int c = 0; c < b[0].length; c++) {
            picinline = 0;
            for (int r = 0; r < b.length; r++) {
                if (b[r][c] == piece) {
                    picinline++;
                } else {
                    picinline = 0;
                }
                if (picinline == this.connectN) {
                    return true;
                }
            }
        }

        picinline = 0;

        // Diagonal down right
        for (int r = 0; r < b.length; r++) {
            for (int c = 0; c < b[r].length; c++) {
                picinline = 0;
                int i = r;
                int j = c;

                while (i < b.length && j < b[0].length) {
                    if (b[i][j] == piece) {
                        picinline++;
                        if (picinline == connectN) {
                            return true;
                        }
                    } else {
                        picinline = 0;
                    }
                    i++;
                    j++;
                }
            }
        }

        picinline = 0;

        // Diagonal down left
        for (int r = 0; r < b.length; r++) {
            for (int c = b[r].length - 1; c >= 0; c--) {
                picinline = 0;
                int i = r;
                int j = c;

                while (i < b.length && j >= 0) {
                    if (b[i][j] == piece) {
                        picinline++;
                        if (picinline == connectN) {
                            return true;
                        }
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

    public static void main(String[] args) {
        int[][] a = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
        };

        tester b = new tester();
        Player p = new HumanPlayer("abo");
        System.out.print(b.checkWinForBoardState(a, p));
    }
}
