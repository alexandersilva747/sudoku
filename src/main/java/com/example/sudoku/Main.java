/**
 * @autor 2343025-2724 Olman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */

package com.example.sudoku;

import com.example.sudoku.views.GameView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class to star the sudoku program
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        GameView.getInstance();
    }
}
