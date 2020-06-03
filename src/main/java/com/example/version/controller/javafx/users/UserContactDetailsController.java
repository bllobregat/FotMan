package com.example.version.controller.javafx.users;

import com.example.version.model.User;
import com.example.version.service.UserService;
import com.example.version.utils.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class UserContactDetailsController implements Initializable {

    @Autowired
    private UserService service;
    @Autowired
    private UsersController controller;
    @Autowired
    private Alerts alerts;

    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;

    private User user;
    private List<User> users;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        user = controller.saveUser();
        this.txtAddress.setText(user.getAddress());
        this.txtEmail.setText(user.getEmail());
        this.txtPhoneNumber.setText(user.getPhoneNumber());

    }


    public void confirm(ActionEvent actionEvent) {

        user = controller.saveUser();
        users = service.listALL();

        if (!users.contains(user)) {
            user.setPhoneNumber(txtPhoneNumber.getText());
            user.setEmail(txtEmail.getText());
            user.setAddress(txtAddress.getText());
            service.update(user.getIdUser(), user);
            alerts.AlertInformation(String.format("User: %s %s contact details has been updated",
                    user.getName(), user.getSurname()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }

    }
}
