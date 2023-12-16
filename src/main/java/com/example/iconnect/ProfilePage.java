package com.example.iconnect;

import com.example.iconnect.Entities.Post;
import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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
    @FXML
    Label GenderL;
    @FXML
    Label BirthDateL;
    @FXML
    VBox FriendVBox;
    @FXML
    VBox MutualVBox;
    @FXML
    Button RestrictFriendBTN;
    ProfileOwner profileOwner = ProfileOwner.CurrentUser;
    //list to take user posts
    private List<Post> profilePosts=new ArrayList<>();
    public void setData(User user){
        profileUser=user;
        setButtons();
        Image image = new Image(getClass().getResourceAsStream(profileUser.getProfileImagePath()));
        nameLabel.setText(profileUser.getUsername());
        CircleImageView.setFill(new ImagePattern(image));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateString = user.getBirthdate().format(formatter);
        BirthDateL.setText(formattedDateString);
        GenderL.setText(user.getGender());
        profilePosts.addAll(profileUser.getPosts());
        Collections.shuffle(profilePosts);
        try{
            for (Post post : profilePosts) {
                if(!profileUser.equals(UserManager.curr_user))
                {
                    if(!profileUser.getFriends().contains(UserManager.curr_user) && post.getPrivacy())continue;
                    if(profileUser.getFriends().contains(UserManager.curr_user) && profileUser.getRestrictedFriends().contains(UserManager.curr_user) && post.getPrivacy()) continue;
                }

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
        SetFriends();

    }
    private void SetFriends()
    {
        FriendVBox.getChildren().clear();
        MutualVBox.getChildren().clear();
        for(User user: profileUser.getFriends())
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root;
            fxmlLoader.setLocation(getClass().getResource("friend_list.fxml"));
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FriendPanel chatPanel = fxmlLoader.getController();
            chatPanel.setData(user,null,null,root,false);
            FriendVBox.getChildren().add(root);
        }
        if(UserManager.curr_user.equals(profileUser)) return;
        for(User user: profileUser.getFriends())
        {
            if(!UserManager.curr_user.getFriends().contains(user)) continue;
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root;
            fxmlLoader.setLocation(getClass().getResource("friend_list.fxml"));
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FriendPanel chatPanel = fxmlLoader.getController();
            chatPanel.setData(user,null,null,root,false);
            MutualVBox.getChildren().add(root);
        }
    }

    private void setButtons() {
        FriendVBox.getChildren().clear();
        MutualVBox.getChildren().clear();
        SetFriends();
        if(profileUser.equals(UserManager.curr_user))
        {
            AcceptFriendBTN.setDisable(true);
            AcceptFriendBTN.setVisible(false);

            DeclineFriendBTN.setDisable(true);
            DeclineFriendBTN.setVisible(false);
            AddFriendBTN.setDisable(true);
            AddFriendBTN.setVisible(false);

            RestrictFriendBTN.setDisable(true);
            RestrictFriendBTN.setVisible(false);

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

            RestrictFriendBTN.setDisable(false);
            RestrictFriendBTN.setVisible(true);
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

            RestrictFriendBTN.setDisable(true);
            RestrictFriendBTN.setVisible(false);
        }else if(UserManager.curr_user.getSentFriendRequests().contains(profileUser))
        {
            profileOwner = ProfileOwner.FriendRecUser;
            AcceptFriendBTN.setDisable(true);
            AcceptFriendBTN.setVisible(false);

            DeclineFriendBTN.setDisable(true);
            DeclineFriendBTN.setVisible(false);

            AddFriendBTN.setDisable(true);
            AddFriendBTN.setVisible(true);

            RestrictFriendBTN.setDisable(true);
            RestrictFriendBTN.setVisible(false);
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

            RestrictFriendBTN.setDisable(true);
            RestrictFriendBTN.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void restrictFriend(ActionEvent e)
    {
        if(UserManager.curr_user.getRestrictedFriends().contains(profileUser))
        {
            UserManager.curr_user.removeRestrictedUser(profileUser);
            RestrictFriendBTN.setText("Restrict Friend!");
        }else
        {
            UserManager.curr_user.addRestrictedUser(profileUser);
            RestrictFriendBTN.setText("Un-Restrict Friend");
        }
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
}
