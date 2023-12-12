package com.example.iconnect;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    @FXML
    ImageView PostImageView;


    public void setData(Post post,boolean isImage)
    {
        UsernameTF.setText(post.getAuthor().getUsername());
        if(isImage)
        {
            Image image = new Image(getClass().getResourceAsStream(post.getImageData()));
            PostImageView.setImage(image);
        }else
        {
            ContentTF.setText(post.getContent());
        }
    }
}
