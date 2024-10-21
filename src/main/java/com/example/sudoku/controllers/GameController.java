/**
 * @autor 2343025-2724 Olman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.controllers;


import com.example.sudoku.models.Game;
import com.example.sudoku.models.alerts.AlertBoxGame;
import com.example.sudoku.views.InstructionsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Game controller class
 */
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
     * Instance of game class.
     */
    private Game game;

    //Numbers in each quadrant
    int[] quadrantNumbers = {2, 2, 2, 2, 2, 2};

    /**
     * Method to star the game
     */
    @FXML
    public void onActionStarGameButton(ActionEvent event) {
        // Show the alert
        System.out.println("ActionEvent");
        new AlertBoxGame().showAlert(
                "Sudoku",
                "This is your last warning",
                "Are you ready?"
        );

        // New sudoku array
        game = new Game(quadrantNumbers);
        int[][] matriz = game.getArrayGame();
        fillSudoku(matriz);
    }

    /**
     * Method that fill the TextField of the Sudoku with the generate values in the array.
     */
    private void fillSudoku(int[][] matriz) {
        // Get each TextField from the GridPane
        TextField[][] textFields = getSudokuFields();
        //Loop for cleaning the text fields styles.
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                textFields[row][col].setStyle(""); // This reset the style.
            }
        }

        // Fill the TextField with the array values
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (matriz[row][col] != 0) {
                    textFields[row][col].setText(String.valueOf(matriz[row][col]));
                    textFields[row][col].setEditable(false); // If is a static number, isn't editable.
                } else {
                    textFields[row][col].setText(""); // Leave the empty fields editable
                    textFields[row][col].setEditable(true); // Allow index numbers user
                }
            }
        }
    }

    /**
     * Method to get each TextField from the sudoku array .
     */
    private TextField[][] getSudokuFields() {
        // Connection the TextField from FXML with variables in this method
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

    /**
     * Method tho implement the action of the instructions button
     * @param actionEvent Represent action event
     * @throws IOException Represent input output exception
     */
    @FXML
    public void onActionGameInstructionsButton(ActionEvent actionEvent) throws IOException {
        System.out.println("ActionEvent");
        InstructionsView instructionsView = InstructionsView.getInstance();
        instructionsView.showInstructions(); // Call the method to show the window or bring in front
    }

    /**
     * Method that represent action event from the validation button
     */
    @FXML
    private int maxValidations = 40; // Maximum allowed validations.
    private int usedValidations = 0; // Counter for used validations.

    public void onActionValidateButton(ActionEvent actionEvent) {
        usedValidations++;

        if (usedValidations > maxValidations) {
            new AlertBoxGame().showAlert(
                    "Sudoku",
                    "GAME OVER",
                    "You have exceeded the maximum number of validations. Try again!"
            );
            return;
        }

        // Get the current values from the TextFields.
        TextField[][] textFields = getSudokuFields();
        int[][] currentMatriz = new int[6][6];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                String textValue = textFields[row][col].getText();
                if (!textValue.isEmpty()) {
                    try {
                        currentMatriz[row][col] = Integer.parseInt(textValue);
                    } catch (NumberFormatException e) {
                        new AlertBoxGame().showAlert("Sudoku", "Error", "Please enter valid numbers.");
                        return;
                    }
                } else {
                    new AlertBoxGame().showAlert("Sudoku", "Incomplete", "Please fill all the cells before validating.");
                    return;
                }
            }
        }

        // Get the solved array from the game class.
        int[][] solvedArray = game.getSolvedArray();

        // Compare the current matriz with the solved matriz.
        boolean isSolved = true;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (currentMatriz[row][col] != solvedArray[row][col]) {
                    isSolved = false;
                    break;
                }
            }
            if (!isSolved) break;
        }

        if (isSolved) {
            new AlertBoxGame().showAlert("Sudoku", "CONGRATULATIONS!", "YOU DID IT!");
        } else {
            new AlertBoxGame().showAlert(
                    "Sudoku",
                    "Mistake",
                    "There is a mistake in your input. Used validations: " + usedValidations + "/" + maxValidations
            );
        }
    }


    /**
     * Method that implement the action help button
     *
     */
    @FXML
    private int maxHelps = 40; // Maximum number of helps available.
    private int usedHelps = 0; // Counter for used helps.

    public void onActionHelpButton(ActionEvent actionEvent) {
        if (usedHelps >= maxHelps) {
            new AlertBoxGame().showAlert("Sudoku", "No more helps!", "You have used all available helps.");
            return;
        }

        // Get the solved array from the game class.
        int[][] solvedArray = game.getSolvedArray();
        TextField[][] textFields = getSudokuFields();

        // Find empty fields.
        List<int[]> emptyFields = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (textFields[row][col].getText().isEmpty()) {
                    emptyFields.add(new int[]{row, col});
                }
            }
        }

        if (emptyFields.isEmpty()) {
            new AlertBoxGame().showAlert("Sudoku", "No help!", "The array is full.");
            return;
        }

        // Select a random empty field.
        Random random = new Random();
        int[] randomField = emptyFields.get(random.nextInt(emptyFields.size()));
        int row = randomField[0];
        int col = randomField[1];

        // Reveal the correct number from the solved array.
        int correctNum = solvedArray[row][col];
        textFields[row][col].setText(String.valueOf(correctNum));
        textFields[row][col].setStyle("-fx-background-color: lightgreen;");

        usedHelps++;

        new AlertBoxGame().showAlert(
                "Sudoku",
                "Help",
                "Revealed number in row " + (row + 1) + ", column " + (col + 1) + ". Remaining helps: " + (maxHelps - usedHelps)
        );
    }
}