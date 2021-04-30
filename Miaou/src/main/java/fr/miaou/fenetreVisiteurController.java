package fr.miaou;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class fenetreVisiteurController {

    int fdrmMois;

    @FXML
    private TextField moisFiche, dateReception, dateValidation, datePaiement, dateRemboursement, nbConges;
    @FXML
    private ListView<String> listeFiches = new ListView<String>();

    //essayer d'utiliser des getter
    @FXML
    private TableView<Frais> TVFraisData = new TableView<>();

    public ObservableList<String> getFichesFrais(){
        ObservableList<String> fichesMois = FXCollections.observableArrayList();
        for (FicheFrais fiche : MainApp.fiches){
            if (fiche.getAg() == MainApp.id){
                fichesMois.add(fiche.getMois());
            }
        }
        return fichesMois;
    }

    public ObservableList<Frais> getFraisData(){
        ObservableList<Frais> FraisData = FXCollections.observableArrayList();
        for (Frais fr : MainApp.frais){
            if (fr.getFdrmAg() == MainApp.id && fr.getFdrmMois() == fdrmMois){
                FraisData.add(fr);
            }
        }
        return FraisData;
    }

    @FXML
    void initialize(){
        listeFiches.setItems(getFichesFrais());
    }

    @FXML
    void selectFiche(MouseEvent event) {
        TVFraisData.getItems().clear();
        for (FicheFrais fiche : MainApp.fiches){
            if (fiche.getAg() == MainApp.id && listeFiches.getSelectionModel().getSelectedItem().equals(fiche.getMois())){
                fdrmMois = fiche.getFdrmMois();
                moisFiche.setText(fiche.getMois());
                dateReception.setText(fiche.getReception());
                dateValidation.setText(fiche.getValidation());
                datePaiement.setText(fiche.getPaiement());
                dateRemboursement.setText(fiche.getRemboursement());
                nbConges.setText(String.valueOf(fiche.getNbrjConges()));
            }
        }
        TVFraisData.getItems().addAll(getFraisData());
    }

    @FXML
    void trierFrais(MouseEvent event) {

    }
    
}