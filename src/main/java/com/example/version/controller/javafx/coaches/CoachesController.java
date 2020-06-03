package com.example.version.controller.javafx.coaches;

import com.example.version.model.Coach;
import com.example.version.model.Player;
import com.example.version.service.CoachService;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CoachesController implements Initializable {


    @Autowired
    private CoachService service;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Alerts alerts;
    @Autowired
    private Stages stages;
    @Autowired
    private PrinterPDF printerPDF;

    @FXML
    private TableView<Coach> tblCoaches;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colSurname;
    @FXML
    private TableColumn colAge;
    @FXML
    private TableColumn colLicence;
    @FXML
    private TextField txtFilter;

    private Coach coach;
    private ObservableList<Coach> coaches;
    private ObservableList<Coach> filterCoaches;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.colName.setCellValueFactory(new PropertyValueFactory(("name")));
        this.colSurname.setCellValueFactory(new PropertyValueFactory(("surname")));
        this.colAge.setCellValueFactory(new PropertyValueFactory(("age")));
        this.colLicence.setCellValueFactory(new PropertyValueFactory(("licence")));

        filterCoaches = FXCollections.observableArrayList(service.listALL());
        coaches = FXCollections.observableArrayList(service.listALL());
        tblCoaches.setItems(FXCollections.observableArrayList(coaches));
        stages.setDoubleClickClear(tblCoaches);
    }

    public void addCoaches(ActionEvent actionEvent) throws IOException {
        stages.setStage(applicationContext, "/fxml/coaches/addCoaches.fxml");

    }

    public void removeCoaches(ActionEvent actionEvent) {
        coach = this.tblCoaches.getSelectionModel().getSelectedItem();


        if (coach == null) {
            alerts.AlertError("Choose one coach first");
        } else {
            Long idCoach = coach.getIdCoach();
            service.delete(idCoach);
            refresh(actionEvent);
            alerts.AlertInformation(String.format("Coach: %s %s has been deleted",
                    coach.getName(), coach.getSurname()));
        }
    }

    public void showContactDetails(ActionEvent actionEvent) throws IOException {

        coach = this.tblCoaches.getSelectionModel().getSelectedItem();

        if (coach == null) alerts.AlertError("Choose one coach first");
        else stages.setStage(applicationContext, "/fxml/coaches/contactDetails.fxml");
    }

    public void refresh(ActionEvent actionEvent) {
        tblCoaches.setItems(FXCollections.observableArrayList(service.listALL()));
    }

    public void back(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public Coach saveCoach() {
        coach = this.tblCoaches.getSelectionModel().getSelectedItem();
        return coach;
    }

    public void setcoachTeam(ActionEvent actionEvent) throws IOException {

        coach = this.tblCoaches.getSelectionModel().getSelectedItem();

        if (coach == null) alerts.AlertError("Choose one coach first");
        else stages.setStage(applicationContext, "/fxml/coaches/setTeam.fxml");
    }

    public void printCoaches(ActionEvent actionEvent) {
        printerPDF.printToPDF(tblCoaches);
    }

    public void filterByName(KeyEvent event) {
        String filterName = this.txtFilter.getText();

        if(filterName.isEmpty()){
            this.tblCoaches.setItems(coaches);
        }else{
            this.filterCoaches.clear();

            for (Coach coach: this.coaches){
                if(coach.getName().toLowerCase().contains(filterName.toLowerCase())){
                    this.filterCoaches.add(coach);
                }
            }
            this.tblCoaches.setItems(filterCoaches);

        }
    }
}
