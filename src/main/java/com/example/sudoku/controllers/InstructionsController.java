/**
 * @autor 2343025-2724 OLman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.controllers;

import com.example.sudoku.views.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class InstructionsController {
    public TextArea textAreaInstructions;

    @FXML
    public void onActiontextAreaInstructions(MouseEvent mouseEvent) {

        //TextArea.setEditable(false);
        System.out.println("MouseEvent");
    }
}