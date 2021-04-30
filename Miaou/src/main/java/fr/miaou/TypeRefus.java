package fr.miaou;

public class TypeRefus {
    
    private int tre_id;
    private String tre_libelle;

    public TypeRefus(int id, String libelle){
        this.tre_id = id;
        this.tre_libelle = libelle;
    }

    public int getIdTre(){
        return this.tre_id;
    }

    public String getLibelleTre(){
        return this.tre_libelle;
    }
}
