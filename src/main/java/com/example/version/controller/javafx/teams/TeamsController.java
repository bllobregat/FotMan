package com.example.version.controller.javafx.teams;

import com.example.version.model.Coach;
import com.example.version.model.Team;
import com.example.version.service.TeamService;
import com.example.version.utils.Alerts;
import com.example.version.utils.PrinterPDF;
import com.example.version.utils.Stages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Component
public class TeamsController implements Initializable {

    @Autowired
    private TeamService service;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Alerts alerts;
    @Autowired
    private Stages stages;
    @Autowired
    private PrinterPDF printerPDF;

    @FXML
    private TableView<Team> tblTeams;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colDivision;
    @FXML
    private TableColumn colStadium;
    @FXML
    private TextField txtFilter;

    private Team team;
    private ObservableList<Team> teams;
    private ObservableList<Team> filterTeams;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.colName.setCellValueFactory(new PropertyValueFactory(("name")));
        this.colDivision.setCellValueFactory(new PropertyValueFactory(("division")));
        this.colStadium.setCellValueFactory(new PropertyValueFactory(("stadium")));
        filterTeams = FXCollections.observableArrayList(service.listALL());
        teams = FXCollections.observableArrayList(service.listALL());
        tblTeams.setItems(FXCollections.observableArrayList(teams));
        stages.setDoubleClickClear(tblTeams);
    }

    public Team saveTeam() {
        team = this.tblTeams.getSelectionModel().getSelectedItem();
        return team;
    }


    public void addTeams(ActionEvent actionEvent) throws IOException {
        stages.setStage(applicationContext, "/fxml/teams/addTeams.fxml");

    }


    public void removeTeams(ActionEvent actionEvent) {

        team = this.tblTeams.getSelectionModel().getSelectedItem();

        if (team == null) {
            alerts.AlertError("Choose one team first");
        } else {
            Long idTeam = team.getIdTeam();
            service.delete(idTeam);
            refresh(actionEvent);
            alerts.AlertInformation(String.format("Team: %s has been deleted",
                    team.getName()));
        }

    }

    public void showteamSquad(ActionEvent actionEvent) throws IOException {

        team = this.tblTeams.getSelectionModel().getSelectedItem();

        if (team == null) alerts.AlertError("Choose one team first");
        else stages.setStage(applicationContext, "/fxml/teams/teamSquad.fxml");

    }

    public void showTactics(ActionEvent actionEvent) throws IOException {
        team = this.tblTeams.getSelectionModel().getSelectedItem();

        if (team == null) alerts.AlertError("Choose one team first");
        else stages.setStage(applicationContext, "/fxml/teams/tactics.fxml");


    }

    public void addCoach(ActionEvent actionEvent) throws IOException {
        team = this.tblTeams.getSelectionModel().getSelectedItem();

        if (team == null) alerts.AlertError("Choose one team first");
        else stages.setStage(applicationContext, "/fxml/teams/coachSquad.fxml");


    }

    public void back(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }

    public void refresh(ActionEvent actionEvent) {
        tblTeams.setItems(FXCollections.observableArrayList(service.listALL()));
    }

    public void printTeams(ActionEvent actionEvent) {
        printerPDF.printToPDF(tblTeams);
    }

    public void filterByName(KeyEvent event) {
        String filterName = this.txtFilter.getText();

        if(filterName.isEmpty()){
            this.tblTeams.setItems(teams);
        }else{
            this.filterTeams.clear();

            for (Team team: this.teams){
                if(team.getName().toLowerCase().contains(filterName.toLowerCase())){
                    this.filterTeams.add(team);
                }
            }
            this.tblTeams.setItems(filterTeams);

        }
    }
}
