package main.java.fr.ynov.sudoku.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGrid {
    private static final int GRID_SIZE = 9;
    private Random random;

    public SudokuGrid() {
        this.random = new Random();
    }

    public int[][] generatePuzzleGrid() {
        int[][] fullGrid = new int[GRID_SIZE][GRID_SIZE];
        if (fillGrid(fullGrid)) {
            return removeNumbers(fullGrid);
        }
        return null;
    }

    private boolean fillGrid(int[][] grid) {
        return fillGridRecursive(grid, 0, 0);
    }

    private boolean fillGridRecursive(int[][] grid, int row, int col) {
        if (row == GRID_SIZE) {
            return true;
        }

        if (col == GRID_SIZE) {
            return fillGridRecursive(grid, row + 1, 0);
        }

        List<Integer> numbers = generateRandomNumbers();

        for (int num : numbers) {
            if (isValidPlacement(grid, row, col, num)) {
                grid[row][col] = num;

                if (fillGridRecursive(grid, row, col + 1)) {
                    return true;
                }

                grid[row][col] = 0;
            }
        }

        return false;
    }

    private List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    private boolean isValidPlacement(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < GRID_SIZE; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        for (int x = 0; x < GRID_SIZE; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[][] removeNumbers(int[][] grid) {
        int[][] puzzleGrid = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            System.arraycopy(grid[i], 0, puzzleGrid[i], 0, GRID_SIZE);
        }

        int cellsToKeep = 25 + random.nextInt(10);

        List<int[]> positions = new ArrayList<>();
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                positions.add(new int[]{row, col});
            }
        }

        Collections.shuffle(positions);

        int cellsRemoved = 0;
        for (int[] pos : positions) {
            if (cellsRemoved >= GRID_SIZE * GRID_SIZE - cellsToKeep) {
                break;
            }
            puzzleGrid[pos[0]][pos[1]] = 0;
            cellsRemoved++;
        }

        return puzzleGrid;
    }

}