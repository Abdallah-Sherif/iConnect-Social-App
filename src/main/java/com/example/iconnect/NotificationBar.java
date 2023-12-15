package com.example.iconnect;

import com.example.iconnect.Entities.FriendRequestNotification;
import com.example.iconnect.Entities.Notification;
import com.example.iconnect.Entities.Post;
import com.example.iconnect.FileManagement.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotificationBar implements Initializable {
    @FXML
    VBox NotificationVBox;

    @FXML
    private HBox PostSelectedPanel;

    @FXML
    void returnToHomepage(MouseEvent event) {
        StackPane StartUpPane = (StackPane)((Node)event.getSource()).getScene().getRoot();
        StartUpPane.getChildren().remove(PostSelectedPanel);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            for (Notification notification : UserManager.curr_user.getCurrentNotifications()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root;
                fxmlLoader.setLocation(getClass().getResource("NotificationPanel.fxml"));
                root = fxmlLoader.load();
                NotificationPanel notificationPanel = fxmlLoader.getController();
                notificationPanel.setData(notification,root,NotificationVBox);
                NotificationVBox.getChildren().add(root);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
