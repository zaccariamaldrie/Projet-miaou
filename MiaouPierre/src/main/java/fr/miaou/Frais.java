package fr.miaou;

import java.time.LocalDate;

public class Frais {
    
    private int fr_id;
    private String fr_libelle_libre;
    private LocalDate fr_date;
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

    public Frais (int id, String libelle, LocalDate date, int quantite, Double montant, int status, int tre, int mfr, int fdrm_ag, int fdrm_mois){
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

    public int getFdrmAg (){
        return fk_fdrm_ag;
    }

    public int getFdrmMois(){
        return fk_fdrm_mois;
    }

    public int getIdFrais(){
        return this.fr_id;
    }

    public int getStatus(){
        return this.fr_status;
    }

    public void setStatus(int status){
        this.fr_status = status;
    }

    public void setTre(int tre_id){
        this.fk_tre = tre_id;
    }

    public void modifierFrais(int id, String libelle, LocalDate date, int quantite, Double montant, int status, int tre, int mfr, int fdrm_ag, int fdrm_mois){
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

    public void decrireFrais(int id, int mois){

        if (fk_fdrm_ag == id && fk_fdrm_mois == mois){
            System.out.println("Caractéristiques du frais-------");
            System.out.println("ID : "+fr_id);
            System.out.println("Libellé : "+fr_libelle_libre);
            System.out.println("Quantité : "+fr_quantite);
            System.out.println("Montant : "+fr_montant);
            System.out.println("Taxe : "+fr_taxe);
            System.out.println("Status : "+fr_status);
        }
    }

    public Double getTaxe(){
        return this.fr_taxe;
    }
}
