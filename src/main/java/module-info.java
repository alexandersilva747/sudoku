module com.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sudoku to javafx.fxml;
    exports com.example.sudoku;
    exports com.example.sudoku.controllers;
    opens com.example.sudoku.controllers to javafx.fxml;
}