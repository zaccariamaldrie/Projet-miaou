package fr.miaou;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class SQLConnection {

    private Connection con;
    private Statement statement;
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

    public void setStatusFrais(Frais frais, int status){
        try {
            frais.setStatus(status);
            statement.execute("UPDATE frais SET fr_status = '"+status+"' WHERE fr_id = "+frais.getIdFrais()+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void envoyerFiche(FicheFrais fiche){
        LocalDate date = LocalDate.now();
        fiche.setReception(date);
        try {
            statement.execute("UPDATE fdrm SET fdrm_reception = '"+date+"' WHERE fk_ag = '"+fiche.getAg()+"' AND fdrm_mois = '"+fiche.getFdrmMois()+"' ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validerFiche(FicheFrais fiche){
        LocalDate date = LocalDate.now();
        fiche.setValidation(date);
        try {
            statement.execute("UPDATE fdrm SET fdrm_validation = '"+date+"' WHERE fk_ag = '"+fiche.getAg()+"' AND fdrm_mois = '"+fiche.getFdrmMois()+"' ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void payerFiche(FicheFrais fiche){
        LocalDate date = LocalDate.now();
        fiche.setPaiement(date);
        try {
            statement.execute("UPDATE fdrm SET fdrm_paiement = '"+date+"' WHERE fk_ag = '"+fiche.getAg()+"' AND fdrm_mois = '"+fiche.getFdrmMois()+"' ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rembourserFiche(FicheFrais fiche){
        LocalDate date = LocalDate.now();
        fiche.setRemboursement(date);
        try {
            statement.execute("UPDATE fdrm SET fdrm_remboursement = '"+date+"' WHERE fk_ag = '"+fiche.getAg()+"' AND fdrm_mois = '"+fiche.getFdrmMois()+"' ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initialiser(ArrayList<Agent> agents, ArrayList<FicheFrais> fiches, ArrayList<Frais> frais, ArrayList<TypeRefus> typeRefus, ArrayList<Vehicule> vehicules){
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM frais");
            while (resultSet.next()){
                frais.add(new Frais(resultSet.getInt("fr_id"), resultSet.getString("fr_libelle_libre"), resultSet.getObject("fr_date", LocalDate.class), resultSet.getInt("fr_quantite"), resultSet.getDouble("fr_montant"), resultSet.getInt("fr_status"), resultSet.getInt("fk_tre"), resultSet.getInt("fk_mfr"), resultSet.getInt("fk_fdrm_ag"), resultSet.getInt("fk_fdrm_mois")));
            }
            resultSet = statement.executeQuery("SELECT * FROM fdrm");
            while (resultSet.next()){
                fiches.add(new FicheFrais(resultSet.getInt("fk_ag"), resultSet.getInt("fdrm_mois"), resultSet.getObject("FDRM_reception", LocalDate.class), resultSet.getObject("FDRM_validation", LocalDate.class), resultSet.getObject("FDRM_paiement", LocalDate.class), resultSet.getObject("FDRM_remboursement", LocalDate.class), resultSet.getInt("nbrj_conges")));
            }
            resultSet = statement.executeQuery("SELECT * FROM agents");
            while (resultSet.next()){
                agents.add(new Agent(resultSet.getInt("ag_matricule"), resultSet.getString("ag_nom"), resultSet.getString("ag_password"), resultSet.getInt("fk_se"), resultSet.getInt("fk_ta"), resultSet.getString("fk_ve")));
            }
            resultSet = statement.executeQuery("SELECT * FROM type_refus");
            while (resultSet.next()){
                typeRefus.add(new TypeRefus(resultSet.getInt("tre_id"), resultSet.getString("tre_libelle")));
            }
            resultSet = statement.executeQuery("SELECT * FROM vehicules");
            while (resultSet.next()){
                vehicules.add(new Vehicule(resultSet.getString("ve_immat"), resultSet.getString("ve_marque"), resultSet.getString("ve_model"), resultSet.getInt("fk_fkm")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createFrais(ArrayList<Frais> frais, int idAgentDonne, String libelle, int quantite, double prix, int mois){
        try {
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

    public void deleteFrais(ArrayList<Frais> frais, int id){
        try {
            for (Frais fr : frais){
                if(fr.getIdFrais() == id){
                    frais.remove(fr);
                }
            }
            statement.execute("DELETE FROM frais WHERE fr_id = "+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createVehicule(ArrayList<Vehicule> vehicules, String ve_immat, String ve_marque, String ve_model){
        try {
            vehicules.add(new Vehicule(ve_immat, ve_marque, ve_model, 1));
            statement.execute("insert into vehicules(ve_immat, ve_marque, ve_model, fk_fkm) values ('"+ve_immat+"', '"+ve_marque+"', '"+ve_model+"', 1)");
        } catch (SQLException e) {
            System.out.println("Erreur");
        }
    }

    public void deleteVehicule(ArrayList<Vehicule> vehicules, String ve_immat){
        try {
            for (Vehicule vehicule : vehicules){
                if(vehicule.getImmat().equals(ve_immat)){
                    vehicules.remove(vehicule);
                }
            }
            statement.execute("DELETE FROM vehicules WHERE ve_immat = '"+ve_immat+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeVehicule (ArrayList<Agent> agents, int id, String immat){
        try {
            for (Agent agent : agents) {
                if (agent.getId() == id){
                    agent.setVehicule(immat);
                }
            }
            statement.execute("UPDATE agents SET fk_ve = '"+immat+"' WHERE ag_matricule = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createFicheFrais(ArrayList<FicheFrais> fiches, int idAgentDonne, int mois){
        try {
            fiches.add(new FicheFrais(idAgentDonne, mois));
            statement.execute("insert into fdrm(fk_ag, fdrm_mois) values ("+idAgentDonne+", "+mois+")");
        } catch (SQLException e) {
            System.out.println("Erreur : Merci de vérifier si la fiche de frais du mois selectionné n'existe pas déjà.");
        }
    }

    public void createTypeRefus(ArrayList<TypeRefus> typeRefus, Frais frais){
        try {
            int id = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Motif du refus : ");
            String motif = sc.nextLine();
            resultSet = statement.executeQuery("SELECT tre_id FROM miaou.type_refus ORDER BY tre_id DESC LIMIT 1;");
            while (resultSet.next()){
                id = resultSet.getInt("tre_id");
            }
            frais.setTre(id+1);
            typeRefus.add(new TypeRefus(id+1, motif));
            statement.execute("insert into type_refus(tre_libelle) values ('"+motif+"')");
        } catch (SQLException e) {
            System.out.println("Erreur type refus");
        }
    }

}
