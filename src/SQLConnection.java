package src;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class SQLConnection {

    private Connection con;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public void connection(){
        String url = "jdbc:mysql://localhost:3306/Miaou?ServerTimezone=UTC";
        String userName = "Miaou";
        String password = "MiaouProject";

        try {
            con = DriverManager.getConnection(url, userName, password);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initialiser(ArrayList users, ArrayList fiches, ArrayList frais){
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM frais");
            while (resultSet.next()){
                frais.add(new Frais(resultSet.getInt("FR_ID"), resultSet.getString("FR_libelle_libre"), resultSet.getTimestamp("FR_date"), resultSet.getInt("FR_quantite"), resultSet.getDouble("FR_montant")));
            }
            resultSet = statement.executeQuery("SELECT * FROM fdrm");
            while (resultSet.next()){
                fiches.add(new FicheFrais(resultSet.getInt("FDRM_ID"), resultSet.getString("FDRM_mois"), resultSet.getTimestamp("FDRM_reception"), resultSet.getTimestamp("FDRM_validation"), resultSet.getTimestamp("FDRM_paiement"), resultSet.getTimestamp("FDRM_remboursement"), resultSet.getInt("nbrj_conges")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createFrais(ArrayList frais){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Outil de création d'un Frais -----");
            System.out.println("Libellé du frais : ");
            String test = sc.nextLine();
            System.out.println("Quantité : ");
            int quantite = sc.nextInt();
            System.out.println("Prix : ");
            double prix = sc.nextDouble();
            frais.add(new Frais(0, test, quantite, prix));
            statement.execute("insert into frais(FR_libelle_libre, FR_date, FR_quantite, FR_montant, FR_total, FR_taxe, FR_status) values ('"+test+"', NULL, "+quantite+", "+prix+", "+quantite*prix+", "+(quantite*prix)*0.2+", 0)");
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createFicheFrais(ArrayList fiches){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Outil de création de Fiche de frais -----");
            System.out.println("Mois de la fiche de frais : ");
            String test = sc.nextLine();
            fiches.add(new FicheFrais(0, test, 0));
            statement.execute("insert into fdrm(FDRM_mois, FDRM_reception, FDRM_validation, FDRM_paiement, FDRM_remboursement, nbrj_conges) values ('"+test+"', NULL, NULL, NULL, NULL, 0)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
