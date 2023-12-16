package com.example.iconnect;

import com.example.iconnect.Entities.Conversation;
import com.example.iconnect.Entities.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class FriendPanel {
    @FXML
    Label usernameTF;
    @FXML
    Circle ProfileImageView;
    Conversation curr_conversation;
    User user;
    VBox friendVBox;
    Parent friendPanel;
    public void setData(User user, Conversation conversation, VBox friendVBox, Parent root)
    {
        friendPanel = root;
        this.friendVBox = friendVBox;
        curr_conversation = conversation;
        this.user = user;
        usernameTF.setText(user.getUsername());
        Image image = new Image(getClass().getResourceAsStream(user.getProfileImagePath()));
        ProfileImageView.setFill(new ImagePattern(image));
    }
    public void addToConversation(MouseEvent e)
    {
        curr_conversation.addUser(user);
        friendVBox.getChildren().remove(friendPanel);
    }
}
