package com.example.version.controller.javafx;

import com.example.version.model.User;
import com.example.version.repository.UserRepo;
import com.example.version.service.UserService;
import com.example.version.utils.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class RegistrationController implements Initializable {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private UserService service;
    @Autowired
    private UserRepo repo;
    @Autowired
    private Alerts alerts;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtAddress;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void register(javafx.event.ActionEvent actionEvent) {
        List<String> emails = service.findUserEmails();
        try {
            User user1 = new User();
            user1.setName(txtName.getText());
            user1.setSurname(txtSurname.getText());
            user1.setAge(Integer.parseInt(txtAge.getText()));
            user1.setAddress(txtAddress.getText());
            user1.setEmail(txtEmail.getText());
            user1.setPhoneNumber(txtPhoneNumber.getText());
            user1.setRole("user");
            user1.setPassword(passwordEncoder.encode(txtPassword.getText()));
            user1.setEnabled(true);
            if (!emails.contains(txtEmail.getText())) {
                service.save(user1);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                alerts.AlertError("Email: " + txtEmail.getText() + " already in use.");
            }

        } catch (NumberFormatException e) {
            alerts.AlertError("Field Age Error: Please introduce a correct number value");
        }


    }
}
