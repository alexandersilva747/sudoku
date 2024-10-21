/**
 * @autor 2343025-2724 OLman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.models;

import com.example.sudoku.models.alerts.AlertBoxGame;

import java.util.Random;

/**
 * Model class that contain de logic of the sudoku game.
 */
public class Game {
    /**
     * Two-dimensional array,where the sudoku numbers will go.
     */
    private int[][] matrizGame;

    /**
     * Constructor to initialize the array
     */
    public Game(int[] quadrantNumbers) {
        matrizGame = new int[6][6];
        generateMatriz(matrizGame, quadrantNumbers);

    }

    /**
     * Method to generate array 6x6 of Sudoku with random numbers.
     * Place some numbers and leave others blank (represented by zero).
     */
//    private boolean generateCompleteMatriz(int row, int col) {
//        if (row == 6) return true; // Completed board.
//        if (col == 6) return generateCompleteMatriz(row + 1, 0); // Go to next row.
//
//        Random random = new Random();
//        int[] numbers = {1, 2, 3, 4, 5, 6}; // Possible numbers.
//        shuffleArray(numbers, random); // Shuffle numbers for randomness.
//
//        for (int num : numbers) {
//            if (isValid(num, row, col)) {
//                matrizGame[row][col] = num;
//
//                if (generateCompleteMatriz(row, col + 1))
//                    return true;
//
//                matrizGame[row][col] = 0; // Backtrack.
//            }
//        }
//        return false; // No valid configuration found.
//    }


    /**
     * Method to generate array and partially reveal numbers in the Sudoku grid based on quadrantNumbers.
     */
    public void generateMatriz(int[][] matrizGame, int[] quadrantNumbers) {
        Random random = new Random();
        int quadrantIndex = 0;

        // Iterate over each 3x2 quadrant.
        for (int blockRow = 0; blockRow < 6; blockRow += 3) {
            for (int blockCol = 0; blockCol < 6; blockCol += 2) {
                int numbersToPlace = quadrantNumbers[quadrantIndex++];

                // Place the specified number of values in the quadrant.
                for (int n = 0; n < numbersToPlace; n++) {
                    boolean validNumberPlaced = false;

                    while (!validNumberPlaced) {
                        int fila = blockRow + random.nextInt(3);
                        int col = blockCol + random.nextInt(2);

                        if (matrizGame[fila][col] == 0) {
                            int num = random.nextInt(6) + 1;
                            if (isValid(num, fila, col)){
                                matrizGame[fila][col] = num;
                                validNumberPlaced = true;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to verification if one number is valid in the row, column and quadrant.
     *
     * @param num     represent the generate number
     * @param fila    represent the value of the row
     * @param columna represent the value of the column
     * @return false when the number is already in the row
     */

    private boolean isValid(int num, int fila, int columna) {
        //Verification of the row
        for (int col = 0; col < 6; col++) {
            if (matrizGame[fila][col] == num) {
                return false; //The number is already in the row.
            }
        }
        // Verification of column
        for (int row = 0; row < 6; row++) {
            if (matrizGame[row][columna] == num) {
                return false; // The number is already in the column
            }
        }
        //Verification of quadrant 2*3
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 2) * 2;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (matrizGame[inicioFila + i][inicioColumna + j] == num) {
                    return false; // The number is already in the quadrant 2*3
                }
            }
        }
        return true; //Valid number
    }


    /**
     * Shuffle an array to randomize its contents.
     */
    private void shuffleArray(int[] array, Random random) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * Get the current Sudoku board.
     */
    public int[][] getArrayGame() {
        return matrizGame;
    }


    /**
     * Validate if a suggestion from click in help is valid.
     */
    public boolean isValidSuggestion(int num, int fila, int columna) {
        return isValid(num, fila, columna);
    }


    /**
     * Validate the array game.
     */
    public boolean validateArrayGame(int num, int fila, int columna) {
        if (!isValid(num, fila, columna)) {
            new AlertBoxGame().showAlert(
                    "Sudoku", "Something isn't working", "Please, try again"
            );
            return false;
        }
        new AlertBoxGame().showAlert(
                "Sudoku", "CONGRATULATIONS", "YOU DID IT"
        );
        return true;
    }
}
