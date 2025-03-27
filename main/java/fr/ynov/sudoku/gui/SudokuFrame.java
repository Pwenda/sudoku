package main.java.fr.ynov.sudoku.gui;

import main.java.fr.ynov.sudoku.gui.SudokuPanel;
import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame {
    private SudokuPanel sudokuPanel;
    private ControlPanel controlPanel;

    public SudokuFrame() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 600));

        sudokuPanel = new SudokuPanel();
        controlPanel = new ControlPanel(sudokuPanel);

        add(sudokuPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }
}
