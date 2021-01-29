package src;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){

        SQLConnection con = new SQLConnection();
        con.connection();

        ArrayList<Utilisateur> users = new ArrayList();
        ArrayList<FicheFrais> fiches = new ArrayList();
        ArrayList<Frais> frais = new ArrayList();

        con.initialiser(users, fiches, frais);

        System.out.println("Bienvenue !");
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("------------------------------------------------------------------");
            System.out.println("1.Lister tout les frais\n2.Lister toutes les fiches de frais\n3.Ajouter un frais\n4.Ajouter une fiche de frais");
            System.out.println("------------------------------------------------------------------");
            int choice = sc.nextInt();
            if (choice == 1){
                for (int i = 0; i < frais.size(); i++ ){
                    frais.get(i).decrire();
                }
            } else if (choice == 2){
                for (int i = 0; i < fiches.size(); i++ ){
                    fiches.get(i).decrire();
                }
            } else if (choice == 3){
                con.createFrais(frais);
            } else if(choice == 4){
                con.createFicheFrais(fiches);
            } else {
                System.out.println("Erreur");
            }
        }
    }

}
