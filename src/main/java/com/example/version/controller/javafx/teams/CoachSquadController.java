package com.example.version.controller.javafx.teams;

import com.example.version.model.CoachManagesTeam;
import com.example.version.model.Team;
import com.example.version.service.CoachManagesTeamService;
import com.example.version.service.TeamService;
import com.example.version.utils.Alerts;
import com.example.version.utils.Stages;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class CoachSquadController implements Initializable {

    private Log log = LogFactory.getLog(getClass());


    @Autowired
    private TeamService service;
    @Autowired
    private TeamsController controller;
    @Autowired
    private CoachManagesTeamService coachManagesTeamService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Alerts alerts;
    @Autowired
    private Stages stages;


    @FXML
    private TableView<CoachManagesTeam> tblTeamCoaches;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colSurname;
    @FXML
    private TableColumn colContractStarts;
    @FXML
    private TableColumn colContractEnds;
    @FXML
    private Label lbName;

    private Team team;

    private CoachManagesTeam coachManagesTeam;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        team = controller.saveTeam();
        this.lbName.setText(team.getName());

        this.colName.setCellValueFactory(new PropertyValueFactory(("name")));
        this.colSurname.setCellValueFactory(new PropertyValueFactory(("surname")));
        this.colContractStarts.setCellValueFactory(new PropertyValueFactory(("contractStarts")));
        this.colContractEnds.setCellValueFactory(new PropertyValueFactory(("contractEnds")));

        tblTeamCoaches.setItems(FXCollections.observableArrayList(coachManagesTeamService.listCoachByTeam(team.getIdTeam())));
        stages.setDoubleClickClear(tblTeamCoaches);

    }


    public void removeCoaches(ActionEvent actionEvent) {
        coachManagesTeam = this.tblTeamCoaches.getSelectionModel().getSelectedItem();
        team = controller.saveTeam();
        log.info(team.toString());
        log.info(coachManagesTeam.toString());
        if (coachManagesTeam == null) {
            alerts.AlertError("Choose one coach first");
        } else {
            Long idCoach = coachManagesTeam.getIdCoach();
            coachManagesTeamService.delete(idCoach);
            refresh(actionEvent);
            alerts.AlertInformation(String.format("Coach: %s %s has been removed from %s",
                    coachManagesTeam.getName(), coachManagesTeam.getSurname(), team.getName()));
        }
    }


    public void back(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }

    public void refresh(ActionEvent actionEvent) {
        team = controller.saveTeam();
        tblTeamCoaches.setItems(FXCollections.observableArrayList(coachManagesTeamService.listCoachByTeam(team.getIdTeam())));
    }


}
