package com.example.iconnect;

import com.example.iconnect.Entities.Post;
import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    VBox PostCardLayout;
    private User current_user = UserManager.curr_user;
    private List<Post> recentPosts = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(User user : UserManager.users)
        {
            user.createPost("Hii",user,false);
        }
        recentPosts.addAll(UserManager.getPostsToLoad());
        try{
            for (Post post : recentPosts) {
                //FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setLocation(getClass().getResource("PostTextOnly.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PostTextOnly.fxml"));
                Parent root = fxmlLoader.load();
                PostController postController = fxmlLoader.getController();
                postController.setData(post);
                PostCardLayout.getChildren().add(root);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
