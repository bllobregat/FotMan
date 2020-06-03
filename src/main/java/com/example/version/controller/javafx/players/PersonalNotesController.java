package com.example.version.controller.javafx.players;

import com.example.version.model.Player;
import com.example.version.service.PlayerService;
import com.example.version.utils.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PersonalNotesController implements Initializable {

    @Autowired
    private PlayerService service;
    @Autowired
    private PlayersController controller;
    @Autowired
    private Alerts alerts;

    private Log log = LogFactory.getLog(getClass());


    @FXML
    private TextArea txtPersonalNotes;

    private Player player;
    private List<Player> players;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        player = controller.savePlayer();
        this.txtPersonalNotes.setText(player.getPersonalNotes());


    }


    public void confirm(ActionEvent actionEvent) {

        player = controller.savePlayer();
        players = service.listALL();

        if (!players.contains(player)) {
            player.setPersonalNotes(txtPersonalNotes.getText());
            service.update(player.getIdPlayer(), player);
            alerts.AlertInformation(String.format("Player: %s %s personal notes has been updated",
                    player.getName(), player.getSurname()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }

    }
}
