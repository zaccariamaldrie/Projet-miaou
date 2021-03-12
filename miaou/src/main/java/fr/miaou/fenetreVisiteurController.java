package fr.miaou;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class fenetreVisiteurController {

    ObservableList<String> oui = FXCollections.observableArrayList("a", "b","c","d","e","f");
    ArrayList<String> test = new ArrayList<String>();
    @FXML
    private TextField moisFiche, dateReception, dateValidation, datePaiement, dateRemboursement, nbConges;
    @FXML
    public ListView<String> listeFiches = new ListView<String>();
    
    @FXML
    void selectFiche(MouseEvent event) {
        
    }

    @FXML
    void trierFrais(MouseEvent event) {
        System.out.println("Waw truc de fou");
//        listeFiches.setItems(oui);;

    }
    
}