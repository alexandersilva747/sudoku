/**
 * @autor 2343025-2724 Olman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * View class GameView to show the main game window.
 */
public class GameView extends Stage{
    public GameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/game-view.fxml")
        );
        Parent root = loader.load();
        this.setTitle("SUDOKU");
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.getIcons().add(new Image(
                getClass().getResource("/com/example/sudoku/images/sudoku.png").toString()
        ));
        this.show();
    }

    public static GameView getInstance() throws IOException {
        if (WelcomeViewHolder.INSTANCE == null) {
            return WelcomeViewHolder.INSTANCE = new GameView();
        } else {
            return WelcomeViewHolder.INSTANCE;
        }
    }

    private static class WelcomeViewHolder {
        private static GameView INSTANCE;
    }
}
