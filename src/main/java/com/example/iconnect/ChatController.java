package com.example.iconnect;

import com.example.iconnect.Entities.Conversation;
import com.example.iconnect.FileManagement.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    @FXML
    private VBox ChatsVBox;
    @FXML
    TextField ChatNameTF;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Conversation conversation : UserManager.chats)
        {
            if(!conversation.getUsersOfConversation().contains(UserManager.curr_user)) {continue;}
            try {
                ConversationPanelCreate(conversation);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void addChat(MouseEvent e) throws IOException {
        Conversation newconversation = new Conversation(UserManager.curr_user,ChatNameTF.getText());
        ConversationPanelCreate(newconversation);
    }
    private void ConversationPanelCreate(Conversation conversation) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root;
        fxmlLoader.setLocation(getClass().getResource("UserOfChat.fxml"));
        root = fxmlLoader.load();
        ChatPanel chatPanel = fxmlLoader.getController();
        chatPanel.setData(conversation);
        ChatsVBox.getChildren().add(root);
    }
}
