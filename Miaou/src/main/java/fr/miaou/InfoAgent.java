package fr.miaou;

public class InfoAgent {
    private int ag_id;
    private String ag_nom;
    private String ag_prenom;

    public InfoAgent(int id, String nom, String prenom){
        setId(id);
        setNom(nom);
        setPrenom(prenom);
    }

    public int getId(){
        return this.ag_id;
    }
    
    public void setId(int id){
        this.ag_id = id;
    }

    public String getNom(){
        return this.ag_nom;
    }
    
    public void setNom(String nom){
        this.ag_nom = nom;
    }
    
    public String getPrenom(){
        return this.ag_prenom;
    }

    public void setPrenom(String prenom){
        this.ag_prenom = prenom;
    }
}
