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

    public int connectAccount(String login, String mdp){
        try {
            resultSet = statement.executeQuery("SELECT * FROM agents");
            while (resultSet.next()){
                if (login.equals(resultSet.getString("ag_nom"))){
                    if (mdp.equals(resultSet.getString("ag_password"))){
                        return resultSet.getInt("ag_matricule");
                    }
                }
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String agGetNom(int id){
        try {
            resultSet = statement.executeQuery("SELECT * FROM agents");
            while (resultSet.next()){
                if (id == resultSet.getInt("ag_matricule")){
                    return resultSet.getString("ag_nom");
                }
            }
            return "Unknown";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    public int agTypeAgent(int id){
        try {
            resultSet = statement.executeQuery("SELECT * FROM agents");
            while (resultSet.next()){
                if (id == resultSet.getInt("ag_matricule")){
                    return resultSet.getInt("fk_ta");
                }
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public void initialiser(ArrayList<Agent> agents, ArrayList<FicheFrais> fiches, ArrayList<Frais> frais){
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM frais");
            while (resultSet.next()){
                frais.add(new Frais(resultSet.getInt("fr_id"), resultSet.getString("fr_libelle_libre"), resultSet.getTimestamp("fr_date"), resultSet.getInt("fr_quantite"), resultSet.getDouble("fr_montant"), resultSet.getInt("fr_status"), resultSet.getInt("fk_tre"), resultSet.getInt("fk_mfr"), resultSet.getInt("fk_fdrm_ag"), resultSet.getInt("fk_fdrm_mois")));
            }
            resultSet = statement.executeQuery("SELECT * FROM fdrm");
            while (resultSet.next()){
                fiches.add(new FicheFrais(resultSet.getInt("fk_ag"), resultSet.getInt("fdrm_mois"), resultSet.getDate("FDRM_reception"), resultSet.getDate("FDRM_validation"), resultSet.getDate("FDRM_paiement"), resultSet.getDate("FDRM_remboursement"), resultSet.getInt("nbrj_conges")));
            }
            resultSet = statement.executeQuery("SELECT * FROM agents");
            while (resultSet.next()){
                agents.add(new Agent(resultSet.getInt("ag_matricule"), resultSet.getString("ag_nom"), resultSet.getString("ag_password"), resultSet.getInt("fk_se"), resultSet.getInt("fk_ta"), resultSet.getString("fk_ve")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createFrais(ArrayList<Frais> frais, int idAgentDonne){
        try {
            Scanner sc = new Scanner(System.in);
            Scanner cs = new Scanner(System.in);
            System.out.println("Outil de création d'un Frais -----");
            System.out.println("Mois de la fiche du frais (en nombre): ");
            int mois = sc.nextInt();
            System.out.println("Libellé du frais : ");
            String libelle = cs.nextLine();
            System.out.println("Quantité : ");
            int quantite = sc.nextInt();
            System.out.println("Prix : ");
            double prix = sc.nextDouble();
            int id = 0;
            resultSet = statement.executeQuery("SELECT fr_id FROM miaou.frais ORDER BY fr_id DESC LIMIT 1;");
            while (resultSet.next()){
                id = resultSet.getInt("fr_id");
            }
            Frais newFrais = new Frais(id+1, libelle, quantite, prix, idAgentDonne, mois);
            frais.add(newFrais);
            statement.execute("insert into frais(fr_libelle_libre, fr_quantite, fr_montant, fr_taxe, fr_status, fk_fdrm_ag, fk_fdrm_mois) values ('"+libelle+"', "+quantite+", "+prix+", "+newFrais.getTaxe()+", 0, "+idAgentDonne+", "+mois+")");
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createFicheFrais(ArrayList<FicheFrais> fiches, int idAgentDonne){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Outil de création de Fiche de frais -----");
            System.out.println("Mois de la fiche de frais (en nombre) : ");
            int mois = sc.nextInt();
            fiches.add(new FicheFrais(idAgentDonne, mois));
            statement.execute("insert into fdrm(fk_ag, fdrm_mois) values ("+idAgentDonne+", "+mois+")");
        } catch (SQLException e) {
            System.out.println("Erreur : Merci de vérifier si la fiche de frais du mois selectionné n'existe pas déjà.");
        }
    }

}
