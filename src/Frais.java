
import java.util.Date;

public class Frais {
    
    private int fr_id;
    private String fr_libelle_libre;
    private Date fr_date;
    private int fr_quantite;
    private Double fr_montant;
    private Double fr_taxe;
    private int fr_status;
    private int fk_tre;
    private int fk_mfr;
    private int fk_fdrm_ag;
    private int fk_fdrm_mois;

    public Frais (int id, String libelle, int quantite, Double montant, int fdrm_ag, int fdrm_mois){
        fr_id = id;
        fr_libelle_libre = libelle;
        fr_quantite = quantite;
        fr_montant = montant;
        fr_taxe = fr_montant*0.2;
        fr_status = 0;
        fk_fdrm_ag = fdrm_ag;
        fk_fdrm_mois = fdrm_mois;
    }

    public Frais (int id, String libelle, Date date, int quantite, Double montant, int status, int tre, int mfr, int fdrm_ag, int fdrm_mois){
        fr_id = id;
        fr_libelle_libre = libelle;
        fr_date = date;
        fr_quantite = quantite;
        fr_montant = montant;
        fr_taxe = montant*0.2;
        fr_status = status;
        fk_tre = tre;
        fk_mfr = mfr;
        fk_fdrm_ag = fdrm_ag;
        fk_fdrm_mois = fdrm_mois;
    }

    public void decrireFrais(int id){

        if (fk_fdrm_ag == id){
            System.out.println("Caractéristiques du frais-------");
            System.out.println("ID : "+fr_id);
            System.out.println("Libellé : "+fr_libelle_libre);
            System.out.println("Quantité : "+fr_quantite);
            System.out.println("Montant : "+fr_montant);
            System.out.println("Taxe : "+fr_taxe);
        }
    }

    public Double getTaxe(){
        return this.fr_taxe;
    }
}
