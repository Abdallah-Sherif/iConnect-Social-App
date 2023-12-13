package com.example.iconnect;

import com.example.iconnect.Entities.Comment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CommentController {
    @FXML
    private Label ContentL;

    @FXML
    private ImageView UserImageV;

    @FXML
    private Label UsernameL;
    public void setData(Comment comment)
    {
        ContentL.setText(comment.getContent());
        UsernameL.setText(comment.getUser().getUsername());
    }
}
