package fr.miaou;

import java.util.function.UnaryOperator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class controllerAjouterFraisNuit {

    @FXML
    private Spinner<Integer> quantite;

    @FXML
    void addFrais(ActionEvent event) {
        for (Agent ag : MainApp.agents){
            if(ag.getId() == MainApp.id){
                MainApp.con.createFraisForfaitaire(MainApp.frais, MainApp.id, ag.getSecteurInt()+5, quantite.getValue(), fenetreVisiteurController.fdrmMois);
            }
        }
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
