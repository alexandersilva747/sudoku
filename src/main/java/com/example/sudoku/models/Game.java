/**
 * @autor 2343025-2724 OLman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.models;

import java.util.Random;

public class Game {
    /**
     * Matriz bidimensional donde iran los numeros del sudoku.
     */
    private int[][] matrizGame;

    public Game() {
        matrizGame = new int[6][6];
        generarMatriz();
    }

    /**
     * Genera una matriz 6x6 de Sudoku con números aleatorios.
     * Coloca algunos números y deja otros vacíos (representados por 0).
     */
    public void generarMatriz() {
        Random random = new Random();

        // Llenar la matriz con algunos números aleatorios
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                // Asigna números aleatorios entre 1 y 6, o deja vacía (0) para que el jugador complete
                if (random.nextBoolean()) {
                    matrizGame[row][col] = random.nextInt(6) + 1;
                } else {
                    matrizGame[row][col] = 0; // Celda vacía
                }
            }
        }
    }

    public int[][] getMatrizGame() {
        return matrizGame;
    }
}

