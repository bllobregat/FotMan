package com.example.version.controller.javafx.coaches;

import com.example.version.model.Coach;
import com.example.version.model.CoachManagesTeam;
import com.example.version.model.Team;
import com.example.version.service.CoachManagesTeamService;
import com.example.version.service.CoachService;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SetTeamCoachController implements Initializable {

    @Autowired
    private TeamService teamService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private CoachManagesTeamService coachTService;
    @Autowired
    private CoachesController controller;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Alerts alerts;
    @Autowired
    private Stages stages;


    @FXML
    private TableView<Team> tblTeams;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colDivision;
    @FXML
    private TableColumn colStadium;
    @FXML
    private Label lbName;
    @FXML
    private TextField txtContractStarts;
    @FXML
    private TextField txtContractEnds;
    @FXML
    private Label lbactual;

    private Team team;
    private Coach coach;
    private CoachManagesTeam coachManagesTeam;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        coach = controller.saveCoach();
        coachManagesTeam = coachTService.get(coach.getIdCoach());
        this.lbName.setText(String.format("%s %s", coach.getName(), coach.getSurname()));
        this.colName.setCellValueFactory(new PropertyValueFactory(("name")));
        this.colDivision.setCellValueFactory(new PropertyValueFactory(("division")));
        this.colStadium.setCellValueFactory(new PropertyValueFactory(("stadium")));

        if (coachManagesTeam == null) {
            this.lbactual.setText("Currently without Team");
        }else{
            team = teamService.get(coachManagesTeam.getIdTeam());
            this.lbactual.setText("Actual team " + team.getName());
            this.txtContractStarts.setText(coachManagesTeam.getContractStarts());
            this.txtContractEnds.setText(coachManagesTeam.getContractEnds());
        }
        tblTeams.setItems(FXCollections.observableArrayList(teamService.listALL()));
    }

    public void Confirm(ActionEvent actionEvent) {
        coach = controller.saveCoach();
        team = saveTeam();
        coachManagesTeam = coachTService.get(coach.getIdCoach());
        if (coachManagesTeam == null) {
            coachManagesTeam = new CoachManagesTeam();
            coachManagesTeam.setIdCoach(coach.getIdCoach());
            coachManagesTeam.setIdTeam(team.getIdTeam());
            coachManagesTeam.setContractStarts(txtContractStarts.getText());
            coachManagesTeam.setContractEnds(txtContractEnds.getText());
            coachTService.save(coachManagesTeam);
            alerts.AlertInformation(String.format("Coach '%s' '%s' set to coach team '%s'",
                    coach.getName(), coach.getSurname(), team.getName()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } else {
            coachManagesTeam.setIdTeam(team.getIdTeam());
            coachManagesTeam.setContractStarts(txtContractStarts.getText());
            coachManagesTeam.setContractEnds(txtContractEnds.getText());
            coachTService.update(coach.getIdCoach(), coachManagesTeam);
            alerts.AlertInformation(String.format("Coach '%s' '%s' set to coach team '%s'",
                    coach.getName(), coach.getSurname(), team.getName()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }
    }

    public Team saveTeam() {
        team = this.tblTeams.getSelectionModel().getSelectedItem();
        return team;
    }
}
