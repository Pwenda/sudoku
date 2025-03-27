package main.java.fr.ynov.sudoku.solver;

import main.java.fr.ynov.sudoku.model.SudokuBoard;
import main.java.fr.ynov.sudoku.utils.SudokuValidator;

public class SudokuSolver {
    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getCell(row, col).isEmpty()) {
                    for (int num = 1; num <= 9; num++) {
                        if (SudokuValidator.isValidMove(board, row, col, num)) {
                            board.setCell(row, col, num);

                            if (solve(board)) {
                                return true;
                            }

                            board.setCell(row, col, 0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
