package com.example.version.controller.javafx.players;

import com.example.version.model.Player;
import com.example.version.service.PlayerService;
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
public class ContactDetailsController implements Initializable {

    @Autowired
    private PlayerService service;
    @Autowired
    private PlayersController controller;
    @Autowired
    private Alerts alerts;

    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;

    private Player player;
    private List<Player> players;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = controller.savePlayer();
        this.txtAddress.setText(player.getAddress());
        this.txtEmail.setText(player.getEmail());
        this.txtPhoneNumber.setText(player.getPhoneNumber());

    }


    public void confirm(ActionEvent actionEvent) {
        player = controller.savePlayer();
        players = service.listALL();

        if (!players.contains(player)) {
            player.setPhoneNumber(txtPhoneNumber.getText());
            player.setEmail(txtEmail.getText());
            player.setAddress(txtAddress.getText());
            service.update(player.getIdPlayer(), player);
            alerts.AlertInformation(String.format("Player: %s %s contact details has been updated",
                    player.getName(), player.getSurname()));
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }

    }
}
