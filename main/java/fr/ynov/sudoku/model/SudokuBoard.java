package main.java.fr.ynov.sudoku.model;

public class SudokuBoard {
    private static final int SIZE = 9;
    private SudokuCell[][] cells;

    public SudokuBoard() {
        cells = new SudokuCell[SIZE][SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new SudokuCell();
            }
        }
    }

    public SudokuCell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, int value) {
        cells[row][col].setValue(value);
    }

    public void clearBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].clear();
            }
        }
    }
}
