package com.example.iconnect;

import com.example.iconnect.Entities.Conversation;
import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FriendsListGUI {

    @FXML
    VBox FriendVBox;

    @FXML
    HBox PostSelectedPanel;
    Parent FriendPanel;
    @FXML
    void returnToConversation(MouseEvent e) throws IOException {
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        StartUpPane.getChildren().remove(FriendPanel);
    }
    Conversation curr_conversation;
    public void setData(Conversation conversation,Parent FriendPanel) throws IOException {
        this.FriendPanel = FriendPanel;
        curr_conversation = conversation;
        loadFriends();


    }
    private void loadFriends() throws IOException {
        for(User user: UserManager.curr_user.getFriends())
        {
            //if(curr_conversation.getUsernamesOfParticipants().contains(UserManager.curr_user.getUsername().toLowerCase())) continue;
            FXMLLoader loader = new FXMLLoader();
            Parent root;
            loader.setLocation(getClass().getResource("friend_list.fxml"));
            root = loader.load();
            FriendPanel friendPanel = loader.getController();
            friendPanel.setData(user,curr_conversation,FriendVBox,root,true);
            FriendVBox.getChildren().add(root);
            System.out.println("hey");
        }
    }
}
