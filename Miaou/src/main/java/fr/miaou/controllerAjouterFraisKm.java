package fr.miaou;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controllerAjouterFraisKm {

    @FXML
    private Spinner<Integer> quantite;

    @FXML
    void addFrais(ActionEvent event) {
        for (Agent ag : MainApp.agents){
            if(ag.getId() == MainApp.id){
                MainApp.con.createFraisKm(MainApp.frais, MainApp.id, quantite.getValue(), fenetreVisiteurController.fdrmMois);
            }
        }
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
