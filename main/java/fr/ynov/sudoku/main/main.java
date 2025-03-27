package main.java.fr.ynov.sudoku.main;

import main.java.fr.ynov.sudoku.gui.SudokuFrame;
import javax.swing.SwingUtilities;


public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuFrame frame = new SudokuFrame();
            frame.setVisible(true);
        });
    }
}
