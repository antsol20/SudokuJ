
public class SudokuJ {
    private final String boardTxt;
    private final int[][] board = new int[9][9];

    public SudokuJ(String boardTxt) {
        this.boardTxt = boardTxt;
    }

    public void solve() {
        fillBoardFromTxT(boardTxt);
        printBoard();
        solveBoard();
    }

    private void fillBoardFromTxT(String boardTxt) {
        String[] rows = boardTxt.split("\n");
        for (int i = 0; i < 9; i++) {
            String[] vals = rows[i].split(",");
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(vals[j]);
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < 9; i++) {
            String row = "";
            for (int j = 0; j < 9; j++) {
                row = row + " " + board[i][j];
            }
            System.out.println(row);
        }
    }

    private static class Box {
        int row;
        int col;
    }

    private boolean solveBoard() {
        Box nextEmpty = findNextEmpty();

        if (nextEmpty.row == -1) {
            System.out.println("Solved");
            printBoard();
            return true;
        }

        for (int guess = 1; guess < 10; guess++) {
            if (isValid(guess, nextEmpty)) {
                board[nextEmpty.row][nextEmpty.col] = guess;
                if (solveBoard()) {
                    return true;

                } else {
                    board[nextEmpty.row][nextEmpty.col] = 0;
                }
            }
        }
        return false;
    }

    private boolean isValid(int num, Box b) {

        // check across
        for (int i = 0; i < 9; i++) {
            if (num == board[b.row][i])
                return false;
        }
        // check down
        for (int i = 0; i < 9; i++) {
            if (num == board[i][b.col])
                return false;
        }

        // check cube
        int startRow = Math.floorDiv(b.row, 3) * 3;
        int startCol = Math.floorDiv(b.col, 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (num == board[i][j])
                    return false;
            }
        }

        return true;
    }

    private Box findNextEmpty() {
        Box b = new Box();
        b.row = -1;
        b.col = -1;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    b.row = i;
                    b.col = j;
                    return b;
                }
            }
        }
        return b;
    }

    public static void main(String[] args) {
        String boardTxt = """
                0,0,1,8,2,5,0,0,0
                7,9,2,0,0,0,8,6,0
                0,0,5,6,0,7,0,0,2
                5,0,0,0,0,0,0,0,0
                1,0,0,0,3,0,0,2,6
                0,0,0,9,6,0,0,0,4
                0,0,0,0,0,9,6,3,0
                0,1,6,0,8,0,0,5,7
                2,0,3,1,5,6,9,4,8
                """;

        SudokuJ s = new SudokuJ(boardTxt);
        s.solve();
    }
}