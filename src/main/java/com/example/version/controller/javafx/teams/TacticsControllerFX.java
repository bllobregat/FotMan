package com.example.version.controller.javafx.teams;

import com.example.version.model.Tactic;
import com.example.version.model.Team;
import com.example.version.service.TacticService;
import com.example.version.service.TeamService;
import com.example.version.utils.Alerts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TacticsControllerFX implements Initializable {

    @Autowired
    private TeamService teamService;
    @Autowired
    private TacticService tacticService;
    @Autowired
    private TeamsController controller;
    @Autowired
    private Alerts alerts;

    @FXML
    private Label lbName;
    @FXML
    private RadioButton rB442;
    @FXML
    private RadioButton rB433;
    @FXML
    private RadioButton rB541;
    @FXML
    private RadioButton rB4231;
    @FXML
    private RadioButton rB4141;
    @FXML
    private RadioButton rB352;
    @FXML
    private TextField txtType;
    @FXML
    private TextArea txtPersonalNotes;

    private Team team;
    private Tactic tactic;
    private ToggleGroup radioGroup = new ToggleGroup();

    public void setRadioGroup(ToggleGroup radioGroup) {
        rB442.setToggleGroup(radioGroup);
        rB433.setToggleGroup(radioGroup);
        rB541.setToggleGroup(radioGroup);
        rB4231.setToggleGroup(radioGroup);
        rB4141.setToggleGroup(radioGroup);
        rB352.setToggleGroup(radioGroup);
        this.radioGroup = radioGroup;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        team = controller.saveTeam();
        this.lbName.setText(team.getName());
        tactic = tacticService.get(team.getIdTeam());
        if (tactic != null) {
            this.txtType.setText(tactic.getType());
            this.txtPersonalNotes.setText(tactic.getPersonalNotes());
            String formation = tactic.getFormation();
            if (formation.equals("4 - 4 - 2")) rB442.setSelected(true);
            if (formation.equals("4 - 3 - 3")) rB433.setSelected(true);
            if (formation.equals("4 - 2 - 3 - 1")) rB4231.setSelected(true);
            if (formation.equals("4 - 1 - 4 - 1")) rB4141.setSelected(true);
            if (formation.equals("5 - 4 - 1")) rB541.setSelected(true);
            if (formation.equals("3 - 5 - 2")) rB352.setSelected(true);
        }
        setRadioGroup(radioGroup);
        setHandleToogleGroup(radioGroup);

    }

    public void confirm(ActionEvent actionEvent) {
        team = controller.saveTeam();
        tactic = tacticService.get(team.getIdTeam());

        if (tactic == null) {
            tactic = new Tactic();
            tactic.setIdTeam(team.getIdTeam());
            tactic.setType(txtType.getText());
            tactic.setPersonalNotes(txtPersonalNotes.getText());
            tactic.setFormation(getSelectedToggletoString());
            tacticService.save(tactic);
            alerts.AlertInformation("New Tactic is created for " + team.getName());
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } else {
            tactic.setType(txtType.getText());
            tactic.setPersonalNotes(txtPersonalNotes.getText());
            tactic.setFormation(getSelectedToggletoString());
            tacticService.update(team.getIdTeam(), tactic);
            alerts.AlertInformation("Tactic has been updated for " + team.getName());
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }
    }

    public String getSelectedToggletoString() {
        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        return selectedRadioButton.getText();
    }

    public void setHandleToogleGroup(ToggleGroup radioGroup) {
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (radioGroup.getSelectedToggle() != null) {
                    if (rB442.isSelected()) {
                        txtType.setText("Classic - Flexible");
                    }
                    if (rB433.isSelected()) {
                        txtType.setText("Passing - Skillful");
                    }
                    if (rB4231.isSelected()) {
                        txtType.setText("Standard - Counter Attack");
                    }
                    if (rB4141.isSelected()) {
                        txtType.setText("Midfielders Game - Flexible");
                    }
                    if (rB352.isSelected()) {
                        txtType.setText("Attack - Aggressive");
                    }
                    if (rB541.isSelected()) {
                        txtType.setText("Defensive - Disciplined");
                    }
                }
            }
        });
    }
}
