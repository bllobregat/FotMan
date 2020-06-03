package com.example.version.controller.javafx.users;

import com.example.version.model.Coach;
import com.example.version.model.Team;
import com.example.version.model.User;
import com.example.version.service.UserService;
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
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UsersController implements Initializable {

    @Autowired
    private UserService service;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Alerts alerts;
    @Autowired
    private Stages stages;
    @Autowired
    private PrinterPDF printer;


    @FXML
    private TableView<User> tblUsers;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colSurname;
    @FXML
    private TableColumn colRole;
    @FXML
    private TextField txtFilter;

    private User user;
    private ObservableList<User> users;
    private ObservableList<User> filterUsers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.colName.setCellValueFactory(new PropertyValueFactory(("name")));
        this.colSurname.setCellValueFactory(new PropertyValueFactory(("surname")));
        this.colRole.setCellValueFactory(new PropertyValueFactory(("role")));
        filterUsers = FXCollections.observableArrayList(service.listALL());
        users = FXCollections.observableArrayList(service.listALL());
        tblUsers.setItems(users);
        stages.setDoubleClickClear(tblUsers);
    }

    public void addUsers(ActionEvent actionEvent) throws IOException {
        stages.setStage(applicationContext, "/fxml/users/addUsers.fxml");

    }

    public void removeUsers(ActionEvent actionEvent) {
        user = this.tblUsers.getSelectionModel().getSelectedItem();
        if (user == null) {
            alerts.AlertError("Choose one user first");
        } else {
            Long idUser = user.getIdUser();
            service.delete(idUser);
            refresh(actionEvent);
            alerts.AlertInformation(String.format("User: %s %s has been deleted",
                    user.getName(), user.getSurname()));
        }
    }

    public void showContactDetails(ActionEvent actionEvent) throws IOException {
        user = this.tblUsers.getSelectionModel().getSelectedItem();

        if (user == null) alerts.AlertError("Choose one user first");
        else stages.setStage(applicationContext, "/fxml/users/contactDetails.fxml");
    }


    public void printUsers(ActionEvent actionEvent) {
        printer.printToPDF(tblUsers);
    }

    public void refresh(ActionEvent actionEvent) {
        tblUsers.setItems(FXCollections.observableArrayList(service.listALL()));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        stages.setStage(applicationContext, "/fxml/menu.fxml");
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();


    }

    public User saveUser() {
        user = this.tblUsers.getSelectionModel().getSelectedItem();
        return user;
    }

    public void filterByName(KeyEvent event) {
        String filterName = this.txtFilter.getText();

        if(filterName.isEmpty()){
            this.tblUsers.setItems(users);
        }else{
            this.filterUsers.clear();

            for (User user: this.users){
                if(user.getName().toLowerCase().contains(filterName.toLowerCase())){
                    this.filterUsers.add(user);
                }
            }
            this.tblUsers.setItems(filterUsers);

        }
    }
}
