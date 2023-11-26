package com.example.MAIN;

import com.example.Pouzivatelia.User;
import com.example.Pouzivatelia.UserDatabase;
import com.example.secondary.Tura;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Paths;
import java.util.Optional;

import static com.example.Pouzivatelia.UserDatabase.userdatabase;
import static com.example.pomocneFunkcie.BMI.bmi;
import static com.example.pomocneFunkcie.Kondicia.kondicia;

/**
 *  This is controler for login screen
 *  !** Návrhový vzor Model-View-Controller **!
 *  !** Oddelenie grafickej časti od logiky **!
 */
public class HelloController extends Tura {
    /**
     * Path to file where user is added afrer registration
     */
    private static final String FILE_PATH_REGISTRATION = "src/main/java/com/example/Pouzivatelia/uzivatelia.txt";

    /**
     * This initializes the login screen
     */
    @FXML  private TextField usernameField = new TextField();
    @FXML  private PasswordField passwordField = new PasswordField();
    @FXML  private Label resultField = new Label();

/**
     * This method is called when "Login" button is pressed
     * @throws IOException check
     */

    @FXML
    /*
      Method react after enter is pressed
     */
    public void initialize() {
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                try {
                    handleLoginButtonAction();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method is called when "Registration" button is pressed
     * !** Lambda výraz **!
     * @throws UserNotFoundException check
     */
public User loadUser(String username, String password) throws UserNotFoundException {
    Optional<User> optionalUser = userdatabase.stream()
            .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
            .findFirst();
    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        user.setLoggedin(true);
        return user;
    } else {
        throw new UserNotFoundException("User not found");
    }
}

    /**
     * This method is called when login button is pressed
     *  @throws IOException check
     */
    @FXML
    private void handleLoginButtonAction() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User loggedUser = null;
        /**
         * !** Výnimka **!
         * Check if user is in database
         * @throws UserNotFoundException check
         */
        try {
            loggedUser = loadUser(username, password);

        } catch (UserNotFoundException e) {
            e.getMessage();
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("error.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 300, 200);
            Stage errorStage = new Stage();
            errorStage.setTitle("Error");
            errorStage.setScene(scene2);
            errorStage.show();
        }
        if (loggedUser != null) {
            FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("SuccesLogin.fxml"));
            Scene scene1 = new Scene(fxmlLoader1.load(), 500, 350);
            SuccesLogin controller = fxmlLoader1.getController();
            controller.setUser(loggedUser);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene1);
        }
    }
    /**
     * This initializes the registration screen
     */
    @FXML private PasswordField passwordFieldR = new PasswordField();
    @FXML private TextField usernameFieldR = new TextField();
    @FXML private CheckBox userEquip = new CheckBox();
    @FXML private TextField userWeight = new TextField();
    @FXML private TextField userHeight = new TextField();
    @FXML private Label existingUser = new Label();

    /**
     * This method is called when registration button is pressed
     */
    @FXML
    private void handleRegistrationButtonAction() {
        String username = usernameFieldR.getText();
        String password = passwordFieldR.getText();
        String weight = userWeight.getText();
        String height = userHeight.getText();
        if(username.isEmpty()){
            existingUser.setText("Fill out username field!");
        }
        if(password.isEmpty()){
            existingUser.setText("Fill out password field!");
        }
        if(weight.isEmpty()){
            existingUser.setText("Fill out weight field!");
        }
        if (height.isEmpty()){
            existingUser.setText("Fill out height field!");
        }
        boolean UserAlreadyExists = false;
        double w = Double.parseDouble(weight);
        double h = Double.parseDouble(height) / 100;
        double b = bmi(w,h);
        int a = kondicia(b);
        for (User user : userdatabase){
            if (user.getUsername().equals(username)){
                UserAlreadyExists = true;
                break;
            }
        }
        if (UserAlreadyExists) {
            existingUser.setText("This username already exist!");
        } else {
            boolean equiped = userEquip.isSelected();
            /**
             * !** Serializácia **!
             * Add user to database
             */
            try (PrintWriter writer = new PrintWriter(new FileWriter(Paths.get(FILE_PATH_REGISTRATION).toFile(), true))) {
                writer.println(username + "," + password + "," + equiped + "," + false + "," + a);
            } catch (IOException ignored) {
            }
            User newUser = new User(username, password, equiped, false, a) {
                @Override
                public void setKondicia(int kondicia) {

                }

                @Override
                public int getKondicia(double weight, double height, int age) {
                    return 0;
                }

                @Override
                public int computeTime(int cas) {
                    return 0;
                }
            };
            userdatabase.add(newUser);
            UserDatabase.loadData();
            boolean found = false;
            for (User user : userdatabase){
                if (user.getUsername().equals(newUser.getUsername())) {
                    found = true;
                    break;
                }
            }
            if (found){
                existingUser.setText("Registration Successful!");
            }
        }
    }

    private class UserNotFoundException extends Exception {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}