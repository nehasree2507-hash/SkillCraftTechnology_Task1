package Task3;
public class SudokuSolver {

    static final int SIZE = 9; // a Sudoku board is 9x9

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("\nSudoku puzzle:");
        printBoard(board);
        if (solve(board)) {
            System.out.println("\n------+-------+------");
            System.out.println("\n------+-------+------");
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("\nThis Sudoku puzzle has no solution.");
        }
    }
    static boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Look for an empty cell.
                if (board[row][col] == 0) {
                    // Try every digit from 1 to 9.
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num; // place the digit
                            // Recursively try to solve the rest.
                            if (solve(board)) {
                                return true;
                            }
                            // Didn't work out - undo and try the next digit.
                            board[row][col] = 0;
                        }
                    }
                    // No digit fit here, so a previous choice was wrong.
                    return false;
                }
            }
        }
        // No empty cell left means the board is fully solved.
        return true;
    }

    // Checks whether placing 'num' at board[row][col] is allowed.
    static boolean isSafe(int[][] board, int row, int col, int num) {
        // Rule 1: number must not already be in this row.
        for (int c = 0; c < SIZE; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }
        // Rule 2: number must not already be in this column.
        for (int r = 0; r < SIZE; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }
        // Rule 3: number must not already be in the 3x3 box.
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;
        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        // Passed all three rules.
        return true;
    }

    // Prints the board in a neat grid with lines between the 3x3 boxes.
    static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }
            for (int col = 0; col < SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }
                // Show a dot for empty cells so they are easy to spot.
                if (board[row][col] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
        }
    }
}
