package com.example.version;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FotManApplication extends Application {


    private ConfigurableApplicationContext applicationContext;
    private Parent rootNode;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        applicationContext = SpringApplication.run(FotManApplication.class);
        FXMLLoader loader = new FXMLLoader(FotManApplication.class.getResource("/fxml/login.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        rootNode = loader.load();

    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add("/css/FotmanLogin.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("FotMan App");
        stage.getIcons().add(new Image("/img/icon.png"));
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.show();
    }


    @Override
    public void stop() {
        applicationContext.close();
    }


}
