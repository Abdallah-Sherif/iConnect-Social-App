package com.example.iconnect;

import com.example.iconnect.Entities.Comment;
import com.example.iconnect.FileManagement.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class CommentController implements Initializable {
    @FXML
    private Label ContentL;

    @FXML
    private Circle UserImageV;

    @FXML
    private Label UsernameL;
    Image LikeOn;
    Image LikeOff;
    @FXML
    ImageView LikeView;
    @FXML
    Label LikeCounter;
    private Comment current_comment;
    public void setData(Comment comment)
    {
        current_comment = comment;
        ContentL.setText(comment.getContent());
        UsernameL.setText(comment.getUser().getUsername());
        Image image = new Image(getClass().getResourceAsStream(comment.getUser().getProfileImagePath()));
        UserImageV.setFill(new ImagePattern(image));
        LikeCounter.setText("Like " + current_comment.getLikes().size());
        if(comment.getLikes().contains(UserManager.curr_user))
        {
            LikeView.setImage(LikeOn);
        }else
        {
            LikeView.setImage(LikeOff);
        }
    }
    public void AddLike(MouseEvent e)
    {
        if(current_comment.getLikes().contains(UserManager.curr_user))
        {
            current_comment.removeLike(UserManager.curr_user);
            LikeView.setImage(LikeOff);

        }else
        {
            current_comment.addLike(UserManager.curr_user);
            LikeView.setImage(LikeOn);
        }
        LikeCounter.setText("Like " + current_comment.getLikes().size());
        System.out.println("liked");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LikeOn = new Image(getClass().getResourceAsStream("likeActive.png"));
        LikeOff = new Image(getClass().getResourceAsStream("like.png"));
    }
}
