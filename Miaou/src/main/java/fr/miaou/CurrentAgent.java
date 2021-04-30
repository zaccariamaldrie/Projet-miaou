package fr.miaou;

public class CurrentAgent {
    private int ag_matricule;
    private String ag_nom;
    private String ag_password;
    private int fk_se;
    private int fk_ta;
    private String fk_ve;

    public CurrentAgent(int matricule, String nom, String password, int se, int ta, String ve){
        ag_matricule = matricule;
        ag_nom = nom;
        ag_password = password;
        fk_se = se;
        fk_ta = ta;
        fk_ve = ve;
    }

    public int getId(){
        return ag_matricule;
    }

    public int getTypeAgent(){
        return fk_ta;
    }

    public String getNom(){
        return ag_nom;
    }

    public String getSecteur(){
        String secteur;
        switch (fk_se) {
            case 1:
                secteur = "Nord";
                break;
            case 2:
                secteur = "Ouest";
                break;
            case 3:
                secteur = "Est";
                break;
            case 4:
                secteur = "Sud";
                break;
            case 5:
                secteur = "Ile-de-France";
                break;
            default:
                secteur = "Error";
        }
        return secteur;
    }
}
