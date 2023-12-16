package com.example.iconnect;

import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class UserSearchPanel {
    @FXML
    private Label UsernameTF;

    @FXML
    private Circle userProfileView;
    User SearchUser;
    HBox PostSelectedPanel;
    public void setData(User user,HBox PostSelectedPanel)
    {
        this.PostSelectedPanel = PostSelectedPanel;
        SearchUser = user;
        UsernameTF.setText(user.getUsername());
        Image image = new Image(getClass().getResourceAsStream(user.getProfileImagePath()));
        userProfileView.setFill(new ImagePattern(image));
    }
    public void goToProfile(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root;
        fxmlLoader.setLocation(getClass().getResource("profile.fxml"));
        root = fxmlLoader.load();
        ProfilePage profilePage = fxmlLoader.getController();
        profilePage.setData(SearchUser);
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root);
        StartUpPane.getChildren().remove(PostSelectedPanel);
    }
}
