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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class PostCreationGUI implements Initializable {
    @FXML
    private ListView<User> AvailableUsersLV;

    @FXML
    private TextField ContentTF;

    @FXML
    private Button PostBTN;

    @FXML
    private VBox PostPanel;

    @FXML
    private CheckBox PrivacyCheckBox;
    @FXML
    ImageView PostImageView;
    private String ImageUrl;
    private Path currentImagePath;
    boolean alreadyExists = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAvailabelUsers();
    }

    public void CreateAPost(ActionEvent e) throws IOException {

        if(ImageUrl == null)
        {
            Post post = new Post(ContentTF.getText(),UserManager.curr_user,PrivacyCheckBox.isSelected(),null,AvailableUsersLV.getItems());
            UserManager.curr_user.addPost(post);
        }else
        {
            Post post = new Post(null,UserManager.curr_user,PrivacyCheckBox.isSelected(),ImageUrl,AvailableUsersLV.getItems());
            UserManager.curr_user.addPost(post);
        }
        Parent root = FXMLLoader.load(this.getClass().getResource("HomePage.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root,true);
    }
    private void getAvailabelUsers()
    {
        AvailableUsersLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for(User user : UserManager.curr_user.getFriends())
        {
            AvailableUsersLV.getItems().add(user);
        }
    }

    public void setImageData(ActionEvent e)
    {
        chooseProfilePicture();
    }
    public void returnToHomepage(ActionEvent e) throws IOException {
        if(currentImagePath!= null & !alreadyExists)
        {
            Files.delete(currentImagePath);
        }
        Parent root = FXMLLoader.load(this.getClass().getResource("HomePage.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root,true);
    }
    private String chooseProfilePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");

        // ... other file chooser setup code ...

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                Path from = Paths.get(selectedFile.toURI());
                Path to = Paths.get("E:\\Java Projects\\iConnect\\src\\main\\resources\\com\\example\\iconnect\\PostImages\\" +selectedFile.getName());
                if(!Files.exists(to))
                {
                    Files.copy(from, to);
                    alreadyExists = false;
                }
                ImageUrl = "PostImages/" + selectedFile.getName();
                Image image = new Image(selectedFile.toURI().toString());
                PostImageView.setImage(image);
                currentImagePath = to;
            } catch (IOException ex) {
                ex.printStackTrace(); // Handle the exception appropriately
            }
        }
        return ImageUrl;
    }
}
