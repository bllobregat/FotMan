package com.example.version.controller.javafx.teams;

import com.example.version.model.PlayerComposeTeam;
import com.example.version.model.Team;
import com.example.version.service.PlayerComposeTeamService;
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
public class TeamSquadController implements Initializable {

    private Log log = LogFactory.getLog(getClass());


    @Autowired
    private TeamService service;
    @Autowired
    private TeamsController controller;
    @Autowired
    private PlayerComposeTeamService playerComposeTeamService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Alerts alerts;
    @Autowired
    private Stages stages;


    @FXML
    private TableView<PlayerComposeTeam> tblTeamPlayers;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colSurname;
    @FXML
    private TableColumn colNumber;
    @FXML
    private TableColumn colPosition;
    @FXML
    private TableColumn colContractStarts;
    @FXML
    private TableColumn colContractEnds;
    @FXML
    private Label lbName;

    private Team team;

    private PlayerComposeTeam playerComposeTeam;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        team = controller.saveTeam();
        this.lbName.setText(team.getName());

        this.colName.setCellValueFactory(new PropertyValueFactory(("name")));
        this.colSurname.setCellValueFactory(new PropertyValueFactory(("surname")));
        this.colNumber.setCellValueFactory(new PropertyValueFactory(("number")));
        this.colPosition.setCellValueFactory(new PropertyValueFactory(("position")));
        this.colContractStarts.setCellValueFactory(new PropertyValueFactory(("contractStarts")));
        this.colContractEnds.setCellValueFactory(new PropertyValueFactory(("contractEnds")));

        tblTeamPlayers.setItems(FXCollections.observableArrayList(playerComposeTeamService.listPlayerByTeam(team.getIdTeam())));
        stages.setDoubleClickClear(tblTeamPlayers);
    }


    public void removePlayers(ActionEvent actionEvent) {

        playerComposeTeam = this.tblTeamPlayers.getSelectionModel().getSelectedItem();
        team = controller.saveTeam();
        log.info(team.toString());
        log.info(playerComposeTeam.toString());
        if (playerComposeTeam == null) {
            alerts.AlertError("Choose one player first");
        } else {
            Long idPlayer = playerComposeTeam.getIdPlayer();
            playerComposeTeamService.delete(idPlayer);
            refresh(actionEvent);
            alerts.AlertInformation(String.format("Player: %s has been removed from %s",
                    playerComposeTeam.getName(), team.getName()));
        }

    }


    public void back(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }

    public void refresh(ActionEvent actionEvent) {
        team = controller.saveTeam();
        tblTeamPlayers.setItems(FXCollections.observableArrayList(playerComposeTeamService.listPlayerByTeam(team.getIdTeam())));
    }


}
