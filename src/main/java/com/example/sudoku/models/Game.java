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
    public Game() {
        matrizGame = new int[6][6];
        generateMatriz();
    }

    /**
     * Method to generate array 6x6 of Sudoku with random numbers.
     * Place some numbers and leave others blank (represented by zero).
     */
    public void generateMatriz() {
        Random random = new Random();

        // Place the array with random numbers
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                // Assign random numbers between 1 and 6, or leave empty (0) for the user.
                if (random.nextBoolean()) {
                    boolean validNumber = false;
                    //When I was using while, I got error.
                    if (!validNumber){
                        int num = random.nextInt(6)+ 1; //Numbers from 1 to 6
                        //Verify if the number can be placed.
                        if (isValid(num, row, col)){
                            matrizGame[row][col] = num; // PLaced the number in the row
                            validNumber = false; // Valid number, get out of the loop
                        }
                    }
                } else {
                    matrizGame[row][col] = 0; // empty row
                }
            }
        }
    }

    /**
     * Method to verification if one number is valid in the row.
     * @param num represent the generate number
     * @param fila represent the value of the row
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

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 2; j++){
                if (matrizGame[inicioFila + i][inicioColumna + j] == num){
                    return  false; // The number is already in the quadrant 2*3
                }
            }
        }
        return true; //Valid number
    }

    public int[][] getArrayGame() {
        return matrizGame;
    }

    /**
     * Method to validate the help button
     * @param num represent the generate number
     * @param fila represent the value of the row
     * @param columna represent the value of the column
     * @return false when the number is already in the row
     */
    public boolean isValidSuggestion(int num, int fila, int columna) {
        // Verification of the row
        for (int col = 0; col < 6; col++) {
            if (matrizGame[fila][col] == num) {
                return false; // The number is already in the row
            }
        }

        // Column verification.
        for (int row = 0; row < 6; row++) {
            if (matrizGame[row][columna] == num) {
                return false; // The number is in the column
            }
        }

        // Quadrant verification 3*2
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 2) * 2;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (matrizGame[inicioFila + i][inicioColumna + j] == num) {
                    return false; // The number is in the quadrant.
                }
            }
        }

        return true; // Valid number
    }


    /**
     * Method to validate the array game.
     * @param num represent the generate number
     * @param fila represent the value of the row
     * @param columna represent the value of the column
     * @return false when the number is already in the row
     */
    public boolean validateArrayGame(int num, int fila, int columna) {
        //Row verification
        for (int col = 0; col < 6; col++) {
            if (matrizGame[fila][col] == num) {
                new AlertBoxGame().showAlert(
                        "Sudoku",
                        "Something isn't working",
                        "Please, try again"
                );
                return false; //Number in the row
            }
        }
        // Column verification
        for (int row = 0; row < 6; row++) {
            if (matrizGame[row][columna] == num) {
                new AlertBoxGame().showAlert(
                        "Sudoku",
                        "Something isn't working",
                        "Please, try again"
                );
                return false; // Number in the column
            }
        }
        //Quadrant 2*3 verification
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 2) * 2;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 2; j++){
                if (matrizGame[inicioFila + i][inicioColumna + j] == num){
                    new AlertBoxGame().showAlert(
                            "Sudoku",
                            "Something isn't working",
                            "Please, try again"
                    );
                    return  false; // Number in the 2x3 quadrant
                }
            }
        }
        new AlertBoxGame().showAlert(
                "Sudoku",
                "CONGRATULATIONS",
                "YOU DID IT"
        );
        return true; //Valid number
    }
}

