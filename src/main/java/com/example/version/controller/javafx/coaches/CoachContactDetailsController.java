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
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CoachContactDetailsController implements Initializable {

    @Autowired
    private CoachService service;
    @Autowired
    private CoachesController controller;
    @Autowired
    private Alerts alerts;

    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;

    private Coach coach;
    private List<Coach> coaches;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        coach = controller.saveCoach();
        this.txtAddress.setText(coach.getAddress());
        this.txtEmail.setText(coach.getEmail());
        this.txtPhoneNumber.setText(coach.getPhoneNumber());

    }


    public void confirm(ActionEvent actionEvent) {

        coach = controller.saveCoach();
        coaches = service.listALL();

        if (!coaches.contains(coach)) {
            coach.setPhoneNumber(txtPhoneNumber.getText());
            coach.setEmail(txtEmail.getText());
            coach.setAddress(txtAddress.getText());
            service.update(coach.getIdCoach(), coach);
            alerts.AlertInformation(String.format("Coach: %s %s contact details has been updated",
                    coach.getName(), coach.getSurname()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }

    }
}
