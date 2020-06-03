package com.example.version.utils;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Stages {

    public void setStage(ApplicationContext applicationContext, String viewFXML)
            throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewFXML));
        loader.setControllerFactory(applicationContext::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/Fotman.css");
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/img/icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("FotMan App");
        stage.showAndWait();
    }

    public void setDoubleClickClear(TableView tableView){
        tableView.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow call(TableView playerTableView) {
                final TableRow row = new TableRow<>();
                row.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        final int index = row.getIndex();
                        if (index >= 0 && index < tableView.getItems().size() && tableView.getSelectionModel().isSelected(index)  ) {
                            tableView.getSelectionModel().clearSelection();
                            mouseEvent.consume();
                        }
                    }
                });
                return row;
            }
        });
    }
    

}
