import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){

        SQLConnection con = new SQLConnection();
        con.connection();

        ArrayList<Agent> agents = new ArrayList();
        ArrayList<FicheFrais> fiches = new ArrayList();
        ArrayList<Frais> frais = new ArrayList();

        con.initialiser(agents, fiches, frais);

        System.out.println("Bienvenue !");
        Scanner sc = new Scanner(System.in);

        int id = 0;

        while (id == 0){
            System.out.println("---------------------------------------");
            System.out.println("Entrez votre identifiant : ");
            String login = sc.nextLine();
            System.out.println("Entrez votre mot de passe : ");
            String mdp = sc.nextLine();
            id = con.connectAccount(login, mdp);
            if (id == 0){
                System.out.println("Votre identifiant ou mot de passe ne correspond pas.");
            } else {
                System.out.println("Bienvenue "+con.agGetNom(id)+" !");
            }
        }

        if (con.agTypeAgent(id) == 1){
            while(true){
                System.out.println("------------------------------------------------------------------");
                System.out.println("1.Lister vos frais\n2.Lister vos fiches de frais\n3.Ajouter un frais\n4.Ajouter une fiche de frais");
                System.out.println("------------------------------------------------------------------");
                int choice = sc.nextInt();
                if (choice == 1){
                    System.out.println("Frais de "+con.agGetNom(id)+" ---------------");
                    for (Frais fr : frais){
                        fr.decrireFrais(id);
                    }
                } else if (choice == 2){
                    System.out.println("Fiches de frais de "+con.agGetNom(id)+" ---------------");
                    for (FicheFrais fiche : fiches){
                        fiche.decrireFiche(id);
                    }
                } else if (choice == 3){
                    con.createFrais(frais, id);
                } else if(choice == 4){
                    con.createFicheFrais(fiches, id);
                } else {
                    System.out.println("Erreur");
                }
            }
        } else if (con.agTypeAgent(id) == 2){
            while (true){
                System.out.println("Selectionner un client à administrer : ");
            }
        }
        
    }

}
