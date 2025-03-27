package main.java.fr.ynov.sudoku.gui;

import main.java.fr.ynov.sudoku.gui.*;
import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private SudokuPanel sudokuPanel;

    public ControlPanel(SudokuPanel sudokuPanel) {
        this.sudokuPanel = sudokuPanel;
        setLayout(new FlowLayout());

        JButton solveButton = new JButton("Résoudre");
        JButton clearButton = new JButton("Effacer");

        solveButton.addActionListener(e -> sudokuPanel.solve());
        clearButton.addActionListener(e -> sudokuPanel.clear());

        add(solveButton);
        add(clearButton);
    }
}
