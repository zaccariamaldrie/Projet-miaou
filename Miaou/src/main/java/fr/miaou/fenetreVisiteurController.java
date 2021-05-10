package fr.miaou;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class fenetreVisiteurController {

    public static int fdrmMois;

    @FXML
    private TextField moisFiche, dateReception, dateValidation, datePaiement, dateRemboursement, nbConges;
    @FXML
    private ListView<String> listeFiches = new ListView<String>();

    @FXML
    private ListView<String> listeFrais = new ListView<String>();

    @FXML
    private Button addFraisLibre, addFraisKm, addFraisNuit, addFraisRestau;

    @FXML
    private MenuItem supprMenu;

    @FXML
    public TableView<Frais> TVFraisData = new TableView<>();

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
        addFraisLibre.setDisable(true);
        addFraisKm.setDisable(true);
        addFraisRestau.setDisable(true);
        addFraisNuit.setDisable(true);
        supprMenu.setDisable(false);
    }

    @FXML
    void selectFiche(Event event) {
        TVFraisData.getItems().clear();
        for (FicheFrais fiche : MainApp.fiches){
            if (fiche.getAg() == MainApp.id && listeFiches.getSelectionModel().getSelectedItem().equals(fiche.getMois())){
                if (fiche.getReception().equals("En attente")){
                    addFraisLibre.setDisable(false);
                    addFraisKm.setDisable(false);
                    addFraisRestau.setDisable(false);
                    addFraisNuit.setDisable(false);
                    supprMenu.setDisable(false);
                } else {
                    addFraisLibre.setDisable(true);
                    addFraisKm.setDisable(true);
                    addFraisRestau.setDisable(true);
                    addFraisNuit.setDisable(true);
                    supprMenu.setDisable(true);
                }
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

    @FXML
    void addFraisLibre(ActionEvent event) throws IOException {
        Scene ajouterFrais = new Scene(MainApp.loadFXML("fenetreAjouterFraisLibre.fxml"));
        Stage fenetreAjouterFrais = new Stage();
        fenetreAjouterFrais.initModality(Modality.WINDOW_MODAL);
        fenetreAjouterFrais.initOwner(MainApp.stage);
        fenetreAjouterFrais.setScene(ajouterFrais);
        fenetreAjouterFrais.show();
    }

    @FXML
    void addFraisNuit(ActionEvent event) throws IOException {
        Scene ajouterFrais = new Scene(MainApp.loadFXML("fenetreAjouterFraisNuit.fxml"));
        Stage fenetreAjouterFrais = new Stage();
        fenetreAjouterFrais.initModality(Modality.WINDOW_MODAL);
        fenetreAjouterFrais.initOwner(MainApp.stage);
        fenetreAjouterFrais.setScene(ajouterFrais);
        fenetreAjouterFrais.show();
    }

    @FXML
    void addFraisRestau(ActionEvent event) throws IOException {
        Scene ajouterFrais = new Scene(MainApp.loadFXML("fenetreAjouterFraisRestau.fxml"));
        Stage fenetreAjouterFrais = new Stage();
        fenetreAjouterFrais.initModality(Modality.WINDOW_MODAL);
        fenetreAjouterFrais.initOwner(MainApp.stage);
        fenetreAjouterFrais.setScene(ajouterFrais);
        fenetreAjouterFrais.show();
    }

    @FXML
    void addFraisKm(ActionEvent event) throws IOException {
        Scene ajouterFrais = new Scene(MainApp.loadFXML("fenetreAjouterFraisKm.fxml"));
        Stage fenetreAjouterFrais = new Stage();
        fenetreAjouterFrais.initModality(Modality.WINDOW_MODAL);
        fenetreAjouterFrais.initOwner(MainApp.stage);
        fenetreAjouterFrais.setScene(ajouterFrais);
        fenetreAjouterFrais.show();
    }

    @FXML
    void supprimerFrais(ActionEvent event) {
        MainApp.con.deleteFrais(MainApp.frais, TVFraisData.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void supprimable(){

    }
}