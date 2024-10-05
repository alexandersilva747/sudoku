/**
 * @autor 2343025-2724 OLman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.controllers;


import com.example.sudoku.models.Game;
import com.example.sudoku.models.alerts.AlertBox;
import com.example.sudoku.views.GameView;
import com.example.sudoku.views.InstructionsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class GameController {
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
        new AlertBox().showAlert(
                "Sudoku",
                "This is your last warning",
                "Are you ready?"
        );
    }
    @FXML
    public void onActionGameInstructionsButton(ActionEvent actionEvent) throws IOException{
        InstructionsView.getInstance();
    }
    @FXML
    public void onActionValidateButton(ActionEvent actionEvent) {
    }
    @FXML
    public void onActionHelpButton(ActionEvent actionEvent) {
    }
}