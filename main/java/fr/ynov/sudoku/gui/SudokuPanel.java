package main.java.fr.ynov.sudoku.gui;

import main.java.fr.ynov.sudoku.model.SudokuBoard;
import main.java.fr.ynov.sudoku.solver.SudokuSolver;
import main.java.fr.ynov.sudoku.solver.SudokuGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SudokuPanel extends JPanel {
    private static final int GRID_SIZE = 9;
    private JTextField[][] cellFields;
    private SudokuBoard board;
    private SudokuSolver solver;
    private SudokuGrid gridGenerator;
    private static final int INITIAL_LIVES = 3;
    private int lives;
    private int[][] solution;
    private JLabel livesLabel;

    public SudokuPanel() {
        board = new SudokuBoard();
        solver = new SudokuSolver();
        gridGenerator = new SudokuGrid();
        lives = INITIAL_LIVES;

        livesLabel = new JLabel("Vies: " + lives);
        livesLabel.setFont(new Font("Arial", Font.BOLD, 16));

        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        cellFields = new JTextField[GRID_SIZE][GRID_SIZE];

        initializeGrid(gridPanel);
        add(livesLabel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        addCellKeyListener();
        generateInitialGrid();

    }

    private void initializeGrid(JPanel gridPanel) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cellFields[row][col] = createTextField(row, col);
                gridPanel.add(cellFields[row][col]);
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
        solution = gridGenerator.generateFullSolution();

        int[][] initialGrid = gridGenerator.createPuzzleFromSolution(solution);

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
        updateLivesLabel();
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
        lives = INITIAL_LIVES;
        updateLivesLabel();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cellFields[row][col].setText("");
                cellFields[row][col].setEditable(true);
                cellFields[row][col].setBackground(Color.WHITE);
                cellFields[row][col].setForeground(Color.BLACK);
                board.setCell(row, col, 0);
            }
        }

        generateInitialGrid();
    }

    private void addCellKeyListener() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                final int finalRow = row;
                final int finalCol = col;

                cellFields[row][col].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        JTextField textField = cellFields[finalRow][finalCol];

                        if (!textField.isEditable()) {
                            e.consume();
                            return;
                        }

                        char c = e.getKeyChar();

                        if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                            return;
                        }

                        if (c < '1' || c > '9') {
                            e.consume();
                            return;
                        }

                        if (!textField.getText().isEmpty()) {
                            e.consume();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        JTextField textField = cellFields[finalRow][finalCol];

                        if (textField.isEditable()) {
                            String text = textField.getText();
                            if (!text.isEmpty()) {
                                int value = Integer.parseInt(text);

                                if (value != solution[finalRow][finalCol]) {
                                    lives--;
                                    updateLivesLabel();

                                    textField.setForeground(Color.RED);

                                    if (lives <= 0) {
                                        gameOver();
                                    }
                                } else {
                                    textField.setForeground(Color.BLUE);
                                }

                                board.setCell(finalRow, finalCol, value);
                            } else {
                                board.setCell(finalRow, finalCol, 0);
                            }
                        }
                    }
                });
            }
        }
    }
    private void updateLivesLabel() {
        livesLabel.setText("Vies: " + lives);
    }
    private void gameOver() {
        JOptionPane.showMessageDialog(this,
                "Vous avez perdu !",
                "Game Over",
                JOptionPane.ERROR_MESSAGE
        );
        revealSolution();
    }
    private void revealSolution() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (cellFields[row][col].isEditable()) {
                    cellFields[row][col].setText(String.valueOf(solution[row][col]));
                    cellFields[row][col].setEditable(false);
                    board.setCell(row, col, solution[row][col]);
                }
            }
        }
    }
}