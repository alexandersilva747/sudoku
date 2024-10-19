/**
 * @autor 2343025-2724 OLman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.controllers;

import com.example.sudoku.views.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * Instructions controller class
 */
public class InstructionsController {
    public TextArea textAreaInstructions;

    /**
     * Method to show mouse event
     * @param mouseEvent represent mouse event
     */
    @FXML
    public void onActionTextAreaInstructions(MouseEvent mouseEvent) {
        System.out.println("MouseEvent");
        textAreaInstructions.setEditable(false);
    }

    /**
     * Methos to show key event
     * @param keyEvent represent key event
     */
    @FXML
    public void onKeyPressedTextAreaInstructions(KeyEvent keyEvent) {
        System.out.println("KeyEvent");
        textAreaInstructions.setEditable(false);
    }
}