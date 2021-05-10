package fr.miaou;

import com.mysql.cj.xdevapi.Schema.Validation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

public class controllerComptable {
    @FXML
    private Label welcomeWho, nbFichesValidation;

    private int agSelected;
    private int moisSelected;
    private int agSuiviSelected;
    private int moisSuiviSelected;

    @FXML
    private TableView<Frais> TVsuiviFrais = new TableView<>(); //4
    @FXML
    private TableView<Frais> TVvalidationFrais = new TableView<>(); //2
    @FXML
    private ListView<String> validationListeAgents = new ListView<>(); //1
    @FXML
    private ListView<String> suiviListeAgents = new ListView<>(); // 3

    @FXML
    void initialize() {
        welcomeWho.setText("Bienvenue "+MainApp.con.agGetNom(MainApp.id));
        nbFichesValidation.setText("");
        validationListeAgents.setItems(getFichesFraisValidation());
        suiviListeAgents.setItems(getFichesFraisPaiement());
    }

    public ObservableList<String> getFichesFraisPaiement(){
        ObservableList<String> fichesMois = FXCollections.observableArrayList();
        for (FicheFrais fiche : MainApp.fiches){
            if (fiche.getPaiement().equals("En attente") && !fiche.getValidation().equals("En attente") && !fiche.getReception().equals("En attente")){
                fichesMois.add(fiche.getMois()+" "+fiche.getNomVisiteur());
            }
        }
        return fichesMois;
    }

    public ObservableList<String> getFichesFraisValidation(){
        ObservableList<String> fichesMois = FXCollections.observableArrayList();
        for (FicheFrais fiche : MainApp.fiches){
            if (!fiche.getReception().equals("En attente")){
                fichesMois.add(fiche.getMois()+" "+fiche.getNomVisiteur());
            }
        }
        return fichesMois;
    }

    public ObservableList<Frais> getFraisData(){
        ObservableList<Frais> fraisData = FXCollections.observableArrayList();
        for (Frais fr : MainApp.frais){
            if (fr.getFdrmAg() == agSelected && fr.getFdrmMois() == moisSelected){
                fraisData.add(fr);
            }
        }
        return fraisData;
    }

    public ObservableList<Frais> getFraisSuiviData(){
        ObservableList<Frais> fraisData = FXCollections.observableArrayList();
        for (Frais fr : MainApp.frais){
            if (fr.getFdrmAg() == agSuiviSelected && fr.getFdrmMois() == moisSuiviSelected){
                fraisData.add(fr);
            }
        }
        return fraisData;
    }

    @FXML
    void validerFiche(ActionEvent event) {
        System.out.println("fiche validée");
    }

    @FXML
    void supprHfNonValides(ActionEvent event) {
        System.out.println("fiches hors forfait non valides supprimées");
    }

    @FXML
    void mettreEnPaiement(ActionEvent event) {
        System.out.println("fiche mise en paiement");
    }

    @FXML
    void fichePayee(ActionEvent event) {
        System.out.println("la fiche a été payée");
    }

    @FXML
    void fraisInvalide(ActionEvent event) {
        System.out.println("frais invalide");
    }

    @FXML
    void selectValidation(Event event) {
        TVvalidationFrais.getItems().clear();
        String conte = validationListeAgents.getSelectionModel().getSelectedItem();
        String mots[] = conte.split(" ");
        moisSelected = morphMois(mots[0]);
        for (Agent ag : MainApp.agents){
            if (ag.getNom().equals(mots[1]+" "+mots[2])){
                agSelected = ag.getId();
            }
        }
        TVvalidationFrais.getItems().addAll(getFraisData());
    }

    @FXML
    void selectSuivi(Event event) {
        TVsuiviFrais.getItems().clear();
        TVsuiviFrais.getItems().addAll(getFraisSuiviData());
    }

    public int morphMois(String moisString){
        int mois;
        switch (moisString) {
            case "Janvier":
                mois = 1;
                break;
            case "Fevrier":
                mois = 2;
                break;
            case "Mars":
                mois = 3;
                break;
            case "Avril":
                mois = 4;
                break;
            case "Mai":
                mois = 5;
                break;
            case "Juin":
                mois = 6;
                break;
            case "Juillet":
                mois = 7;
                break;
            case "Aout":
                mois = 8;
                break;
            case "Septembre":
                mois = 9;
                break;
            case "Octobre":
                mois = 10;
                break;
            case "Novembre":
                mois = 11;
                break;
            case "Decembre":
                mois = 12;
                break;
            default:
                mois = 0;
        }
        return mois;
    }
}
