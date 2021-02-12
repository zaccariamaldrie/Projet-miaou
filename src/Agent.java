

public class Agent {
    
    private int ag_matricule;
    private String ag_nom;
    private String ag_password;
    private int fk_se;
    private int fk_ta;
    private String fk_ve;

    public Agent (int matricule, String nom, String password, int se, int ta, String ve){
        ag_matricule = matricule;
        ag_nom = nom;
        ag_password = password;
        fk_se = se;
        fk_ta = ta;
        fk_ve = ve;
    }
}
