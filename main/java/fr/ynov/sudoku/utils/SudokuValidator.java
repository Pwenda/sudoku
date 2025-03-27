package main.java.fr.ynov.sudoku.utils;

import main.java.fr.ynov.sudoku.model.SudokuBoard;


public class SudokuValidator {
    public static boolean isValidMove(SudokuBoard board, int row, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (board.getCell(row, x).getValue() == num) {
                return false;
            }
        }

        for (int x = 0; x < 9; x++) {
            if (board.getCell(x, col).getValue() == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(startRow + i, startCol + j).getValue() == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
