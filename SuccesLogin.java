package com.example.MAIN;

import com.example.Pouzivatelia.User;
import com.example.Pouzivatelia.*;
import com.example.secondary.Tura;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import static com.example.secondary.TrasaDatabase.trasadatabase;
import static com.example.secondary.Tura.najdiSpojenie;

/**
 * This is controler for main window screen
 */
public class SuccesLogin {
    /**
     * This initializes the main window screen
     */
    @FXML private Label userProfile = new Label();
    @FXML private CheckBox jedlo = new CheckBox();
    @FXML private CheckBox vodopad = new CheckBox();
    @FXML private CheckBox zlanovanie = new CheckBox();
    @FXML private CheckBox vlek = new CheckBox();
    @FXML private Label output = new Label();
    @FXML private ChoiceBox<String> startP = new ChoiceBox<>();
    @FXML private ChoiceBox<String> cielP = new ChoiceBox<>();

    /**
     * Information which user is logged in
     */
    public User user;

    /**
     * Set user in main window screen
     * @param user username
     */
    public void setUser(User user) {
        this.user = user;
        userProfile.setText("@ " + user.getUsername() + (isHorolezec(user) ? "C" : ""));
    }

    /**
     * information if user is Horozec !** POUŽITIE INSTANCEOF - RTTI **!
     * @param user user
     * @return boolean when horolezec
     */
    private boolean isHorolezec(User user) {
        if (user instanceof Horolezec horolezec) {
            return horolezec.getEquip() && horolezec.getCondition() == 1 || horolezec.getCondition() == 2;
        }
        return false;
    }

    /**
     * This method is called when "Vyhladat" button is pressed
     */
    @FXML
    private void handleVyhladatButtonAcrion() {
        String zaciatok = startP.getValue();
        String koniec = cielP.getValue();
        boolean Eat = jedlo.isSelected();
        boolean Vpad = vodopad.isSelected();
        boolean Vlek = vlek.isSelected();
        boolean Lano = zlanovanie.isSelected();
        Tura.Trasa novinky = najdiSpojenie(zaciatok, koniec, trasadatabase, Eat, Vpad, Vlek, Lano);
        assert novinky != null;
        if(isHorolezec(user)){
            int horolezecCas = novinky.getCas();
            horolezecCas -= 25;
            novinky.setCas(horolezecCas);
        }
        output.setText(novinky.toString());
    }

    /**
     * This will return to login screen after log out button is pressed
     * @throws IOException check
     */
    @FXML private void logout() throws IOException {
        user.setLoggedin(false);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        Stage stage = (Stage) vlek.getScene().getWindow();
        stage.setScene(scene);
    }
}