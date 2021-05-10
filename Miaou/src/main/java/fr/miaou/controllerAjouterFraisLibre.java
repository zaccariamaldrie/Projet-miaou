package fr.miaou;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

public class controllerAjouterFraisLibre {

    @FXML
    private TextField libelle, montant;

    @FXML
    private DatePicker date;

    @FXML
    private Spinner<Integer> quantite;

    @FXML
    void initialize() {
    }

    @FXML
    void addFrais(ActionEvent event) {
        for (Agent ag : MainApp.agents){
            if(ag.getId() == MainApp.id){
                MainApp.con.createFrais(MainApp.frais, MainApp.id, libelle.getText(), quantite.getValue(), Double.valueOf(montant.getText()), fenetreVisiteurController.fdrmMois, date.getValue());
            }
        }
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
