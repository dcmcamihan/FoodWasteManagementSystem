package com.example.foodwastemanagementsystem.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static final String CURRENCY = "₱ ";

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/foodwastemanagementsystem/views/home.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Food Market System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}