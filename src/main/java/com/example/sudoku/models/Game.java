/**
 * @autor 2343025-2724 OLman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.models;

import com.example.sudoku.models.alerts.AlertBoxGame;

import java.util.Random;

public class Game {
    /**
     * Matriz bidimensional donde iran los numeros del sudoku.
     */
    private int[][] matrizGame;

    public Game() {
        matrizGame = new int[6][6];
        generateMatriz();
    }

    /**
     * Genera una matriz 6x6 de Sudoku con números aleatorios.
     * Coloca algunos números y deja otros vacíos (representados por 0).
     */
    public void generateMatriz() {
        Random random = new Random();

        // Llenar la matriz con algunos números aleatorios
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                // Asignar números aleatorios entre 1 y 6, o deja vacía (0) para que el jugador complete
                if (random.nextBoolean()) {
                    boolean validNumber = false;
                    //Verificar esta parte de mi codigo, me estaba generando un ciclo infinito con while
                    if (!validNumber){
                        int num = random.nextInt(6)+ 1; //Numeros del 1 al 6
                        //Verificar si el numero se puede colocar
                        if (isValid(num, row, col)){
                            matrizGame[row][col] = num; // Coloca el numero en la celda
                            validNumber = false; // Numero valido, salir del ciclo
                        }
                    }
                    //matrizGame[row][col] = random.nextInt(6) + 1; //Celda llena
                } else {
                    matrizGame[row][col] = 0; // Celda vacía
                }
            }
        }
    }

    /**
     * Metodo para verificar si un numero es valido en una celda dada
     * @param num representa al numero generado
     * @param fila representa valor de la fila
     * @param columna representa valor de la columna
     * @return retorna false para indicar que el numero ya esta en la fila
     */

    private boolean isValid(int num, int fila, int columna) {
        //Verificar fila
        for (int col = 0; col < 6; col++) {
            if (matrizGame[fila][col] == num) {
                return false; //El numero ya esta en la fila
            }
        }
        // Verificar columna
        for (int row = 0; row < 6; row++) {
            if (matrizGame[row][columna] == num) {
                return false; // El numero ya esta en la columna
            }
        }
        //Verificar cuadrante 2*3
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 2) * 2;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 2; j++){
                if (matrizGame[inicioFila + i][inicioColumna + j] == num){
                    return  false; // El numero ya esta en el cuadrante 2*3
                }
            }
        }
        return true; //El numero es valido
    }

    public int[][] getMatrizGame() {
        return matrizGame;
    }

    //metodo para validar la ayuda

    public boolean isValidSuggestion(int num, int fila, int columna) {
        // Verificar fila
        for (int col = 0; col < 6; col++) {
            if (matrizGame[fila][col] == num) {
                return false; // El numero ya esta en la fila
            }
        }

        // Verificar columna
        for (int row = 0; row < 6; row++) {
            if (matrizGame[row][columna] == num) {
                return false; // El numero ya esta en la columna
            }
        }

        // Verificar cuadrante 3*2
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 2) * 2;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (matrizGame[inicioFila + i][inicioColumna + j] == num) {
                    return false; // El número ya está en el cuadrante
                }
            }
        }

        return true; // El número es válido
    }

    // Metodo para validar la matriz
    public boolean validateMatrizGame(int num, int fila, int columna) {
        //Verificar fila
        for (int col = 0; col < 6; col++) {
            if (matrizGame[fila][col] == num) {
                new AlertBoxGame().showAlert(
                        "Sudoku",
                        "Something isn't working",
                        "Please, try again"
                );
                return false; //El numero ya esta en la fila
            }
        }
        // Verificar columna
        for (int row = 0; row < 6; row++) {
            if (matrizGame[row][columna] == num) {
                new AlertBoxGame().showAlert(
                        "Sudoku",
                        "Something isn't working",
                        "Please, try again"
                );
                return false; // El numero ya esta en la columna
            }
        }
        //Verificar cuadrante 2*3
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
                    return  false; // El numero ya esta en el cuadrante 2*3
                }
            }
        }
        //Ajustar mensaje, porque esta apareciendo cuando hay colmnas o filas llenas,
        //Solo debe aparecer cuando se complar las tres condiciones
        new AlertBoxGame().showAlert(
                "Sudoku",
                "CONGRATULATIONS",
                "YOU DID IT"
        );
        return true; //El numero es valido
    }
}

