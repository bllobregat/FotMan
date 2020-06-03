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
import java.util.ResourceBundle;


@Component
public class AddPlayersController implements Initializable {

    @Autowired
    private PlayerService service;
    @Autowired
    private PlayersController controller;
    @Autowired
    private Alerts alerts;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtHeight;
    @FXML
    private TextField txtNationality;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player player = controller.savePlayer();
        if (player != null) {
            txtName.setText(player.getName());
            txtSurname.setText(player.getSurname());
            txtAge.setText(String.valueOf(player.getIdPlayer()));
            txtNationality.setText(player.getNationality());
            txtWeight.setText(player.getWeight());
            txtHeight.setText(player.getHeight());
        }
    }


    public void addPlayers(ActionEvent actionEvent) {
        try {
            Player player = controller.savePlayer();
            if (player == null) {
                Player player1 = new Player();
                player1.setName(txtName.getText());
                player1.setSurname(txtSurname.getText());
                player1.setAge(Integer.parseInt(txtAge.getText()));
                player1.setNationality(txtNationality.getText());
                player1.setWeight(txtWeight.getText());
                player1.setHeight(txtHeight.getText());
                player1.setPersonalNotes("");
                player1.setAddress("");
                player1.setEmail("");
                player1.setPhoneNumber("");
                service.save(player1);
                alerts.AlertInformation(String.format("Player: %s %s has been created", player1.getName(), player1.getSurname()));
                controller.refresh(actionEvent);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                player.setName(txtName.getText());
                player.setSurname(txtSurname.getText());
                player.setAge(Integer.parseInt(txtAge.getText()));
                player.setNationality(txtNationality.getText());
                player.setWeight(txtWeight.getText());
                player.setHeight(txtHeight.getText());
                service.update(player.getIdPlayer(), player);
                alerts.AlertInformation(String.format("Player: %s %s has been updated", player.getName(), player.getSurname()));
                controller.refresh(actionEvent);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
        } catch (NumberFormatException e) {
            alerts.AlertError("Field Age Error: Please introduce a correct number value");
        }
    }
}
