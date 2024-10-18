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
    private TextField[] numbFields;


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
        fillSudoku(matriz);
    }

    /**
     * Llena los TextField del Sudoku con los valores generados en la matriz.
     */
    private void fillSudoku(int[][] matriz) {
        // Obtener todos los TextField del GridPane
        TextField[][] textFields = getSudokuFields();
        //ciclo para limpiar el estilo de los campos que cambiaron a verde cuando se uso la ayuda en el juego anterior
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                textFields[row][col].setStyle(""); // Esto restablece el estilo a su valor por defecto
            }
        }

        // Llenar los TextField con los valores de la matriz
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (matriz[row][col] != 0) {
                    textFields[row][col].setText(String.valueOf(matriz[row][col]));
                    textFields[row][col].setEditable(false); // Si es un número fijo, no se debe poder editar
                } else {
                    textFields[row][col].setText(""); // Dejar vacíos los campos editables
                    textFields[row][col].setEditable(true); // Permitir al jugador ingresar números
                }
            }
        }
    }

    /**
     * Este metodo debe obtener todos los TextField que forman el tablero de Sudoku.
     */
    private TextField[][] getSudokuFields() {
        // vincular los TextField desde archivo FXML a variables en este metodo
        TextField[][] fields = new TextField[6][6];
        fields[0][0] = TextField00;
        fields[0][1] = TextField01;
        fields[0][2] = TextField02;
        fields[0][3] = TextField03;
        fields[0][4] = TextField04;
        fields[0][5] = TextField05;
        fields[1][0] = TextField10;
        fields[1][1] = TextField11;
        fields[1][2] = TextField12;
        fields[1][3] = TextField13;
        fields[1][4] = TextField14;
        fields[1][5] = TextField15;
        fields[2][0] = TextField20;
        fields[2][1] = TextField21;
        fields[2][2] = TextField22;
        fields[2][3] = TextField23;
        fields[2][4] = TextField24;
        fields[2][5] = TextField25;
        fields[3][0] = TextField30;
        fields[3][1] = TextField31;
        fields[3][2] = TextField32;
        fields[3][3] = TextField33;
        fields[3][4] = TextField34;
        fields[3][5] = TextField35;
        fields[4][0] = TextField40;
        fields[4][1] = TextField41;
        fields[4][2] = TextField42;
        fields[4][3] = TextField43;
        fields[4][4] = TextField44;
        fields[4][5] = TextField45;
        fields[5][0] = TextField50;
        fields[5][1] = TextField51;
        fields[5][2] = TextField52;
        fields[5][3] = TextField53;
        fields[5][4] = TextField54;
        fields[5][5] = TextField55;

        return fields;
    }

    @FXML
    public void onActionGameInstructionsButton(ActionEvent actionEvent) throws IOException {
        InstructionsView instructionsView = InstructionsView.getInstance();
        instructionsView.showInstructions(); // Llamar al metodo para mostrar la ventana o llevarla al frente
    }
    @FXML
    public void onActionValidateButton(ActionEvent actionEvent) {
        // Obtener los valores del Sudoku desde los TextField
        TextField[][] textFields = getSudokuFields();
        int[][] currentMatriz = new int[6][6];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                String textValue = textFields[row][col].getText();
                if (!textValue.isEmpty()) {
                    try {
                        int num = Integer.parseInt(textValue);
                        currentMatriz[row][col] = num;
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
                int num = currentMatriz[row][col];
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
        TextField[][] textFields = getSudokuFields();

        // Encuentra un campo vacío aleatorio
        List<int[]> emptyField = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (matriz[row][col] == 0) { // Campo vacío
                    emptyField.add(new int[]{row, col});
                }
            }
        }

        if (emptyField.isEmpty()) {
            new AlertBoxGame().showAlert("Sudoku", "¡Sin sugerencias!", "El tablero está completo.");
            return;
        }

        // Selecciona un campo vacío aleatorio
        Random random = new Random();
        int[] randomField = emptyField.get(random.nextInt(emptyField.size()));
        int fila = randomField[0];
        int col = randomField[1];

        // Buscar un número válido para esa posición
        for (int num = 1; num <= 6; num++) {
            if (game.isValidSuggestion(num, fila, col)) {
                // Sugerir el número al jugador
                textFields[fila][col].setText(String.valueOf(num));
                textFields[fila][col].setStyle("-fx-background-color: lightgreen;"); // Cambiar color como sugerencia
                new AlertBoxGame().showAlert("Sudoku", "Sugerencia", "Número sugerido en fila " + (fila+1) + ", columna " + (col+1));
                return;
            }
        }

        new AlertBoxGame().showAlert("Sudoku", "Sin sugerencias", "No se encontró un número válido para el campo seleccionado.");
    }
}