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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
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
    public void setLoginBTN(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("LoginPage.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root);
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
        User NewUser = new User(UserName,Pass,EmailTF.getText(),gender,userBirthDate);
        UserManager.AddUser(NewUser);
        Parent root = FXMLLoader.load(this.getClass().getResource("LoginPage.fxml"));
        StackPane StartUpPane = (StackPane)((Node)e.getSource()).getScene().getRoot();
        SceneTransitions.doFadeIn(StartUpPane,root);
        //switchToPage("HomePage");
        //homepage.initializeHomepage();
        //CureentUser = NewUser;
        //homepage.GetAllPosts();
    }

}
