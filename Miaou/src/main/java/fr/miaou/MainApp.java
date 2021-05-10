package fr.miaou;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {
    public static Stage stage;
    private static Scene fenetreVisiteur;
    private static Scene fenetreConnection;
    private static Scene fenetreComptable;
    public static int id;

    public static ArrayList<Agent> agents;
    public static ArrayList<FicheFrais> fiches;
    public static ArrayList<Frais> frais;
    public static ArrayList<TypeRefus> typeRefus;
    public static ArrayList<Vehicule> vehicules;

    public static SQLConnection con;

    @Override
    public void start(Stage s) throws IOException {
        stage = s;
        stage.setTitle("Miaou");
        stage.setScene(setScene(0));
        stage.setResizable(false);
        stage.show();
    }

    public static Scene setScene(int idDonne) throws IOException {
        fenetreConnection = new Scene(loadFXML("fenetreConnection.fxml"));
        fenetreVisiteur = new Scene(loadFXML("fenetreVisiteur.fxml"));
        fenetreComptable = new Scene(loadFXML("fenetreComptable.fxml"));
        if(idDonne == 0){
            return fenetreConnection;
        }else if(idDonne == 1){
            return fenetreVisiteur;
        }else if(idDonne == 2){
            return fenetreComptable;
        }
        return null;
    }
    
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loadFenetre = new FXMLLoader(MainApp.class.getResource("/fxml/" + fxml));
        return loadFenetre.load();
    } 

    public static void main(String[] args) {

        /* 
        Creation de la connection avec la base de données
        */

        con = new SQLConnection();
        con.connection();

        /* 
        Creation des array dans lesquels seront gérés les objets
        */

        agents = new ArrayList<>();
        fiches = new ArrayList<>();
        frais = new ArrayList<>();
        typeRefus = new ArrayList<>();
        vehicules = new ArrayList<>();

        con.initialiser(agents, fiches, frais, typeRefus, vehicules);

        id = 0;

        launch(args);
    }

}
