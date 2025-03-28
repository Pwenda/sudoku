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

    public boolean setValue(int value) {
        if (! isEditable){
            return false;
        }
        if (!isValidInput(value)){
            return false;
        }
        this.value = value;
        return true;
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
    public static boolean isValidInput(int value) {
        return value >= 0 && value <= 9;
    }
}
