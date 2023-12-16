package com.example.iconnect;

import com.example.iconnect.Entities.Conversation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ChatPanel {

    @FXML
    Label ChatNameTF;
    Conversation curr_conversation;
    public void setData(Conversation conversation)
    {
        ChatNameTF.setText(conversation.getNameOfChat());
        curr_conversation = conversation;
    }
    public void openChat(MouseEvent e) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("Conversation.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root);
    }
}
