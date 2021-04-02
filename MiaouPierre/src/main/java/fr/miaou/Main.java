package fr.miaou;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){

        /* 
        Creation de la connection avec la base de données
        */

        SQLConnection con = new SQLConnection();
        con.connection();

        /* 
        Creation des array dans lesquels seront gérés les objets
        */

        ArrayList<Agent> agents = new ArrayList<>();
        ArrayList<FicheFrais> fiches = new ArrayList<>();
        ArrayList<Frais> frais = new ArrayList<>();
        ArrayList<TypeRefus> typeRefus = new ArrayList<>();
        ArrayList<Vehicule> vehicules = new ArrayList<>();

        con.initialiser(agents, fiches, frais, typeRefus, vehicules);

        System.out.println("Bienvenue !");
        Scanner sc = new Scanner(System.in);
        Scanner cs = new Scanner(System.in);

        int id = 0;

        /* ==================================================================================

        =============================== Début du traitement =================================
        
        ================================================================================== */ 

        while (id == 0){
            System.out.println("---------------------------------------");
            System.out.println("Entrez votre identifiant : ");
            String login = sc.nextLine();
            System.out.println("Entrez votre mot de passe : ");
            String mdp = sc.nextLine();

            /* 
            Teste l'identifiant et le mot de passe, si ils correspondent à un couple
            identifiant / mot de passe, on stock l'id de l'utilisateur en question
            dans la variable id
            */

            id = con.connectAccount(login, mdp);



            if (id == 0){
                System.out.println("Votre identifiant ou mot de passe ne correspond pas.");
            } else {
                System.out.println("Bienvenue "+con.agGetNom(id)+" !");
            }
        }

        /* ==================================================================================

        ============================ Fin de l'authentification ==============================
        
        ================================================================================== */ 



        /* ==================================================================================

        ================================= Partie Visiteur ===================================
        
        ================================================================================== */ 

        /* 
        Teste le type d'agent
        */

        if (con.agTypeAgent(id) == 1){
            while(true){
                System.out.println("------------------------------------------------------------------");
                System.out.println("1.Décrire une fiche de frais\n2.Lister vos fiches de frais\n3.Ajouter un frais\n4.Ajouter une fiche de frais\n5.Supprimer un frais\n6.Envoyer une fiche\n7.Changer de vehicule");
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
                    System.out.println("Outil de création d'un Frais -----");
                    System.out.println("Mois de la fiche du frais (en nombre): ");
                    int mois = sc.nextInt();
                    System.out.println("Libellé du frais : ");
                    String libelle = cs.nextLine();
                    System.out.println("Quantité : ");
                    int quantite = sc.nextInt();
                    System.out.println("Prix : ");
                    double prix = sc.nextDouble();
                    con.createFrais(frais, id, libelle, quantite, prix, mois);
                } else if(choice == 4){
                    System.out.println("Outil de création de Fiche de frais -----");
                    System.out.println("Mois de la fiche de frais (en nombre) : ");
                    int mois = sc.nextInt();
                    con.createFicheFrais(fiches, id, mois);
                } else if(choice == 5){
                    System.out.println("Selectionner l'id du frais à supprimer : ");
                    choice = sc.nextInt();
                    con.deleteFrais(frais, choice);
                } else if(choice == 6){
                    System.out.println("Selectionner le mois de la fiche de frais à envoyer : ");
                    choice = sc.nextInt();
                    for (FicheFrais fiche : fiches){
                        if(fiche.getAg() == id && fiche.getFdrmMois() == choice){
                            con.envoyerFiche(fiche);
                        }
                    }
                } else if (choice == 7){
                    System.out.println("Liste des vehicules disponibles dans la base de données : ");
                    for (Vehicule vehicule : vehicules){
                        vehicule.decricreVehicule();
                    }
                    System.out.println("Veuillez choisir le vehicule souhaité (immatriculation) : ");
                    String immat = cs.nextLine();
                    con.changeVehicule(agents, id, immat);
                }else {
                    System.out.println("Erreur");
                }
            }

        /* ==================================================================================

        ================================= Partie Comptable ==================================
        
        ================================================================================== */ 

        /* 
        Teste le type d'agent
        */

        } else if (con.agTypeAgent(id) == 2){
            int idClient;
            while (true){
                System.out.println("1.Administrer un visiteur\n2.Gérer la banque de véhicule");
                int choix = sc.nextInt();
                if (choix == 1){
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
                    int choiceFiche = sc.nextInt();
                    System.out.println("Quelle action voulez vous éffectuer sur la fiche de frais ?");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("1.Traiter les frais de la fiche de frais\n2.Valider la fiche de frais");
                    System.out.println("------------------------------------------------------------------");
                    int choice3 = sc.nextInt();
                    if (choice3 == 1){
                        System.out.println("Frais de "+con.agGetNom(idClient)+" pour le mois choisit ---------------");
                        for (Frais fr : frais){
                            fr.decrireFrais(idClient, choiceFiche);
                        }
                        System.out.println("Selectionner le frais que vous voulez administrer :");
                        int choiceFrais = sc.nextInt();
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("1.Valider le frais\n2.Refuser le frais");
                        System.out.println("------------------------------------------------------------------");
                        choice3 = sc.nextInt();
                        if(choice3 == 1){
                            for (Frais fr : frais){
                                if(fr.getIdFrais() == choiceFrais){
                                    con.setStatusFrais(fr, 1);
                                }
                            }
                        } else if (choice3 == 2){
                            for (Frais fr : frais){
                                if(fr.getIdFrais() == choiceFrais){
                                    con.createTypeRefus(typeRefus, fr);
                                    con.setStatusFrais(fr, 2);
                                }
                            }
                        } else {
                            System.out.println("Erreur choix");
                        }
                    } else if (choice3 == 2){
                        boolean ok = true;
                        for (Frais fr : frais){
                            if(fr.getFdrmAg() == idClient && fr.getFdrmMois() == choiceFiche){
                                if(fr.getStatus() == 0){
                                    ok = false;
                                }
                            }
                        }
                        if(ok == true){
                            for (FicheFrais fiche : fiches){
                                if(fiche.getAg() == idClient && fiche.getFdrmMois() == choiceFiche){
                                    con.validerFiche(fiche);
                                }
                            }
                        } else {
                            System.out.println("Tout les frais n'ont pas été vérifiés, la fiche ne peut pas être validée.");
                        }
                    } else {
                        System.out.println("Erreur choix");
                    }
                } else {
                    System.out.println("Cet agent n'est pas présent dans la base de données.");
                }
                } else {
                    System.out.println("1.Lister les vehicules\n2.Ajouter un vehicule\n3.Supprimer un vehicule");
                    choix = sc.nextInt();
                    if (choix == 1){
                        for (Vehicule vehicule : vehicules){
                            vehicule.decricreVehicule();
                        }
                    } else if (choix == 2){
                        System.out.println("Outil de création d'un vehicule -----");
                        System.out.println("Immatriculation du vehicule : ");
                        String immat = cs.nextLine();
                        System.out.println("Marque du vehicule : ");
                        String marque = cs.nextLine();
                        System.out.println("Model du vehicule : ");
                        String model = cs.nextLine();
                        con.createVehicule(vehicules, immat, marque, model);
                    } else {
                        System.out.println("Immatriculation du vehicule qui doit être supprimé : ");
                        String immat = sc.nextLine();
                        con.deleteVehicule(vehicules, immat);
                    }
                }
            }
        }
        
    }

}
