package com.example.iconnect;

import com.example.iconnect.Entities.Post;
import com.example.iconnect.FileManagement.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class HomePage implements Initializable {

    @FXML
    VBox PostCardLayout;
    @FXML
    Circle ProfileImageView;
    @FXML
    Label UsernameLabel;
    private List<Post> recentPosts = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UsernameLabel.setText(UserManager.curr_user.getUsername());
        recentPosts.addAll(UserManager.getPostsToLoad());
        Collections.shuffle(recentPosts);
        try{
            for (Post post : recentPosts) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root;
                if(post.getImageData() == null)
                {
                    fxmlLoader.setLocation(getClass().getResource("PostTextOnly.fxml"));
                    root = fxmlLoader.load();
                    PostController postController = fxmlLoader.getController();
                    postController.setData(post,false);
                }
                else
                {
                    fxmlLoader.setLocation(getClass().getResource("PostImageOnly.fxml"));
                    root = fxmlLoader.load();
                    PostController postController = fxmlLoader.getController();
                    postController.setData(post,true);
                }
                PostCardLayout.getChildren().add(root);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        InputStream inputStream = getClass().getResourceAsStream(UserManager.curr_user.getProfileImagePath());
        if(inputStream != null)
        {
            Image image = new Image(inputStream);
            ProfileImageView.setFill(new ImagePattern(image));
            UserManager.curr_user_profile = image;
        }else
        {
            Image image = new Image(getClass().getResourceAsStream("user.png"));
            ProfileImageView.setFill(new ImagePattern(image));
        }
    }

    public void GoToCreatePostText(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("PostCreateText.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root,true);
    }
    public void GoToCreatePostImage(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("PostCreateImage.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root,true);
    }
    public void GoToProfilePage(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root;
        fxmlLoader.setLocation(getClass().getResource("profile.fxml"));
        root = fxmlLoader.load();
        ProfilePage profilePage = fxmlLoader.getController();
        profilePage.setData(UserManager.curr_user);
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root,true);
    }
    public void OpenSearchPanel(MouseEvent e) throws IOException {
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root;
        if(StartUpPane.getChildren().size() >= 2)
        {
            return;
        }
        fxmlLoader.setLocation(getClass().getResource("SearchResultsGUI.fxml"));
        root = fxmlLoader.load();
        StartUpPane.getChildren().add(root);
    }
    public void openNotificationPanel(MouseEvent e) throws IOException {
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root;
        if(StartUpPane.getChildren().size() >= 2)
        {
            return;
        }
        fxmlLoader.setLocation(getClass().getResource("NotificationBar.fxml"));
        root = fxmlLoader.load();
        StartUpPane.getChildren().add(root);
    }
}
