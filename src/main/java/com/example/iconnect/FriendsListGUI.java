package com.example.iconnect;

import com.example.iconnect.Entities.Conversation;
import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class FriendsListGUI {
    @FXML
    ListView<User> FriendListView;

    @FXML
    HBox PostSelectedPanel;

    @FXML
    void returnToConversation(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root;
        fxmlLoader.setLocation(getClass().getResource("Conversation.fxml"));
        root = fxmlLoader.load();
        ConversationPanel conversationPanel = fxmlLoader.getController();
        conversationPanel.setData(curr_conversation);
    }
    Conversation curr_conversation;
    public void setData(Conversation conversation)
    {
        curr_conversation = conversation;
        loadFriends();
        FriendListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observableValue, User user, User t1) {
                curr_conversation.addUser(FriendListView.getSelectionModel().getSelectedItem());
            }
        });

    }
    private void loadFriends()
    {
        for(User user: UserManager.curr_user.getFriends())
        {
            if(curr_conversation.getUsersOfConversation().contains(user)) continue;
            FriendListView.getItems().add(user);
        }
    }
}
