package src;

import java.util.Date;

public class FicheFrais {
    
    private int FDRM_ID;
    private String FDRM_mois;
    private Date FDRM_reception;
    private Date FDRM_validation;
    private Date FDRM_paiement;
    private Date FDRM_remboursement;
    private int nbrj_conges;

    public FicheFrais (int id, String mois, int conges){
        FDRM_ID = id;
        FDRM_mois = mois;
        nbrj_conges = conges;
    }

    public FicheFrais (int id, String mois, Date reception, Date validation, Date paiement, Date remboursement, int conges){
        FDRM_ID = id;
        FDRM_mois = mois;
        FDRM_reception = reception;
        FDRM_validation = validation;
        FDRM_paiement = paiement;
        FDRM_remboursement = remboursement;
        nbrj_conges = conges;
    }

    public void decrire(){
        System.out.println("Caractéristiques de la fiche de frais-------");
        System.out.println("ID : "+FDRM_ID);
        System.out.println("Mois de la fiche de frais : "+FDRM_mois);
        System.out.println("Date de reception : "+FDRM_reception);
        System.out.println("Date de validation : "+FDRM_validation);
        System.out.println("Date de paiement : "+FDRM_paiement);
        System.out.println("Date remboursement : "+FDRM_remboursement);
        System.out.println("Nombre de jours de congés : "+nbrj_conges);
    }
}
