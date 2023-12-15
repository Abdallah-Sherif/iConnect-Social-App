package com.example.iconnect;

import com.example.iconnect.Entities.Post;
import com.example.iconnect.Entities.User;
import com.example.iconnect.PostController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ProfilePage implements Initializable {
    User profileUser;
    @FXML
    Circle CircleImageView;
    @FXML
    Label nameLabel;
    @FXML
    VBox VprofilrPost;
    //list to take user posts
    private List<Post> profilePosts=new ArrayList<>();
    public void setData(User user){
        profileUser=user;
        Image image = new Image(getClass().getResourceAsStream(profileUser.getProfileImagePath()));
        nameLabel.setText(profileUser.getUsername());
        CircleImageView.setFill(new ImagePattern(image));
        profilePosts.addAll(profileUser.getPosts());
        Collections.shuffle(profilePosts);
        try{
            for (Post post : profilePosts) {
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
                VprofilrPost.getChildren().add(root);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
