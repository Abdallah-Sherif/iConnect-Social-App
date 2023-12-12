package com.example.iconnect;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import com.example.iconnect.Entities.Post;

public class PostController {

    @FXML
    ImageView ProfileImage;
    @FXML
    Label UsernameTF;
    @FXML
    Label ContentTF;
    @FXML
    VBox PostPanel;

    public void setData(Post post)
    {
        ProfileImage.setImage(post.getImageData());
        UsernameTF.setText(post.getAuthor().getUsername());
        ContentTF.setText(post.getContent());
    }
}
