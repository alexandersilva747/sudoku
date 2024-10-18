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

    public void showInstructions() {
        if (!this.isShowing()) {
            this.show(); // Mostrar la ventana si no está visible
        } else {
            this.toFront(); // Si ya está abierta, llevarla al frente
        }
    }
}
