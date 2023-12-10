package com.example.iconnect;

import com.example.iconnect.Entities.User;
import com.example.iconnect.FileManagement.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPage {

    @FXML
    Button LoginBTN;
    @FXML
    TextField UsernameTF;
    @FXML
    PasswordField PassF;
    @FXML
    Button SignUpBTN;
    public void setLoginBTN(ActionEvent e)
    {
        String UserName = UsernameTF.getText();
        String Pass = new String(PassF.getText());
        User curr_user = UserManager.IsUser(UserName,Pass);
        UserManager.DisplayUsers();
        if(curr_user != null)
        {
            System.out.println("LoggedIn!");
        }
        else
        {
            System.out.println("not a user");
        }
    }

}
