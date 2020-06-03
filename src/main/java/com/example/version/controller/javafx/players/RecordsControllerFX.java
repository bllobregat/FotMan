package com.example.version.controller.javafx.players;

import com.example.version.model.Player;
import com.example.version.model.Record;
import com.example.version.service.PlayerService;
import com.example.version.service.RecordService;
import com.example.version.utils.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class RecordsControllerFX implements Initializable {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private PlayerService playerService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private PlayersController controller;
    @Autowired
    private Alerts alerts;

    @FXML
    private Label lbName;
    @FXML
    private TextField txtSeasons;
    @FXML
    private TextField txtMatches;
    @FXML
    private TextField txtGoals;

    private Player player;
    private Record record;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = controller.savePlayer();
        this.lbName.setText(String.format("%s %s", player.getName(), player.getSurname()));
        record = recordService.get(player.getIdPlayer());
        if (record != null) {
            this.txtSeasons.setText(String.valueOf(record.getSeasons()));
            this.txtMatches.setText(String.valueOf(record.getMatches()));
            this.txtGoals.setText(String.valueOf(record.getGoals()));
        }


    }

    public void confirm(ActionEvent actionEvent) {
        player = controller.savePlayer();
        record = recordService.get(player.getIdPlayer());
        if (record == null) {
            record = new Record();
            record.setIdPlayer(player.getIdPlayer());
            record.setSeasons(Integer.parseInt(txtSeasons.getText()));
            record.setMatches(Integer.parseInt(txtMatches.getText()));
            record.setGoals((Integer.parseInt(txtGoals.getText())));
            recordService.save(record);
            alerts.AlertInformation(String.format("New Record is created for %s %s",
                    player.getName(), player.getSurname()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } else {
            record.setSeasons(Integer.parseInt(txtSeasons.getText()));
            record.setMatches(Integer.parseInt(txtMatches.getText()));
            record.setGoals((Integer.parseInt(txtGoals.getText())));
            recordService.update(player.getIdPlayer(), record);
            alerts.AlertInformation(String.format("Record has been updated for %s %s",
                    player.getName(), player.getSurname()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }
    }
}
