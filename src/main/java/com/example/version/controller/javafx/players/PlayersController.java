package com.example.version.controller.javafx.players;

import com.example.version.model.Person;
import com.example.version.model.Player;
import com.example.version.service.PlayerService;
import com.example.version.utils.Alerts;
import com.example.version.utils.PrinterPDF;
import com.example.version.utils.Stages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.util.LambdaSafe;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PlayersController implements Initializable {


    @FXML
    public TableView<Player> tblPlayers;
    @Autowired
    private PlayerService service;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Alerts alerts;
    @Autowired
    private Stages stages;
    @Autowired
    private PrinterPDF printerPDF;

    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colSurname;
    @FXML
    private TableColumn colAge;
    @FXML
    private TableColumn colHeight;
    @FXML
    private TableColumn colWeight;
    @FXML
    private TableColumn colNationality;
    @FXML
    private TextField txtFilter;

    private Player player;
    private ObservableList<Player> players;
    private ObservableList<Player> filterPlayers;


    public void initAttributtes(Player player, Long idPlayer) {
        player = this.tblPlayers.getSelectionModel().getSelectedItem();
        idPlayer = player.getIdPlayer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.colName.setCellValueFactory(new PropertyValueFactory(("name")));
        this.colSurname.setCellValueFactory(new PropertyValueFactory(("surname")));
        this.colAge.setCellValueFactory(new PropertyValueFactory(("age")));
        this.colHeight.setCellValueFactory(new PropertyValueFactory(("height")));
        this.colWeight.setCellValueFactory(new PropertyValueFactory(("weight")));
        this.colNationality.setCellValueFactory(new PropertyValueFactory(("nationality")));

        filterPlayers = FXCollections.observableArrayList(service.listALL());
        players = FXCollections.observableArrayList(service.listALL());
        tblPlayers.setItems(FXCollections.observableArrayList(players));
        stages.setDoubleClickClear(tblPlayers);
    }

    @FXML
    public void addPlayers(ActionEvent actionEvent) throws IOException {
        stages.setStage(applicationContext, "/fxml/players/addPlayers.fxml");

    }


    public Player savePlayer() {
        player = this.tblPlayers.getSelectionModel().getSelectedItem();
        return player;
    }

    public void filterByName(KeyEvent event){
        String filterName = this.txtFilter.getText();

        if(filterName.isEmpty()){
            this.tblPlayers.setItems(players);
        }else{
            this.filterPlayers.clear();

            for (Player player: this.players){
                if(player.getName().toLowerCase().contains(filterName.toLowerCase())){
                    this.filterPlayers.add(player);
                }
            }
            this.tblPlayers.setItems(filterPlayers);

        }
    }

    public void removePlayers(ActionEvent actionEvent) {

        player = this.tblPlayers.getSelectionModel().getSelectedItem();
        if (player == null) {
            alerts.AlertError("Choose one player first");
        } else {
            Long idPlayer = player.getIdPlayer();
            service.delete(idPlayer);
            refresh(actionEvent);
            alerts.AlertInformation(String.format("Player: %s %s has been deleted",
                    player.getName(), player.getSurname()));
        }
    }

    public void showRecords(ActionEvent actionEvent) throws IOException {

        player = this.tblPlayers.getSelectionModel().getSelectedItem();

        if (player == null) alerts.AlertError("Choose one player first");
        else stages.setStage(applicationContext, "/fxml/players/records.fxml");

    }

    public void showTrainings(ActionEvent actionEvent) throws IOException {

        player = this.tblPlayers.getSelectionModel().getSelectedItem();

        if (player == null) alerts.AlertError("Choose one player first");
        else stages.setStage(applicationContext, "/fxml/players/training.fxml");

    }

    public void showPersonalNotes(ActionEvent actionEvent) throws IOException {

        player = this.tblPlayers.getSelectionModel().getSelectedItem();

        if (player == null) alerts.AlertError("Choose one player first");
        else stages.setStage(applicationContext, "/fxml/players/personalNotes.fxml");


    }

    public void showContactDetails(ActionEvent actionEvent) throws IOException {

        player = this.tblPlayers.getSelectionModel().getSelectedItem();

        if (player == null) alerts.AlertError("Choose one player first");
        else stages.setStage(applicationContext, "/fxml/players/contactDetails.fxml");
    }


    public void setTeamPlayer(ActionEvent actionEvent) throws IOException {
        player = this.tblPlayers.getSelectionModel().getSelectedItem();

        if (player == null) alerts.AlertError("Choose one player first");
        else stages.setStage(applicationContext, "/fxml/players/setTeamPlayer.fxml");
    }

    public void refresh(ActionEvent actionEvent) {
        tblPlayers.setItems(FXCollections.observableArrayList(service.listALL()));
    }

    public void back(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void printPlayers(ActionEvent actionEvent) {
        printerPDF.printToPDF(tblPlayers);
    }


}
