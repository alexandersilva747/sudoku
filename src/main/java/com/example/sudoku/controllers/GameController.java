/**
 * @autor 2343025-2724 OLman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.controllers;


import com.example.sudoku.models.Game;
import com.example.sudoku.models.alerts.AlertBoxGame;
import com.example.sudoku.views.InstructionsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    public TextField TextField00;
    public TextField TextField01;
    public TextField TextField02;
    public TextField TextField03;
    public TextField TextField10;
    public TextField TextField11;
    public TextField TextField12;
    public TextField TextField13;
    public TextField TextField04;
    public TextField TextField14;
    public TextField TextField05;
    public TextField TextField20;
    public TextField TextField21;
    public TextField TextField22;
    public TextField TextField23;
    public TextField TextField24;
    public TextField TextField15;
    public TextField TextField25;
    public TextField TextField30;
    public TextField TextField40;
    public TextField TextField31;
    public TextField TextField41;
    public TextField TextField50;
    public TextField TextField51;
    public TextField TextField32;
    public TextField TextField42;
    public TextField TextField52;
    public TextField TextField33;
    public TextField TextField43;
    public TextField TextField53;
    public TextField TextField34;
    public TextField TextField35;
    public TextField TextField44;
    public TextField TextField45;
    public TextField TextField54;
    public TextField TextField55;
    /**
     * Button to star the game
     */
    @FXML
    private Button starGameButton;
    /**
     * Button to get help
     */
    private Button helpButton;
    /**
     * Button to validate the game
     */
    private Button validateButton;
    /**
     * Button to obtain the game instructions.
     */
    private Button gameInstructionsButton;

    /**
     * Campo de texto que captura el numero ingresado por el usuario.
     * Este campo esta vinculado a la interfaz grafica
     *
     */
    @FXML
    private TextField txtPalabraSecreta;

    /**
     * juego Instancia del juego que maneja la lógica y estado del juego.
     */
    private Game game;

    /**
     * Arreglo que representa los numeros que se van ingresando
     */
    private TextField[] camposLetras;


    @FXML
    public void onActionStarGameButton(ActionEvent event) {
        // Mostrar la alerta al usuario
        new AlertBoxGame().showAlert(
                "Sudoku",
                "This is your last warning",
                "Are you ready?"
        );

        // Generar la nueva matriz de Sudoku
        game = new Game();
        int[][] matriz = game.getMatrizGame();

        // Llenar los TextField con los números generados
        llenarSudoku(matriz);
    }

    /**
     * Llena los TextField del Sudoku con los valores generados en la matriz.
     */
    private void llenarSudoku(int[][] matriz) {
        // Obtén todos los TextField del GridPane (asumiendo que ya están asociados correctamente)
        TextField[][] camposTexto = obtenerCamposSudoku();

        // Llenar los TextField con los valores de la matriz
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (matriz[row][col] != 0) {
                    camposTexto[row][col].setText(String.valueOf(matriz[row][col]));
                    camposTexto[row][col].setEditable(false); // Si es un número fijo, no se debe poder editar
                } else {
                    camposTexto[row][col].setText(""); // Dejar vacíos los campos editables
                    camposTexto[row][col].setEditable(true); // Permitir al jugador ingresar números
                }
            }
        }
    }

    /**
     * Este metodo debe obtener todos los TextField que forman el tablero de Sudoku.
     * Puedes hacer esto usando fx:id en tu FXML para cada TextField o de forma programática.
     */
    private TextField[][] obtenerCamposSudoku() {
        // Suponiendo que tienes todos los TextField en un array o GridPane
        // Necesitarás vincular los TextField desde tu archivo FXML a variables en este metodo
        TextField[][] campos = new TextField[6][6];
        campos[0][0] = TextField00;
        campos[0][1] = TextField01;
        campos[0][2] = TextField02;
        campos[0][3] = TextField03;
        campos[0][4] = TextField04;
        campos[0][5] = TextField05;
        campos[1][0] = TextField10;
        campos[1][1] = TextField11;
        campos[1][2] = TextField12;
        campos[1][3] = TextField13;
        campos[1][4] = TextField14;
        campos[1][5] = TextField15;
        campos[2][0] = TextField20;
        campos[2][1] = TextField21;
        campos[2][2] = TextField22;
        campos[2][3] = TextField23;
        campos[2][4] = TextField24;
        campos[2][5] = TextField25;
        campos[3][0] = TextField30;
        campos[3][1] = TextField31;
        campos[3][2] = TextField32;
        campos[3][3] = TextField33;
        campos[3][4] = TextField34;
        campos[3][5] = TextField35;
        campos[4][0] = TextField40;
        campos[4][1] = TextField41;
        campos[4][2] = TextField42;
        campos[4][3] = TextField43;
        campos[4][4] = TextField44;
        campos[4][5] = TextField45;
        campos[5][0] = TextField50;
        campos[5][1] = TextField51;
        campos[5][2] = TextField52;
        campos[5][3] = TextField53;
        campos[5][4] = TextField54;
        campos[5][5] = TextField55;

        return campos;
    }

    @FXML
    public void onActionGameInstructionsButton(ActionEvent actionEvent) throws IOException {
        InstructionsView instructionsView = InstructionsView.getInstance();
        instructionsView.showInstructions(); // Llamar al metodo para mostrar la ventana o llevarla al frente
    }
    @FXML
    public void onActionValidateButton(ActionEvent actionEvent) {
        // Obtener los valores del Sudoku desde los TextField
        TextField[][] camposTexto = obtenerCamposSudoku();
        int[][] matrizActual = new int[6][6];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                String valorTexto = camposTexto[row][col].getText();
                if (!valorTexto.isEmpty()) {
                    try {
                        int num = Integer.parseInt(valorTexto);
                        matrizActual[row][col] = num;
                    } catch (NumberFormatException e) {
                        // Manejar números inválidos (por ejemplo, si el jugador ingresó un valor no numérico)
                        new AlertBoxGame().showAlert(
                                "Sudoku",
                                "Error",
                                "Please enter valid numbers."
                        );
                        return;
                    }
                }
            }
        }

        // Validar cada número de la matriz
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int num = matrizActual[row][col];
                if (num != 0) {
                    if (!game.validateMatrizGame(num, row, col)) {
                        // Si hay un error en la validación, mostrar alerta y detener
                        return;
                    }
                }
            }
        }

        // Si todas las celdas son válidas, puedes mostrar un mensaje de éxito
        new AlertBoxGame().showAlert(
                "Sudoku",
                "CONGRATULATIONS",
                "YOU DID IT"
        );
    }

    @FXML
    public void onActionHelpButton(ActionEvent actionEvent) {
        // Obtén la matriz actual del juego
        int[][] matriz = game.getMatrizGame();

        // Obtén los TextField (asociados al tablero)
        TextField[][] camposTexto = obtenerCamposSudoku();

        // Encuentra un campo vacío aleatorio
        List<int[]> camposVacios = new ArrayList<>();
        for (int fila = 0; fila < 6; fila++) {
            for (int col = 0; col < 6; col++) {
                if (matriz[fila][col] == 0) { // Campo vacío
                    camposVacios.add(new int[]{fila, col});
                }
            }
        }

        if (camposVacios.isEmpty()) {
            new AlertBoxGame().showAlert("Sudoku", "¡Sin sugerencias!", "El tablero está completo.");
            return;
        }

        // Selecciona un campo vacío aleatorio
        Random random = new Random();
        int[] campoAleatorio = camposVacios.get(random.nextInt(camposVacios.size()));
        int fila = campoAleatorio[0];
        int col = campoAleatorio[1];

        // Buscar un número válido para esa posición
        for (int num = 1; num <= 6; num++) {
            if (game.validateMatrizGame(num, fila, col)) {
                // Sugerir el número al jugador
                camposTexto[fila][col].setText(String.valueOf(num));
                camposTexto[fila][col].setStyle("-fx-background-color: lightgreen;"); // Cambiar color como sugerencia
                new AlertBoxGame().showAlert("Sudoku", "Sugerencia", "Número sugerido en fila " + (fila+1) + ", columna " + (col+1));
                return;
            }
        }

        new AlertBoxGame().showAlert("Sudoku", "Sin sugerencias", "No se encontró un número válido para el campo seleccionado.");
    }
}