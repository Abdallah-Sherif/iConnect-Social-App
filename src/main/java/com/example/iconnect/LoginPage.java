package com.example.iconnect;

import com.example.iconnect.Entities.Post;
import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginPage {

    @FXML
    Button LoginBTN;
    @FXML
    TextField UsernameTF;
    @FXML
    PasswordField PassF;
    @FXML
    Button SignUpBTN;
    @FXML
    AnchorPane LoginPane;
    @FXML
    StackPane StartUpPane;

    private Stage stage;
    private Scene scene;
    public void setLoginBTN(ActionEvent e) throws IOException {
        String UserName = UsernameTF.getText();
        String Pass = new String(PassF.getText());
        User curr_user = UserManager.IsUser(UserName,Pass);
        UserManager.DisplayUsers();
        if(curr_user != null)
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("HomePage.fxml"));
            Parent root = loader.load();
            SceneTransitions.doFadeIn(StartUpPane,root);
        }
        else
        {
            System.out.println("not a user");
        }
    }
    public void setImageData()
    {

    }
    public void setSignUpBTN(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("SignUpPage.fxml"));
        SceneTransitions.doFadeIn(StartUpPane,root);
    }
}
