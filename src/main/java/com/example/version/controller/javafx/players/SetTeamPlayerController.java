package com.example.version.controller.javafx.players;

import com.example.version.model.Player;
import com.example.version.model.PlayerComposeTeam;
import com.example.version.model.Team;
import com.example.version.service.PlayerComposeTeamService;
import com.example.version.service.PlayerService;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SetTeamPlayerController implements Initializable {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerComposeTeamService playerComposeTeamService;
    @Autowired
    private PlayersController controller;
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
    private Label lbName;
    @FXML
    private TextField txtContractStarts;
    @FXML
    private TextField txtContractEnds;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtPosition;
    @FXML
    private Label lbActual;

    private Team team;
    private Player player;
    private PlayerComposeTeam composeTeam;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        player = controller.savePlayer();
        composeTeam = playerComposeTeamService.get(player.getIdPlayer());
        this.lbName.setText(String.format("%s %s", player.getName(), player.getSurname()));
        this.colName.setCellValueFactory(new PropertyValueFactory(("name")));
        this.colDivision.setCellValueFactory(new PropertyValueFactory(("division")));
        if (composeTeam == null) {
            this.lbActual.setText("Currently without Team");
        }else {
            team = teamService.get(composeTeam.getIdTeam());
            this.lbActual.setText("Actual team " + team.getName());
            this.txtNumber.setText(String.valueOf(composeTeam.getNumber()));
            this.txtPosition.setText(composeTeam.getPosition());
            this.txtContractStarts.setText(composeTeam.getContractStarts());
            this.txtContractEnds.setText(composeTeam.getContractEnds());
        }
        tblTeams.setItems(FXCollections.observableArrayList(teamService.listALL()));
    }

    public void Confirm(ActionEvent actionEvent) {
        try {
            player = controller.savePlayer();
            team = saveTeam();
            composeTeam = playerComposeTeamService.get(player.getIdPlayer());
            List<PlayerComposeTeam> composeTeamList = playerComposeTeamService.listALL();
            if (composeTeam == null) {
                composeTeam = new PlayerComposeTeam();
                composeTeam.setIdTeam(team.getIdTeam());
                composeTeam.setIdPlayer(player.getIdPlayer());
                composeTeam.setContractStarts(txtContractStarts.getText());
                composeTeam.setContractEnds(txtContractEnds.getText());
                composeTeam.setNumber(Integer.parseInt(txtNumber.getText()));
                composeTeam.setPosition(txtPosition.getText());
                log.info(composeTeam.toString());
                playerComposeTeamService.save(composeTeam);
                alerts.AlertInformation(String.format("Player %s %s set to play in team %s",
                        player.getName(), player.getSurname(), team.getName()));
                controller.refresh(actionEvent);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                composeTeam.setIdTeam(team.getIdTeam());
                composeTeam.setIdPlayer(player.getIdPlayer());
                composeTeam.setContractStarts(txtContractStarts.getText());
                composeTeam.setContractEnds(txtContractEnds.getText());
                composeTeam.setNumber(Integer.parseInt(txtNumber.getText()));
                composeTeam.setPosition(txtPosition.getText());
                playerComposeTeamService.update(player.getIdPlayer(), composeTeam);
                alerts.AlertInformation(String.format("Player %s %s set to play in  team %s",
                        player.getName(), player.getSurname(), team.getName()));
                controller.refresh(actionEvent);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
        } catch (NumberFormatException e) {
            alerts.AlertError("Field Number Error: Please introduce a correct number value");
        }

    }

    public Team saveTeam() {
        team = this.tblTeams.getSelectionModel().getSelectedItem();
        return team;
    }
}
