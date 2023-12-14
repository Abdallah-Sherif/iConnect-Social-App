package com.example.iconnect;

import com.example.iconnect.Entities.Post;
import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

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
}
