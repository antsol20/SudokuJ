public class SudokuJSolve {

    final private int[][] board;

    public SudokuJSolve(int[][] board) {
        this.board = board;
    }

    public int[][] solve() {
        solveBoard();
        return board;
    }


    private static class SudokuBox {
        int row;
        int col;
    }

    private boolean solveBoard() {
       SudokuBox nextEmpty = findNextEmpty();

        if (nextEmpty.row == -1) {
            System.out.println("Solved");
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

    private boolean isValid(int num, SudokuBox b) {

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

    private SudokuBox findNextEmpty() {
        SudokuBox b = new SudokuBox();
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
}
