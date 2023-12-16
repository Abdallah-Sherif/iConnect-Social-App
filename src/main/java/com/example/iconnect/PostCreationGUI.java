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

public class PostCreationGUI {

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


    public void CreateAPost(ActionEvent e) throws IOException {

        if(ImageUrl == null)
        {
            Post post = new Post(ContentTF.getText(),UserManager.curr_user,PrivacyCheckBox.isSelected(),null);
            UserManager.curr_user.addPost(post);
        }else
        {
            Post post = new Post(null,UserManager.curr_user,PrivacyCheckBox.isSelected(),ImageUrl);
            UserManager.curr_user.addPost(post);
        }
        Parent root = FXMLLoader.load(this.getClass().getResource("HomePage.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root);
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
        SceneTransitions.doFadeIn(StartUpPane,root);
    }
    private String chooseProfilePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String projectDirectory = System.getProperty("user.dir");

                // Define the relative path to the "PostImages" folder
                String relativePath = "src/main/resources/com/example/iconnect/PostImages/";

                Path from = Paths.get(selectedFile.toURI());
                Path to = Paths.get(projectDirectory, relativePath, selectedFile.getName());
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
                ex.printStackTrace();
            }
        }
        return ImageUrl;
    }
}
