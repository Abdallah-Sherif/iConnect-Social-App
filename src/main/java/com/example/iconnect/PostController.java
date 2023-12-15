package com.example.iconnect;

import com.example.iconnect.FileManagement.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import com.example.iconnect.Entities.Post;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PostController implements Initializable {

    @FXML
    Circle ProfileImage;
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
    @FXML
    ImageView LikeIcon;
    Post Curr_post;
    Image LikeOn;
    Image LikeOff;
    @FXML
    Label CommentLabel;
    boolean isImage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LikeOn = new Image(getClass().getResourceAsStream("likeActive.png"));
        LikeOff = new Image(getClass().getResourceAsStream("like.png"));
    }
    public void setData(Post post,boolean isImage)
    {
        this.isImage = isImage;
        Curr_post = post;
        UsernameTF.setText(post.getAuthor().getUsername());
        Image imagee = new Image(getClass().getResourceAsStream(post.getAuthor().getProfileImagePath()));
        ProfileImage.setFill(new ImagePattern(imagee));
        if(isImage)
        {
            Image image = new Image(getClass().getResourceAsStream(post.getImageData()));
            PostImageView.setImage(image);
        }
        else
        {
            ContentTF.setText(post.getContent());
        }
        if(post.getLikes().contains(UserManager.curr_user))
        {
            LikeIcon.setImage(LikeOn);
        }else
        {
            LikeIcon.setImage(LikeOff);
        }
        LikeLabel.setText("Like " + Curr_post.getLikes().size());
        CommentLabel.setText("Comment " + Curr_post.getComments().size());
    }
    public void setLikes(ActionEvent e)
    {
        if(Curr_post.getLikes().contains(UserManager.curr_user))
        {
            Curr_post.removeLike(UserManager.curr_user);
            LikeIcon.setImage(LikeOff);

        }else
        {
            Curr_post.addLike(UserManager.curr_user);
            LikeIcon.setImage(LikeOn);
        }
        LikeLabel.setText("Like " + Curr_post.getLikes().size());
        System.out.println("liked");
    }
    public void OpenComments(ActionEvent e) throws IOException {
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        if(StartUpPane.getChildren().size() >= 2) return;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root;
        fxmlLoader.setLocation(getClass().getResource("PostComments.fxml"));
        root = fxmlLoader.load();
        StartUpPane.getChildren().add(root);
        PostComments postComments = fxmlLoader.getController();
        FXMLLoader fxmlLoaderPost = new FXMLLoader();
        Parent rootPost;
        if(Curr_post.getImageData() == null)
        {
            fxmlLoaderPost.setLocation(getClass().getResource("PostTextOnly.fxml"));
            rootPost = fxmlLoaderPost.load();
            PostController postController = fxmlLoaderPost.getController();
            postController.setData(Curr_post,false);
        }
        else
        {
            fxmlLoaderPost.setLocation(getClass().getResource("PostImageOnly.fxml"));
            rootPost = fxmlLoaderPost.load();
            PostController postController = fxmlLoaderPost.getController();
            postController.setData(Curr_post,true);
        }
        postComments.setPostPanel(rootPost,Curr_post,this);
    }
}
