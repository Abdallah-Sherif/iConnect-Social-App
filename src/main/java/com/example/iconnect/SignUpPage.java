package com.example.iconnect;


import com.example.iconnect.Entities.User;
import com.example.iconnect.Entities.Verification;
import com.example.iconnect.FileManagement.UserManager;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;


public class SignUpPage {

    @FXML
    Button LoginBTN;
    @FXML
    TextField UsernameTF;
    @FXML
    PasswordField PassF;
    @FXML
    Button SignUpBTN;
    @FXML
    PasswordField PassFC;
    @FXML
    Label ErrorLabel;
    @FXML
    TextField EmailTF;
    @FXML
    RadioButton MaleRBTN;
    @FXML
    RadioButton FemaleRBTN;
    @FXML
    DatePicker DatePick;
    @FXML
    Circle ProfileImageView;
    private String ImageUrl;
    private Path currentImagePath;
    boolean alreadyExists = true;
    public void setLoginBTN(ActionEvent e) throws IOException {
        if(currentImagePath!= null & !alreadyExists)
        {
            Files.delete(currentImagePath);
        }
        Parent root = FXMLLoader.load(this.getClass().getResource("LoginPage.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root,true);
    }
    public void setSignUpBTN(ActionEvent e) throws IOException {
        String UserName = UsernameTF.getText();
        String Pass = PassF.getText();
        String PassC = PassFC.getText();
        String gender ="";
        LocalDate userBirthDate = DatePick.getValue();
        if(MaleRBTN.isSelected())
        {
            gender = "Male";
        } else if (FemaleRBTN.isSelected()) {
            gender = "Female";
        }

        //Errors
        if(UserName.isEmpty())
        {
            ErrorLabel.setText("Please enter your Full name");
            return;
        }
        if(EmailTF.getText().isEmpty())
        {
            ErrorLabel.setText("Please enter your email");
            return;
        }
        if(!Verification.checkemail(EmailTF.getText()))
        {
            ErrorLabel.setText("Please enter a valid email");
            return;
        }
        if(Pass.isEmpty())
        {
            ErrorLabel.setText("Please enter your password");
            return;
        }
        if(PassFC.getText().isEmpty())
        {
            ErrorLabel.setText("Please confirm your password");
            return;
        }
        if(gender.isEmpty())
        {
            ErrorLabel.setText("Please choose a gender");
            return;
        }
        if (userBirthDate == null)
        {
            ErrorLabel.setText("Please enter a birthdate");
            return;
        }
        if(!Pass.equals(PassC))
        {
            ErrorLabel.setText("Passwords don't match!");
            return;
        }
        if(!Verification.checkpassword((Pass)))
        {
            ErrorLabel.setText("Your password need to contain a capital letter ,small letter ,number and symbol");
            return;
        }
        User NewUser = new User(UserName,Pass,EmailTF.getText(),gender,userBirthDate,ImageUrl);
        UserManager.AddUser(NewUser);
        Parent root = FXMLLoader.load(this.getClass().getResource("LoginPage.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root,true);
        //switchToPage("HomePage");
        //homepage.initializeHomepage();
        //CureentUser = NewUser;
        //homepage.GetAllPosts();
    }
    public void getImageUrl(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String projectDirectory = System.getProperty("user.dir");

                // Define the relative path to the "PostImages" folder
                String relativePath = "src/main/resources/com/example/iconnect/ProfileImages/";

                Path from = Paths.get(selectedFile.toURI());
                Path to = Paths.get(projectDirectory, relativePath, selectedFile.getName());
                if(!Files.exists(to))
                {
                    Files.copy(from, to);
                    alreadyExists = false;
                }
                ImageUrl = "ProfileImages/" + selectedFile.getName();
                Image image = new Image(selectedFile.toURI().toString());
                ProfileImageView.setFill(new ImagePattern(image));
                currentImagePath = to;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
