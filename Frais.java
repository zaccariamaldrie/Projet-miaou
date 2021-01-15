public class Frais {
    
    int prix;
    String type;

    public Frais (){
        prix = 0;
        type="none";
    }

    public Frais (int prixFrais, String typeFrais){
        prix = prixFrais;
        type = typeFrais;
    }
}
