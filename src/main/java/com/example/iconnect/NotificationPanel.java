package com.example.iconnect;

import com.example.iconnect.Entities.Notification;
import com.example.iconnect.FileManagement.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class NotificationPanel {
    @FXML
    private Circle ProfileImageView;

    @FXML
    private Label UsernameTF;
    public void setData(Notification notification)
    {
        UsernameTF.setText(notification.getMessage());
        Image image = new Image(getClass().getResourceAsStream(notification.getSender().getProfileImagePath()));
        ProfileImageView.setFill(new ImagePattern(image));
    }
}
