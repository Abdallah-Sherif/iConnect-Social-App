package com.example.iconnect;

import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SearchResultsGUI {
    @FXML
    private VBox CommentsSP;

    @FXML
    private HBox PostSelectedPanel;

    @FXML
    private VBox PostVBox;

    @FXML
    private ImageView SearchBTN;

    @FXML
    private TextField SearchBar;

    @FXML
    private VBox SearchResultsVBox;
    @FXML
    void returnToHomepage(MouseEvent event) {
        StackPane StartUpPane = (StackPane)((Node)event.getSource()).getScene().getRoot();
        StartUpPane.getChildren().remove(PostSelectedPanel);
    }

    public void Search(MouseEvent e) throws IOException {
        SearchResultsVBox.getChildren().clear();
        String result = SearchBar.getText().toLowerCase();
        if (result != null) {
            for (User user : UserManager.users) {
                if(user.equals(UserManager.curr_user)) continue;
                if (user.getUsername().toLowerCase().contains(result.toLowerCase())) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Parent root;
                    fxmlLoader.setLocation(getClass().getResource("UserSearchPanel.fxml"));
                    root = fxmlLoader.load();
                    UserSearchPanel userSearchPanel = fxmlLoader.getController();
                    userSearchPanel.setData(user,PostSelectedPanel);
                    SearchResultsVBox.getChildren().add(root);
                }
            }
        }
    }
}
