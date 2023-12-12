package com.example.iconnect;

import com.example.iconnect.FileManagement.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import com.example.iconnect.Entities.Post;

import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML
    Label LikeLabel;
    Post Curr_post;
    public void setData(Post post,boolean isImage)
    {
        Curr_post = post;
        UsernameTF.setText(post.getAuthor().getUsername());
        if(isImage)
        {
            Image image = new Image(getClass().getResourceAsStream(post.getImageData()));
            PostImageView.setImage(image);
        }
        else
        {
            ContentTF.setText(post.getContent());
        }
    }
    public void setLikes(ActionEvent e)
    {
        if(Curr_post.getLikes().contains(UserManager.curr_user))
        {
            Curr_post.removeLike(UserManager.curr_user);
        }else
        {
            Curr_post.addLike(UserManager.curr_user);
        }
        LikeLabel.setText("Like " + Curr_post.getLikes().size());
        System.out.println("liked");
    }
}
