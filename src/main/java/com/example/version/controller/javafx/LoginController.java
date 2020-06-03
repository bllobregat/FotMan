package com.example.version.controller.javafx;

import com.example.version.model.User;
import com.example.version.service.UserService;
import com.example.version.utils.Alerts;
import com.example.version.utils.Stages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class LoginController implements Initializable {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserService userService;
    @Autowired
    private Stages stages;
    @Autowired
    private Alerts alerts;

    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private Label lblLogin;


    public String getPassword() {
        return password.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    @FXML
    public void login(ActionEvent actionEvent) throws IOException {
        if (userService.authenticate(getEmail(), getPassword()).equals("user")) {
            stages.setStage(applicationContext, "/fxml/menu.fxml");
        } else if (userService.authenticate(getEmail(), getPassword()).equals("admin")) {
            stages.setStage(applicationContext, "/fxml/users/users.fxml");
        } else {
            lblLogin.setText("Incorrect email or password");
        }
        email.setText("");
        password.setText("");
    }


    @FXML
    public void register(ActionEvent actionEvent) throws IOException {
        stages.setStage(applicationContext, "/fxml/register.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
