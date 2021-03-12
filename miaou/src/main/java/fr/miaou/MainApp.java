package fr.miaou;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    public static Stage stage;
    private static Scene fenetreVisiteur;
    private static Scene fenetreConnection;

    @Override
    public void start(Stage s) throws IOException {
        stage = s;
        stage.setTitle("Miaou");
        stage.setScene(setScene("connection"));
        stage.setResizable(false);
        stage.show();
    }

    public static Scene setScene(String a) throws IOException {
        fenetreConnection = new Scene(loadFXML("fenetreConnection.fxml"));
        fenetreVisiteur = new Scene(loadFXML("fenetreVisiteur.fxml"));
        if(a == "connection"){
            return fenetreConnection;
        }else if(a == "visiteur"){
            return fenetreVisiteur;
        }
        return null;
    }
    
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loadFenetre = new FXMLLoader(MainApp.class.getResource("/fxml/" + fxml));
        return loadFenetre.load();
    } 

    public static void main(String[] args) {
        launch(args);
    }

}
