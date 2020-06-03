package com.example.version.controller.javafx.players;

import com.example.version.model.Player;
import com.example.version.model.Training;
import com.example.version.service.PlayerService;
import com.example.version.service.TrainingService;
import com.example.version.utils.Alerts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TrainingControllerFX implements Initializable {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private PlayerService playerService;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private PlayersController controller;
    @Autowired
    private Alerts alerts;

    @FXML
    private Label lbName;
    @FXML
    private RadioButton rBSpeed;
    @FXML
    private RadioButton rBAgility;
    @FXML
    private RadioButton rBStrength;
    @FXML
    private RadioButton rBFlexibility;
    @FXML
    private RadioButton rBTactical;
    @FXML
    private RadioButton rBGame;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextArea txtExercises;
    @FXML
    private TextArea txtPersonalNotes;

    private Player player;
    private Training training;
    private ToggleGroup radioGroup = new ToggleGroup();


    public void setRadioGroup(ToggleGroup radioGroup) {
        rBSpeed.setToggleGroup(radioGroup);
        rBAgility.setToggleGroup(radioGroup);
        rBStrength.setToggleGroup(radioGroup);
        rBFlexibility.setToggleGroup(radioGroup);
        rBTactical.setToggleGroup(radioGroup);
        rBGame.setToggleGroup(radioGroup);
        this.radioGroup = radioGroup;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = controller.savePlayer();
        this.lbName.setText(player.getName() + " " + player.getSurname());
        training = trainingService.get(player.getIdPlayer());
        if (training != null) {
            this.txtDuration.setText(training.getDuration());
            this.txtExercises.setText(training.getExercises());
            this.txtPersonalNotes.setText(training.getPersonalNotes());
            String type = training.getType();
            if (type.equals("Speed & Acceleration")) rBSpeed.setSelected(true);
            if (type.equals("Agility")) rBAgility.setSelected(true);
            if (type.equals("Strength")) rBStrength.setSelected(true);
            if (type.equals("Flexibility")) rBFlexibility.setSelected(true);
            if (type.equals("Tactical")) rBTactical.setSelected(true);
            if (type.equals("Game Simulations")) rBGame.setSelected(true);
        }
        setRadioGroup(radioGroup);
        setHandleToogleGroup(radioGroup);

    }


    public void confirm(ActionEvent actionEvent) {
        player = controller.savePlayer();
        training = trainingService.get(player.getIdPlayer());
        if (training == null) {
            training = new Training();
            training.setIdPlayer(player.getIdPlayer());
            training.setDuration(txtDuration.getText());
            training.setExercises(txtExercises.getText());
            training.setPersonalNotes(txtPersonalNotes.getText());
            training.setType(getSelectedToggletoString());
            trainingService.save(training);
            alerts.AlertInformation("New training is created for "
                    + player.getName() + " " + player.getSurname());
            controller.refresh(actionEvent);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } else {
            training.setDuration(txtDuration.getText());
            training.setExercises(txtExercises.getText());
            training.setPersonalNotes(txtPersonalNotes.getText());
            training.setType(getSelectedToggletoString());
            trainingService.update(player.getIdPlayer(), training);
            alerts.AlertInformation("Training has been updated for "
                    + player.getName() + " " + player.getSurname());
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
                    if (rBSpeed.isSelected()) {
                        txtDuration.setText("40 min");
                        txtExercises.setText(" 1. Box Jump x4 \n 2. 30sec Sprints x3 \n " +
                                "3. 30sec Hill Sprints x3 \n 4. Dynamic Drills x3");
                    }
                    if (rBAgility.isSelected()) {
                        txtDuration.setText("50 min");
                        txtExercises.setText(" 1. Isometric Hamstring Bridge x4 \n 2. Burpees x4 \n " +
                                "3. Jumping Lunges x4 \n 4. Mountain Climbers ");
                    }
                    if (rBStrength.isSelected()) {
                        txtDuration.setText("65 min");
                        txtExercises.setText(" 1. DeadLift x4 \n 2. Bulgarian squat x4 \n " +
                                "3.Single-leg half squats x4 \n 4. Reverse Lunge x4");
                    }
                    if (rBFlexibility.isSelected()) {
                        txtDuration.setText("40 min");
                        txtExercises.setText(" 1. Kneeling Hip Flexor Stretch x4 \n 2.Lunge With Spinal Twist x3 \n " +
                                "3. 90/90 Stretch x3 \n 4. Frog Stretch x4");
                    }
                    if (rBTactical.isSelected()) {
                        txtDuration.setText("130 min");
                        txtExercises.setText(" 1. Passing Excerceses 20 min \n 2. Building Attacks 20 min \n " +
                                "3. Pressing & Transition 20 min \n 4. Decision Making 30 min");
                    }
                    if (rBGame.isSelected()) {
                        txtDuration.setText("120 min");
                        txtExercises.setText(" 1. Game Functional Possession 30 min \n 2. Swip Formation Game 30 min \n " +
                                "3. Defending Cooperation 30 min \n 4. Fun Game 30 min");
                    }
                }
            }
        });
    }
}



