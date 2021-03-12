package fr.miaou;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){

        SQLConnection con = new SQLConnection();
        con.connection();

        ArrayList<Agent> agents = new ArrayList<>();
        ArrayList<FicheFrais> fiches = new ArrayList<>();
        ArrayList<Frais> frais = new ArrayList<>();

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
                System.out.println("1.Décrire une fiche de frais\n2.Lister vos fiches de frais\n3.Ajouter un frais\n4.Ajouter une fiche de frais\n5.Supprimer un frais\n6.Envoyer une fiche");
                System.out.println("------------------------------------------------------------------");
                int choice = sc.nextInt();
                if (choice == 1){
                    System.out.println("Selectionner le mois de la fiche de frais : ");
                    choice = sc.nextInt();
                    System.out.println("Frais de "+con.agGetNom(id)+" pour le mois choisit ---------------");
                    for (Frais fr : frais){
                        fr.decrireFrais(id, choice);
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
                } else if(choice == 5){
                    System.out.println("Selectionner l'id du frais à supprimer : ");
                    choice = sc.nextInt();
                    con.deleteFrais(choice);
                } else if(choice == 6){
                    System.out.println("Selectionner le mois de la fiche de frais à envoyer : ");
                    choice = sc.nextInt();
                    for (FicheFrais fiche : fiches){
                        if(fiche.getAg() == id && fiche.getFdrmMois() == choice){
                            con.envoyerFiche(fiche);
                        }
                    }
                } else {
                    System.out.println("Erreur");
                }
            }
        } else if (con.agTypeAgent(id) == 2){
            int idClient;
            while (true){
                idClient = 0;
                for (Agent agent : agents){
                    agent.decrireAgent();
                }
                System.out.println("Selectionner un visiteur à administrer : ");
                String choice = sc.nextLine();
                for (Agent agent : agents){
                    if (agent.getNom().equals(choice)){
                        idClient = agent.getId();
                        System.out.println("Vous avez choisi d'administrer "+agent.getNom());
                    }
                }
                if (idClient != 0){
                    System.out.println("Fiches de frais du visiteur ---------------");
                    for (FicheFrais fiche : fiches){
                        fiche.decrireFiche(idClient);
                    }
                    System.out.println("Selectionner la fiche de frais que vous souhaitez administrer :");
                    int choice2 = sc.nextInt();
                    System.out.println("Quelle action voulez vous éffectuer sur la fiche de frais ?");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("1.Traiter les frais de la fiche de frais\n2.Valider la fiche de frais");
                    System.out.println("------------------------------------------------------------------");
                    choice2 = sc.nextInt();
                    if (choice2 == 1){
                        System.out.println("Frais de "+con.agGetNom(idClient)+" pour le mois choisit ---------------");
                        for (Frais fr : frais){
                            fr.decrireFrais(idClient, choice2);
                        }
                        System.out.println("Selectionner le frais que vous voulez administrer :");
                        int choiceFrais = sc.nextInt();
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("1.Valider le frais\n2.Refuser le frais");
                        System.out.println("------------------------------------------------------------------");
                        choice2 = sc.nextInt();
                        if(choice2 == 1){
                            for (Frais fr : frais){
                                if(fr.getIdFrais() == choiceFrais){
                                    fr.setStatus(1);
                                }
                            }
                        } else if (choice2 == 2){
                            System.out.println("Motif du refus : ");
                            choice = sc.nextLine();
                            for (Frais fr : frais){
                                if(fr.getIdFrais() == choiceFrais){

                                }
                            }
                        } else {
                            System.out.println("Erreur");
                        }
                    } else if (choice2 == 2){
                        
                    } else {
                        System.out.println("Erreur");
                    }
                } else {
                    System.out.println("Cet agent n'est pas présent dans la base de données.");
                }
            }
        }
        
    }

}
