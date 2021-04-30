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
        MainApp.id = MainApp.con.connectAccount(loginArea.getText(), passwordArea.getText());
        if (MainApp.id == 0){
            System.out.println("Votre identifiant ou mot de passe ne correspond pas.");
        } else {
            MainApp.stage.setScene(MainApp.setScene(MainApp.con.agTypeAgent(MainApp.id)));
        }
    }

    @FXML
    void initialize() {

    }
}
