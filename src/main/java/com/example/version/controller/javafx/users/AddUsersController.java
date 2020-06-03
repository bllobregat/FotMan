package com.example.version.controller.javafx.users;

import com.example.version.model.User;
import com.example.version.service.UserService;
import com.example.version.utils.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class AddUsersController implements Initializable {

    Log log = LogFactory.getLog(getClass());

    @FXML
    public TextField txtAge;
    @Autowired
    private UserService service;
    @Autowired
    private UsersController controller;
    @Autowired
    private Alerts alerts;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private RadioButton rbadmin;
    @FXML
    private RadioButton rbuser;
    @FXML
    private RadioButton rbEnabled;
    @FXML
    private RadioButton rbDisabled;

    private ToggleGroup radioGroup = new ToggleGroup();
    private ToggleGroup radioGroupEnabled = new ToggleGroup();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = controller.saveUser();
        if (user != null) {
            txtName.setText(user.getName());
            txtSurname.setText(user.getSurname());
            txtAge.setText(String.valueOf(user.getAge()));
            if (user.getRole().equals("admin")) rbadmin.setSelected(true);
            if (user.getRole().equals("user")) rbuser.setSelected(true);
            rbEnabled.setSelected(true);
        }

        setRadioGroup(radioGroup);
        setRadioGroupEnabled(radioGroupEnabled);
    }


    public void addUsers(ActionEvent actionEvent) {
        try {
            User user = controller.saveUser();
            if (user == null) {
                User user1 = new User();
                user1.setName(txtName.getText());
                user1.setSurname(txtSurname.getText());
                user1.setAge(Integer.parseInt(txtAge.getText()));
                user1.setRole(getSelectedToggletoString());
                user1.setEmail("");
                user1.setAddress("");
                user1.setPhoneNumber("");
                if(rbDisabled.isArmed()) user.setEnabled(false);
                service.save(user1);
                alerts.AlertInformation(String.format("User: %s %s has been created", user1.getName(), user1.getSurname()));
                controller.refresh(actionEvent);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                user.setName(txtName.getText());
                user.setSurname(txtSurname.getText());
                user.setAge(Integer.parseInt(txtAge.getText()));
                user.setRole(getSelectedToggletoString());
                if(rbDisabled.isArmed()) user.setEnabled(false);
                user.setEmail("");
                user.setAddress("");
                user.setPhoneNumber("");
                service.update(user.getIdUser(), user);
                alerts.AlertInformation(String.format("User: %s %s has been updated", user.getName(), user.getSurname()));
                controller.refresh(actionEvent);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
        } catch (NumberFormatException e) {
            alerts.AlertError("Field Age Error: Please introduce a correct number value");
        }

    }

    public String getSelectedToggletoString() {
        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        return selectedRadioButton.getText().toLowerCase();
    }


    public void setRadioGroupEnabled(ToggleGroup radioGroupEnabled) {
        rbDisabled.setToggleGroup(radioGroupEnabled);
        rbEnabled.setToggleGroup(radioGroupEnabled);
        this.radioGroupEnabled = radioGroupEnabled;
    }

    public void setRadioGroup(ToggleGroup radioGroup) {
        rbadmin.setToggleGroup(radioGroup);
        rbuser.setToggleGroup(radioGroup);
        this.radioGroup = radioGroup;
    }
}
