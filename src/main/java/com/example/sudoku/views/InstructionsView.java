/**
 * @autor 2343025-2724 OLman Alexander Silva Zuñiga gr 81
 * @version 1.0
 */
package com.example.sudoku.views;


import com.example.sudoku.controllers.InstructionsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * View class InstructionsView to show the instructions view window
 */
public class InstructionsView extends Stage {

    private final InstructionsController instructionsController;

    public InstructionsView() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/instructions-view.fxml")
        );
        Parent root = loader.load();
        this.instructionsController = loader.getController();
        this.setTitle("SUDOKU");
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.getIcons().add(new Image(
                getClass().getResource("/com/example/sudoku/images/sudoku.png").toString()
        ));
        this.show();
    }

    public InstructionsController getGameController() {
        return this.instructionsController;
    }

    public static InstructionsView getInstance() throws IOException {
        if (GameViewHolder.INSTANCE == null) { //Pendiente revisar el tema del gameviewholder, ya que este pertenece al juego y no a las instrucciones
            return GameViewHolder.INSTANCE = new InstructionsView();
        } else {
            return GameViewHolder.INSTANCE;
        }
    }

    private static class GameViewHolder {
        private static InstructionsView INSTANCE;
    }

    /**
     * Method to show repeatedly with each click on instructions button.
     * Pull the window in front if was already opened.
     */
    public void showInstructions() {
        if (!this.isShowing()) {
            this.show(); // Show the window if isn't available.
        } else {
            this.toFront(); // If is available, bring in front.
        }
    }
}
