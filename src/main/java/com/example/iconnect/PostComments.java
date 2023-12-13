package com.example.iconnect;

import com.example.iconnect.Entities.Comment;
import com.example.iconnect.Entities.Post;
import com.example.iconnect.FileManagement.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class PostComments {

    @FXML
    VBox CommentsSP;
    @FXML
    VBox PostVBox;
    @FXML
    private TextField ContentTF;

    @FXML
    private ImageView UserImageV;
    private Parent postPanel;
    private Post current_post;
    @FXML
    private AnchorPane PostSelectedPanel;

    public void setPostPanel(Parent postPanel,Post post) {
        current_post = post;
        this.postPanel = postPanel;
        PostVBox.getChildren().add(postPanel);
        loadComments();
    }
    public void CreateNewComment(ActionEvent e)
    {
        String content = ContentTF.getText();
        Comment newcomment = new Comment(current_post, UserManager.curr_user,content);
        current_post.addComment(newcomment);
        ContentTF.clear();
        loadComments();
    }
    private void loadComments()
    {
        CommentsSP.getChildren().clear();
        try{
            for(Comment comment : current_post.getComments())
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root;
                fxmlLoader.setLocation(getClass().getResource("CommentGUI.fxml"));
                root = fxmlLoader.load();
                CommentsSP.getChildren().add(root);
                CommentController commentController = fxmlLoader.getController();
                // Check if the controller is null
                if (commentController != null) {
                    commentController.setData(comment);
                } else {
                    System.err.println("Error: CommentController is null");
                }

            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void returnToHomepage(MouseEvent event)
    {
        StackPane StartUpPane = (StackPane)((Node)event.getSource()).getScene().getRoot();
        StartUpPane.getChildren().remove(PostSelectedPanel);
    }
}
