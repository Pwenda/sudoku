package main.java.fr.ynov.sudoku.gui;

import main.java.fr.ynov.sudoku.model.SudokuBoard;
import main.java.fr.ynov.sudoku.solver.SudokuSolver;
import main.java.fr.ynov.sudoku.solver.SudokuGrid;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {
    private static final int GRID_SIZE = 9;
    private JTextField[][] cellFields;
    private SudokuBoard board;
    private SudokuSolver solver;
    private SudokuGrid gridGenerator;

    public SudokuPanel() {
        board = new SudokuBoard();
        solver = new SudokuSolver();
        gridGenerator = new SudokuGrid();

        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        cellFields = new JTextField[GRID_SIZE][GRID_SIZE];

        initializeGrid();
        generateInitialGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cellFields[row][col] = createTextField(row, col);
                add(cellFields[row][col]);
            }
        }
    }

    private JTextField createTextField(int row, int col) {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.BOLD, 20));

        if ((row / 3 + col / 3) % 2 == 0) {
            textField.setBackground(Color.LIGHT_GRAY);
        }

        return textField;
    }

    private void generateInitialGrid() {
        int[][] initialGrid = gridGenerator.generatePuzzleGrid();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (initialGrid[row][col] != 0) {
                    cellFields[row][col].setText(String.valueOf(initialGrid[row][col]));
                    cellFields[row][col].setEditable(false);
                    cellFields[row][col].setBackground(Color.LIGHT_GRAY.darker());
                    board.setCell(row, col, initialGrid[row][col]);
                }
            }
        }
    }

    public void solve() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (cellFields[row][col].isEditable()) {
                    String text = cellFields[row][col].getText();
                    int value = text.isEmpty() ? 0 : Integer.parseInt(text);
                    board.setCell(row, col, value);
                }
            }
        }

        if (solver.solve(board)) {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    if (cellFields[row][col].isEditable()) {
                        cellFields[row][col].setText(
                                board.getCell(row, col).getValue() == 0 ?
                                        "" : String.valueOf(board.getCell(row, col).getValue())
                        );
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Pas de solution possible !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void clear() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (cellFields[row][col].isEditable()) {
                    cellFields[row][col].setText("");
                    board.setCell(row, col, 0);
                }
            }
        }
    }

    public void newGame() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cellFields[row][col].setText("");
                cellFields[row][col].setEditable(true);
                cellFields[row][col].setBackground(Color.WHITE);
                board.setCell(row, col, 0);
            }
        }

        generateInitialGrid();
    }
}