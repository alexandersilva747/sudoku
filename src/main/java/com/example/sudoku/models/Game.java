/**
 * @autor 2343025-2724 OLman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.models;

import java.util.Random;

/**
 * Model class that contain de logic of the sudoku game.
 */

public class Game {
    /**
     * Two-dimensional array, where the Sudoku numbers will go.
     */
    private int[][] matrizGame;
    private int[][] solvedArray; //copy for the validation and help

    /**
     * Constructor to initialize the array
     */
    public Game(int[] quadrantNumbers) {
        matrizGame = new int[6][6];
        solvedArray = new int[6][6];
        // Fill the board completely
        if (fillBoard()) {
            //copy solved array
            copySolvedArray();
            // Remove numbers based on quadrantNumbers
            removeNumbers(quadrantNumbers);
        }
    }

    /**
     * Method to generate a full board using backtracking
     */
    private boolean fillBoard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (matrizGame[row][col] == 0) {
                    for (int num = 1; num <= 6; num++) {
                        if (isValid(num, row, col)) {
                            matrizGame[row][col] = num;
                            if (fillBoard()) {
                                return true;
                            }
                            matrizGame[row][col] = 0; // backtrack
                        }
                    }
                    return false; // if no valid number can be placed
                }
            }
        }
        return true; // board is completely filled
    }

    /**
     * Method to copy the fully solved board into solvedArray
     */
    private void copySolvedArray() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                solvedArray[row][col] = matrizGame[row][col];
            }
        }
    }

    /**
     * Method to remove numbers from the board based on quadrantNumbers
     */
    public void removeNumbers(int[] quadrantNumbers) {
        Random random = new Random();
        int quadrantIndex = 0;

        // Iterate over each 3x2 quadrant
        for (int blockRow = 0; blockRow < 6; blockRow += 3) {
            for (int blockCol = 0; blockCol < 6; blockCol += 2) {
                int numbersToPlace = quadrantNumbers[quadrantIndex++];

                // Remove numbers in the quadrant
                int removed = 0;
                while (removed < (6 - numbersToPlace)) { // Remove numbers to match the quadrantNumbers
                    int fila = blockRow + random.nextInt(3);
                    int col = blockCol + random.nextInt(2);

                    if (matrizGame[fila][col] != 0) {
                        matrizGame[fila][col] = 0;
                        removed++;
                    }
                }
            }
        }
    }

    /**
     * Method to validate if a number is valid in the row, column, and quadrant
     */
    private boolean isValid(int num, int fila, int columna) {
        // Verification of the row
        for (int col = 0; col < 6; col++) {
            if (matrizGame[fila][col] == num) {
                return false; // The number is already in the row
            }
        }
        // Verification of the column
        for (int row = 0; row < 6; row++) {
            if (matrizGame[row][columna] == num) {
                return false; // The number is already in the column
            }
        }
        // Verification of 3x2 quadrant
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 2) * 2;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (matrizGame[inicioFila + i][inicioColumna + j] == num) {
                    return false; // The number is already in the 3x2 quadrant
                }
            }
        }
        return true; // Valid number
    }

    /**
     * Get the initial Sudoku board
     */
    public int[][] getArrayGame() {
        return matrizGame;
    }

    /**
     * Get the fully solved Sudoku board
     */
    public int[][] getSolvedArray() {
        return solvedArray;
    }
}
