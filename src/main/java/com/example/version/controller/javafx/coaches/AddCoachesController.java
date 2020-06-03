package com.example.version.controller.javafx.coaches;

import com.example.version.model.Coach;
import com.example.version.service.CoachService;
import com.example.version.utils.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class AddCoachesController implements Initializable {

    @Autowired
    private CoachService service;
    @Autowired
    private CoachesController controller;
    @Autowired
    private Alerts alerts;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtLicence;

    private Coach coach;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Coach coach = controller.saveCoach();
        if (coach != null) {
            txtName.setText(coach.getName());
            txtSurname.setText(coach.getSurname());
            txtAge.setText(String.valueOf(coach.getIdCoach()));

        }
    }


    public void addCoaches(ActionEvent actionEvent) {
        try {
            Coach coach = controller.saveCoach();
            if (coach == null) {
                Coach coach1 = new Coach();
                coach1.setName(txtName.getText());
                coach1.setSurname(txtSurname.getText());
                coach1.setAge(Integer.parseInt(txtAge.getText()));
                coach1.setLicence(txtLicence.getText());
                coach1.setAddress("");
                coach1.setPhoneNumber("");
                coach1.setEmail("");
                service.save(coach1);
                alerts.AlertInformation(String.format("Coach: %s %s has been created", coach1.getName(), coach1.getSurname()));
                controller.refresh(actionEvent);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                coach.setName(txtName.getText());
                coach.setSurname(txtSurname.getText());
                coach.setAge(Integer.parseInt(txtAge.getText()));
                coach.setLicence(txtLicence.getText());
                service.update(coach.getIdCoach(), coach);
                alerts.AlertInformation(String.format("Coach: %s %s has been created", coach.getName(), coach.getSurname()));
                controller.refresh(actionEvent);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
        } catch (NumberFormatException e) {
            alerts.AlertError("Field Age Error: Please introduce a correct number value");
        }


    }


}
