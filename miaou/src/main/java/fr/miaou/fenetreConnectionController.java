package fr.miaou;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class fenetreConnectionController {

    @FXML
    private TextField loginArea;
    @FXML
    private PasswordField passwordArea;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void loginIn(ActionEvent event) throws IOException {
        System.out.println(loginArea.getText());
        System.out.println(passwordArea.getText());
        MainApp.stage.setScene(MainApp.setScene("visiteur"));
    }

    @FXML
    void initialize() {

    }
}
