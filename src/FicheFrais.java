

import java.util.Date;

public class FicheFrais {
    
    private int fk_ag;
    private int fdrm_mois;
    private Date fdrm_reception;
    private Date fdrm_validation;
    private Date fdrm_paiement;
    private Date fdrm_remboursement;
    private int nbrj_conges;

    public FicheFrais (int ag, int mois){
        fk_ag = ag;
        fdrm_mois = mois;
    }

    public FicheFrais (int ag, int mois, Date reception, Date validation, Date paiement, Date remboursement, int conges){
        fk_ag = ag;
        fdrm_mois = mois;
        fdrm_reception = reception;
        fdrm_validation = validation;
        fdrm_paiement = paiement;
        fdrm_remboursement = remboursement;
        nbrj_conges = conges;
    }

    public void decrireFiche(int id){

        if (fk_ag == id){
            System.out.println("Caractéristiques de la fiche de frais-------");
            System.out.println("Mois de la fiche de frais : "+fdrm_mois);
            System.out.println("Date de reception : "+fdrm_reception);
            System.out.println("Date de validation : "+fdrm_validation);
            System.out.println("Date de paiement : "+fdrm_paiement);
            System.out.println("Date remboursement : "+fdrm_remboursement);
            System.out.println("Nombre de jours de congés : "+nbrj_conges);
        }
    }
    
}
