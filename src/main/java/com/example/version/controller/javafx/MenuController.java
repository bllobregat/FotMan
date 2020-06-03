package com.example.version.controller.javafx;

import com.example.version.service.UserService;
import com.example.version.utils.Stages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuController implements Initializable {

    @Autowired
    private UserService userService;
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Autowired
    private Stages stages;

    @FXML
    private Button btnPlayers;
    @FXML
    private Button btnTeam;
    @FXML
    private Button btnCoaches;
    @FXML
    private Button btnSignout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void goPlayers(ActionEvent actionEvent) throws IOException {
        stages.setStage(applicationContext, "/fxml/players/players.fxml");
    }

    public void goTeams(ActionEvent actionEvent) throws IOException {
        stages.setStage(applicationContext, "/fxml/teams/teams.fxml");
    }

    public void goCoaches(ActionEvent actionEvent) throws IOException {
        stages.setStage(applicationContext, "/fxml/coaches/coaches.fxml");
    }

    public void signOut(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }
}
