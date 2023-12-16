package com.example.iconnect;

import com.example.iconnect.Entities.Conversation;
import com.example.iconnect.Entities.Message;
import com.example.iconnect.FileManagement.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;

public class ConversationPanel {
    @FXML
    VBox MessagesVBox;
    @FXML
    TextField MessageContentTF;
    Conversation curr_conversation;
    public void setData(Conversation conversation) throws IOException {
        curr_conversation = conversation;
        loadMsgs();
    }
    private void loadMsgs() throws IOException {
        for(Message message: curr_conversation.getAllMessages())
        {
            createMsgPanel(message);
        }
    }
    private void createMsgPanel(Message message) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root;
        fxmlLoader.setLocation(getClass().getResource("Messages.fxml"));
        root = fxmlLoader.load();
        MessagePanel chatPanel = fxmlLoader.getController();
        chatPanel.setData(message);
        MessagesVBox.getChildren().add(root);
    }
    public void createNewMsg(MouseEvent e) throws IOException {
        Message message = new Message(UserManager.curr_user,MessageContentTF.getText());
        createMsgPanel(message);
        MessageContentTF.clear();
    }
    public void addUser(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root;
        fxmlLoader.setLocation(getClass().getResource("FriendsListGUI.fxml"));
        root = fxmlLoader.load();
        FriendsListGUI friendsListGUI = fxmlLoader.getController();
        friendsListGUI.setData(curr_conversation);
    }
}
