package com.example.iconnect;

import com.example.iconnect.FileManagement.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root,1280,720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event ->
        {
            event.consume();
            UserManager.saveUsers();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You're about to logout!");
            alert.setContentText("Do you want to save before exiting?");
            if(alert.showAndWait().get() == ButtonType.OK)
            {
                stage.close();
            }
        });
        UserManager.loadUsers();
    }

    public static void main(String[] args) {
        launch();
    }
}