/**
 * Module info
 */
module com.example {
    /**
     * requirements
     */
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.MAIN to javafx.fxml;
    exports com.example.MAIN;
    exports com.example.Pouzivatelia;
    opens com.example.Pouzivatelia to javafx.fxml;

}