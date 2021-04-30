package fr.miaou;

public class Vehicule {
    
    private String ve_immat;
    private String ve_marque;
    private String ve_model;
    private int fk_fkm;

    public Vehicule(String immat, String marque, String model, int fkm){
        this.ve_immat = immat;
        this.ve_marque = marque;
        this.ve_model = model;
        this.fk_fkm = fkm;
    }

    public String getImmat(){
        return this.ve_immat;
    }

    public String getMarque(){
        return this.ve_marque;
    }

    public String getModel(){
        return this.ve_model;
    }

    public int getFkm(){
        return this.fk_fkm;
    }

    public void decricreVehicule(){
        System.out.println("Description du vehicule");
        System.out.println("Immatriculation du vehicule : "+ve_immat);
        System.out.println("Marque du vehicule : "+ve_marque);
        System.out.println("Model du vehicule : "+ve_model);
    }
}
