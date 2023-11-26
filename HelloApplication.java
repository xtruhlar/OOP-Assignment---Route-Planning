package com.example.MAIN;

import com.example.Pouzivatelia.UserDatabase;
import com.example.secondary.TrasaDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.util.Objects;

/**
 * TatrasPlaner
 * @author David_Truhlar
 */
public class HelloApplication extends Application {
    /**
     * Start method
     * @param stage login screen
     * @throws IOException check
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/com/example/MAIN/image-removebg-preview.png")).toExternalForm()));
        stage.setTitle("TatrasPlaner!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method
     * @param args main
     */
    public static void main(String[] args) {
        TrasaDatabase.loadData();
        UserDatabase.loadData();
        launch();

    }
}