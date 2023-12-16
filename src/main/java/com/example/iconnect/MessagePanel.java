package com.example.iconnect;

import com.example.iconnect.Entities.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


public class MessagePanel {
    @FXML
    Label MessageL;
    @FXML
    Label UsernameL;
    Message curr_message;
    @FXML
    Circle ProfileImageView;
    @FXML
    HBox RightMarginBox;
    public void setData(Message message)
    {
        MessageL.setText(message.getContent());
        UsernameL.setText(message.getSender().getUsername());
        Image image = new Image(getClass().getResourceAsStream(message.getSender().getProfileImagePath()));
        ProfileImageView.setFill(new ImagePattern(image));
    }
}
