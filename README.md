# Sudoku Game & Solver

## Overview
This Java application provides a complete implementation of the Sudoku game, including grid generation, solving capabilities, and real-time move validation according to classic Sudoku rules.

## Features

- **Grid Generation** - Creates Sudoku puzzles with unique solutions
- **Automatic Solver** - Implements backtracking algorithm to solve any valid Sudoku grid
- **Move Validation** - Real-time validation of moves against Sudoku rules
- **Adjustable Difficulty** - Puzzles with 25-34 initial hints for varied gameplay
- **Clean Architecture** - Modular design with separation of concerns

## Technical Stack

- Java (JDK 11+)
- Maven for dependency management

## Project Structure

- `fr.ynov.sudoku.model` - Board and cell representation classes
- `fr.ynov.sudoku.solver` - Grid generation and solving algorithms
- `fr.ynov.sudoku.utils` - Helper utilities for game logic

## Key Algorithms

### Grid Generation
The application creates puzzles by:
1. Generating a fully populated valid grid
2. Strategically removing numbers while maintaining uniqueness
3. Preserving 25-34 initial values for an appropriate difficulty level

### Solving Algorithm
The solver implements backtracking to:
1. Identify empty cells
2. Test valid numbers (1-9) according to Sudoku constraints
3. Recursively advance or backtrack when needed

## Installation

```bash
git clone https://github.com/username/sudoku.git
cd sudoku
mvn clean install
```

## Usage

```bash
java -jar target/sudoku-1.0.jar
```

