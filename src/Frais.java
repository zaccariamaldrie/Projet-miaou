package src;
import java.util.Date;

public class Frais {
    
    private int FR_ID;
    private String FR_libelle_libre;
    private Date FR_date;
    private int FR_quantite;
    private Double FR_montant;
    private Double FR_total;
    private Double FR_taxe;
    private String FR_status;

    public Frais (int id, String libelle, int quantite, Double montant){
        FR_ID = id;
        FR_libelle_libre = libelle;
        FR_quantite = quantite;
        FR_montant = montant;
        FR_total = quantite*montant;
        FR_taxe = FR_total*0.2;
    }

    public Frais (int id, String libelle, Date date, int quantite, Double montant){
        FR_ID = id;
        FR_libelle_libre = libelle;
        FR_date = date;
        FR_quantite = quantite;
        FR_montant = montant;
        FR_total = quantite*montant;
        FR_taxe = FR_total*0.2;
    }

    public void decrire(){
        System.out.println("Caractéristiques du frais-------");
        System.out.println("ID : "+FR_ID);
        System.out.println("Libellé : "+FR_libelle_libre);
        System.out.println("Quantité : "+FR_quantite);
        System.out.println("Montant : "+FR_montant);
        System.out.println("Total à rembourser : "+FR_total);
        System.out.println("Taxe : "+FR_taxe);
    }
}
