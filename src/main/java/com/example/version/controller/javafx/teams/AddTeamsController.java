package com.example.version.controller.javafx.teams;

import com.example.version.model.Team;
import com.example.version.service.TeamService;
import com.example.version.utils.Alerts;
import com.example.version.utils.Stages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AddTeamsController implements Initializable {

    @Autowired
    private TeamService service;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TeamsController controller;
    @Autowired
    private Alerts alerts;
    @Autowired
    private Stages stages;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDivision;
    @FXML
    private TextField txtStadium;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Team team = controller.saveTeam();
        if (team != null) {
            txtName.setText(team.getName());
            txtDivision.setText(team.getDivision());
            txtStadium.setText(team.getStadium());
        }

    }

    public void addTeams(ActionEvent actionEvent) {
        Team team = controller.saveTeam();
        if (team == null) {
            Team team1 = new Team();
            team1.setName(txtName.getText());
            team1.setDivision(txtDivision.getText());
            team1.setStadium(txtStadium.getText());
            service.save(team1);
            alerts.AlertInformation(String.format("Team: %s has been created", team1.getName()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } else {
            team.setName(txtName.getText());
            team.setDivision(txtDivision.getText());
            team.setStadium(txtStadium.getText());
            service.update(team.getIdTeam(), team);
            alerts.AlertInformation(String.format("Team: %s has been updated", team.getName()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }
    }
}
