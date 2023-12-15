package com.example.iconnect;

import com.example.iconnect.Entities.Notification;
import com.example.iconnect.FileManagement.UserManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class NotificationPanel {
    @FXML
    private Circle ProfileImageView;

    @FXML
    private Label UsernameTF;
    VBox notificationBar;
    Parent NotificationBox;
    public void setData(Notification notification,Parent root,VBox notificationBar)
    {
        NotificationBox = root;
        this.notificationBar = notificationBar;
        UsernameTF.setText(notification.getMessage());
        Image image = new Image(getClass().getResourceAsStream(notification.getSender().getProfileImagePath()));
        ProfileImageView.setFill(new ImagePattern(image));
    }
    public void DeleteNotification(MouseEvent e)
    {
        notificationBar.getChildren().remove(NotificationBox);
    }
}
