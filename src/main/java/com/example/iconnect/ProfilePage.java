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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

public class ProfilePage implements Initializable {
    enum ProfileOwner {
        FriendUser,
        UnknownUser,
        CurrentUser,
        FriendReqUser,
        FriendRecUser
    }
    @FXML
    Button AcceptFriendBTN;

    @FXML
    Button AddFriendBTN;

    @FXML
    Button DeclineFriendBTN;
    User profileUser;
    @FXML
    Circle CircleImageView;
    @FXML
    Label nameLabel;
    @FXML
    VBox VprofilrPost;
    ProfileOwner profileOwner = ProfileOwner.CurrentUser;
    //list to take user posts
    private List<Post> profilePosts=new ArrayList<>();
    public void setData(User user){
        profileUser=user;
        setButtons();
        Image image = new Image(getClass().getResourceAsStream(profileUser.getProfileImagePath()));
        nameLabel.setText(profileUser.getUsername());
        CircleImageView.setFill(new ImagePattern(image));
        profilePosts.addAll(profileUser.getPosts());
        Collections.shuffle(profilePosts);
        try{
            for (Post post : profilePosts) {
                if(post.getPrivacy() && !user.getFriends().contains(UserManager.curr_user)) continue;
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

    private void setButtons() {
        if(profileUser.equals(UserManager.curr_user))
        {
            AcceptFriendBTN.setDisable(true);
            AcceptFriendBTN.setVisible(false);

            DeclineFriendBTN.setDisable(true);
            DeclineFriendBTN.setVisible(false);
            AddFriendBTN.setDisable(true);
            AddFriendBTN.setVisible(false);
        }
        else if(UserManager.curr_user.getFriends().contains(profileUser))
        {
            profileOwner = ProfileOwner.FriendUser;

            AcceptFriendBTN.setDisable(true);
            AcceptFriendBTN.setVisible(false);

            DeclineFriendBTN.setDisable(true);
            DeclineFriendBTN.setVisible(false);

            AddFriendBTN.setDisable(true);
            AddFriendBTN.setVisible(true);
            AddFriendBTN.setText("Already Friends!");

        }else if(UserManager.curr_user.getReceivedFriendRequests().contains(profileUser))
        {
            profileOwner = ProfileOwner.FriendRecUser;
            AcceptFriendBTN.setDisable(false);
            AcceptFriendBTN.setVisible(true);

            DeclineFriendBTN.setDisable(false);
            DeclineFriendBTN.setVisible(true);

            AddFriendBTN.setDisable(true);
            AddFriendBTN.setVisible(false);
        }else if(UserManager.curr_user.getSentFriendRequests().contains(profileUser))
        {
            profileOwner = ProfileOwner.FriendRecUser;
            AcceptFriendBTN.setDisable(true);
            AcceptFriendBTN.setVisible(false);

            DeclineFriendBTN.setDisable(true);
            DeclineFriendBTN.setVisible(false);

            AddFriendBTN.setDisable(true);
            AddFriendBTN.setVisible(true);
            AddFriendBTN.setText("Friend Request Sent!");
        }
        else if(!UserManager.curr_user.getFriends().contains(profileUser))
        {
            profileOwner = ProfileOwner.UnknownUser;
            AcceptFriendBTN.setDisable(true);
            AcceptFriendBTN.setVisible(false);

            DeclineFriendBTN.setDisable(true);
            DeclineFriendBTN.setVisible(false);

            AddFriendBTN.setDisable(false);
            AddFriendBTN.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void returnToHomeepage(MouseEvent e) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("HomePage.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root);
    }
    public void sendFriend(ActionEvent e)
    {
        UserManager.SendFriendRequest(UserManager.curr_user,profileUser);
        profileOwner = ProfileOwner.FriendRecUser;
        setButtons();
    }
    public void AcceptFriend(ActionEvent e)
    {
        UserManager.acceptFriendRequest(profileUser,UserManager.curr_user);
        profileOwner = ProfileOwner.FriendUser;
        setButtons();
    }
    public void DeclineFriend(ActionEvent e)
    {
        UserManager.declineFriendRequest(profileUser,UserManager.curr_user);
        profileOwner = ProfileOwner.UnknownUser;
        setButtons();
    }
    public List<User> getMutualFriends(User friend)
    {
        List<User> mutualFriends=new ArrayList<>();
        for(User mutual: UserManager.curr_user.getFriends())
        {
            if (friend.getFriends().contains(mutual)){
                mutualFriends.add(mutual);
            }
        }
        return mutualFriends;
    }
}
