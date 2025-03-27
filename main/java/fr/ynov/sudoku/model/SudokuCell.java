package main.java.fr.ynov.sudoku.model;

public class SudokuCell {
    private int value;
    private boolean isEditable;

    public SudokuCell() {
        this.value = 0;
        this.isEditable = true;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public void clear() {
        this.value = 0;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }
}
