package com.example.iconnect;

import com.example.iconnect.Entities.Conversation;
import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class FriendPanel {
    @FXML
    Label usernameTF;
    @FXML
    Circle ProfileImageView;
    Conversation curr_conversation;
    User user;
    VBox friendVBox;
    Parent friendPanel;
    Boolean isChat;
    public void setData(User user, Conversation conversation, VBox friendVBox, Parent root,boolean isChat)
    {
        this.isChat = isChat;
        friendPanel = root;
        if(isChat)
        {
            this.friendVBox = friendVBox;
            curr_conversation = conversation;
        }
        this.user = user;
        usernameTF.setText(user.getUsername());
        Image image = new Image(getClass().getResourceAsStream(user.getProfileImagePath()));
        ProfileImageView.setFill(new ImagePattern(image));
    }
    public void addToConversation(MouseEvent e) throws IOException {
        if(isChat)
        {
            curr_conversation.addUser(user);
            friendVBox.getChildren().remove(friendPanel);
        }else
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root;
            fxmlLoader.setLocation(getClass().getResource("profile.fxml"));
            root = fxmlLoader.load();
            ProfilePage profilePage = fxmlLoader.getController();
            profilePage.setData(user);
            StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
            SceneTransitions.doFadeIn(StartUpPane,root);
        }
    }
}
