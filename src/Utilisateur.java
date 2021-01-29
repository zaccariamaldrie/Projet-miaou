package src;

public class Utilisateur {
    
    protected String nom;
    protected String mdp;
    protected String email;
    protected int id;
    protected String type;

    public String getNom(){
        String nomReturn = this.nom;
        return nomReturn;
    }
}
