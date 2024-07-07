package com.example.foodwastemanagementsystem.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void shopping(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/foodwastemanagementsystem/views/market.fxml");
    }

    @FXML
    public void records(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/foodwastemanagementsystem/views/records.fxml");
    }

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        // Get the stage and close it
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

