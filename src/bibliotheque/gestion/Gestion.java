package bibliotheque.gestion;

import bibliotheque.metier.*;
import bibliotheque.utilitaires.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Gestion {
    Scanner sc = new Scanner(System.in);

    private static List<Auteur> laut = new ArrayList<>();
    private static List<Lecteur> llect = new ArrayList<>();
    private static List<Ouvrage> louv = new ArrayList<>();
    private static List<Exemplaire> lex = new ArrayList<>();
    private static List<Rayon> lrayon = new ArrayList<>();
    private static List<Location> lloc = new ArrayList<>();


    public void populate() {
        Auteur a = new Auteur("Verne", "Jules", "France");
        laut.add(a);

        Livre l = new Livre("Vingt mille lieues sous les mers", 10, LocalDate.of(1880, 1, 1), 1.50, "français", "aventure", "a125", 350, TypeLivre.ROMAN, "histoire de sous-marin");
        louv.add(l);

        a.addOuvrage(l);

        a = new Auteur("Spielberg", "Steven", "USA");
        laut.add(a);

        DVD d = new DVD("AI", 12, LocalDate.of(2000, 10, 1), 2.50, "anglais", "SF", 4578l, LocalTime.of(2, 0, 0), (byte) 2);
        d.getAutresLangues().add("français");
        d.getAutresLangues().add("italien");
        d.getSousTitres().add("néerlandais");
        louv.add(d);

        a.addOuvrage(d);

        a = new Auteur("Kubrick", "Stanley", "GB");
        laut.add(a);

        a.addOuvrage(d);


        CD c = new CD("The Compil 2023", 0, LocalDate.of(2023, 1, 1), 2, "English", "POP", 1245, (byte) 20, LocalTime.of(1, 40, 0));
        louv.add(c);

        Rayon r = new Rayon("r12", "aventure");
        lrayon.add(r);

        Exemplaire e = new Exemplaire("m12", "état neuf", l);
        lex.add(e);
        e.setRayon(r);


        r = new Rayon("r45", "science fiction");
        lrayon.add(r);

        e = new Exemplaire("d12", "griffé", d);
        lex.add(e);

        e.setRayon(r);


        Lecteur lec = new Lecteur(1, "Dupont", "Jean", LocalDate.of(2000, 1, 4), "Mons", "jean.dupont@mail.com", "0458774411");
        llect.add(lec);

        Location loc = new Location(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 3, 1), lec, e);
        lloc.add(loc);
        loc.setDateRestitution(LocalDate.of(2023, 2, 4));

        lec = new Lecteur(1, "Durant", "Aline", LocalDate.of(1980, 10, 10), "Binche", "aline.durant@mail.com", "045874444");
        llect.add(lec);

        loc = new Location(LocalDate.of(2023, 2, 5), LocalDate.of(2023, 3, 5), lec, e);
        lloc.add(loc);
    }

    private void menu() {
        //Permet de construire une liste avec toutes les options du menu
        List options = new ArrayList<>(Arrays.asList("auteurs", "ouvrages", "exemplaires", "rayons", "lecteurs", "locations", "fin"));

        do {
            int choix = Utilitaire.choixListe(options);

            switch (choix) {
                case 1:
                    gestAuteurs();
                    break;
                case 2:
                    gestOuvrages();
                    break;
                case 3:
                    gestExemplaires();
                    break;
                case 4:
                    gestRayons();
                    break;
                case 5:
                    gestLecteurs();
                    break;
                case 6:
                    gestLocations();
                    break;
                case 7:
                    gestRestitution();
                    break;
                default:
                    System.exit(0);
            }
        } while (true);
    }

    private void gestRestitution() {
        //TODO lister exemplaires en location , choisir l'un d'entre eux, enregistrer sa restitution et éventuellement changer état

        int choix;
        //lister exemplaires en location
        List<Exemplaire> listeExemplairesLocation = new ArrayList<>();
        for (Exemplaire exemplaire : lex) {
            if (exemplaire.enLocation()) {
                listeExemplairesLocation.add(exemplaire);
            }
        }

        //choisir l'un d'entre eux, enregistrer sa restitution et éventuellement changer état
        choix = Utilitaire.choixListe(listeExemplairesLocation);
        Exemplaire exemplaire = listeExemplairesLocation.get(choix - 1);
        exemplaire.setDescriptionEtat(exemplaire.getDescriptionEtat());

    }

    private void gestLocations() {
        //TODO ne lister que les exemplaires libres et les trier par matricule

        Collections.sort(lex, new Comparator<Exemplaire>() { //Méthode de trie par matricule
            @Override
            public int compare(Exemplaire e1, Exemplaire e2) {
                return e1.getMatricule().compareTo(e2.getMatricule());
            }
        });
        List<Exemplaire> listeExemplairesLibres = new ArrayList<>();
        for (Exemplaire exemplaire : lex) { //Parcourir la liste des exemplaires
            if (!exemplaire.enLocation()) { //Si l'exemplaire n'est pas en location
                listeExemplairesLibres.add(exemplaire); //Ajout à la liste libre, l'exemplaire qui n'est pas en location
            }
        }

        //OKTODO lister exemplaires,lister lecteurs,créer la location avec le constructeur à deux paramètres(loueur,exemplaire)

        int choix;
        choix = Utilitaire.choixListe(lex);
        if (lex.get(choix).enLocation()) {
            System.out.println("exemplaire en location");
            return;
        }
        Exemplaire ex = lex.get(choix - 1);
        choix = Utilitaire.choixListe(llect);
        Lecteur lec = llect.get(choix - 1);
        lloc.add(new Location(lec, ex));

    }

    private void gestLecteurs() {
        System.out.println("numéro");
        int num = sc.nextInt();
        sc.skip("\n");
        System.out.println("nom ");
        String nom = sc.nextLine();
        System.out.println("prénom ");
        String prenom = sc.nextLine();
        System.out.println("date de naissance");
        String[] jma = sc.nextLine().split(" "); //Diviser les variables(jour, mois et année) par un espace (" ")
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dn = LocalDate.of(a, m, j); //Transformer les variables(jour, mois et année) sous forme de date
        System.out.println("adresse");
        String adr = sc.nextLine();
        System.out.println("mail");
        String mail = sc.nextLine();
        System.out.println("tel ");
        String tel = sc.nextLine();
        Lecteur lect = new Lecteur(num, nom, prenom, dn, adr, mail, tel); //Création du lecteur
        llect.add(lect); //Ajout du lecteur créé dans la liste lecteur
        System.out.println("Lecteur créé");
    }

    private void gestRayons() {
        System.out.println("code ");
        String code = sc.next();
        System.out.println("genre ");
        String genre = sc.next();
        Rayon r = new Rayon(code, genre);
        System.out.println("Rayon créé");

        //TODO attribuer exemplaire, les exemplaires sont triés par ordre de titre de l'ouvrage , empêcher doublons sur l'exemplaire

        System.out.println("Combien d'exemplaires voulez-vous ajouter au rayon ?");
        int nbExemplaires = sc.nextInt();

        Exemplaire e = null;
        for (int i = 0; i < nbExemplaires; i++) {
            System.out.println("Création d'un nouvel exemplaire pour le rayon " + code + " - " + genre);
            gestExemplaires();
            r.addExemplaire(e);
        }
    }

    private void gestExemplaires() {
        System.out.println("matricule ");
        String mat = sc.next();
        System.out.println("etat  ");
        String etat = sc.next();
        System.out.println("ouvrage ");
        for (int i = 0; i < louv.size(); i++) {
            System.out.println((i + 1 + "." + louv.get(i)));
        }
        int choix;
        do {
            System.out.println("choix :");
            choix = sc.nextInt();
            sc.skip("\n");
        } while (choix < 1 || choix > louv.size());
        Exemplaire ex = new Exemplaire(mat, etat, louv.get(choix - 1)); //Création de l'exemplaire avec la matricule, l'état et le choix du type d l'ouvrage
        lex.add(ex); //Ajout de l'exemplaire créé à la liste d'exemplaire
        System.out.println("Exemplaire créé");

        //TODO attribuer rayon , les rayons sont triès par ordre de code

        System.out.println("Création d'un rayon pour l'exemplaire " + ex + " : ");
        List<Rayon> listeRayon = new ArrayList<>();
        for (Rayon r : lrayon) {
            gestRayons();
            r.addExemplaire(ex);
        }
        Collections.sort(lrayon, new Comparator<Rayon>() {
            @Override
            public int compare(Rayon rayon1, Rayon rayon2) {
                return rayon1.getCodeRayon().compareTo(rayon2.getCodeRayon());
            }
        });

        for (int i = 0; i < lrayon.size(); i++) {
            System.out.println((i + 1 + "." + lrayon.get(i)));
        }

    }

    private void gestOuvrages() {
        /*
        Ouvrage o = null;
        System.out.println("titre");
        String titre= sc.nextLine();
        System.out.println("age minimum");
        int ageMin= sc.nextInt();
        sc.skip("\n");
        System.out.println("date de parution");

        LocalDate dp= Utilitaire.lecDate();
        System.out.println("prix de location");
        double ploc = sc.nextDouble();
        sc.skip("\n");
        System.out.println("langue");
        String langue=sc.nextLine();
        System.out.println("genre");
        String genre=sc.nextLine();
        TypeOuvrage[] tto = TypeOuvrage.values();
        List<TypeOuvrage> lto = new ArrayList<>(Arrays.asList(tto));
        int choix = Utilitaire.choixListe(lto);
        switch (choix){
                case 1 :
                           System.out.println("isbn ");
                           String isbn = sc.next();
                           System.out.println("pages ");
                           int nbrePages = sc.nextInt();
                           sc.skip("\n");
                           TypeLivre[] ttl = TypeLivre.values();
                           List<TypeLivre> ltl = new ArrayList<>(Arrays.asList(ttl));
                            choix = Utilitaire.choixListe(ltl);
                            TypeLivre tl = ttl[choix-1];
                           System.out.println("résumé du livre :");
                           String resume = sc.nextLine();
                           o=new Livre(titre,ageMin,dp,ploc,langue,genre,isbn,nbrePages,tl,resume);
                           ;break;
                case 2 :
                            System.out.println("code : ");
                            long code= sc.nextLong();
                            System.out.println("nombre de plages :");
                            byte nbrePlages= sc.nextByte();
                            LocalTime dureeTotale = Utilitaire.lecTime();
                            o=new CD(titre,ageMin,dp,ploc,langue,genre,code,nbrePlages,dureeTotale);
                            ;break;
                case 3 :
                            System.out.println("code : ");
                            code= sc.nextLong();
                            dureeTotale=Utilitaire.lecTime();
                            byte nbreBonus= sc.nextByte();
                            o=new DVD(titre,ageMin,dp,ploc,langue,genre,code,dureeTotale,nbreBonus);
                            System.out.println("autres langues");
                            List<String> langues = new ArrayList<>(Arrays.asList("anglais","français","italien","allemand","fin"));
                            do{
                                choix=Utilitaire.choixListe(langues);
                                if(choix==langues.size())break;
                                ((DVD)o).getAutresLangues().add(langues.get(choix-1)); //NOTODO vérifier unicité ou utiliser set et pas de doublon avec langue d'origine
                            }while(true);
                           System.out.println("sous-titres");
                            do{
                             choix=Utilitaire.choixListe(langues);
                             if(choix==langues.size())break;
                             ((DVD)o).getSousTitres().add(langues.get(choix-1)); //NOTODO vérifier unicité ou utiliser set
                             }while(true);
                            ;break;
            }
            */

        TypeOuvrage[] to = TypeOuvrage.values();
        List<TypeOuvrage> lTO = new ArrayList<>(Arrays.asList(to));
        int choix;
        choix = Utilitaire.choixListe(lTO);
        Ouvrage ouvrage = null;

        /*
        switch (choix){
            case 1 : ouvrage = new LivreFactoryBeta().create();
                break;
            case 2 : ouvrage = new CDFactoryBeta().create();
                break;
            case 3 : ouvrage = new DVDFactoryBeta().create():
                break;
        }
         */

        List<OuvrageFactory> lof = new ArrayList<>(Arrays.asList(new LivreFactory(), new CDFactory(), new DVDFactory()));
        ouvrage = lof.get(choix - 1).create();
        louv.add(ouvrage);
        System.out.println("Ouvrage créé");

        //TODO attribuer auteurs, les auteur sont triés par odre de nom et prénom, empêcher doublons

        System.out.println("Création d'un auteur ");
        List<Auteur> listeAuteur = new ArrayList<>();
        for (Auteur auteur : laut) {
            gestAuteurs();
            auteur.addOuvrage(ouvrage);
        }
        Collections.sort(laut, new Comparator<Auteur>() {
            @Override
            public int compare(Auteur auteur1, Auteur auteur2) {
                return (auteur1.getNom().compareTo(auteur2.getNom()));
            }
        });
        Collections.sort(laut, new Comparator<Auteur>() {
            @Override
            public int compare(Auteur auteur1, Auteur auteur2) {
                return (auteur1.getPrenom().compareTo(auteur2.getPrenom()));
            }
        });

        for (int i = 0; i < laut.size(); i++) {
            System.out.println((i + 1 + "." + laut.get(i)));
        }


    }

    private void gestAuteurs() {
        System.out.println("nom ");
        String nom = sc.nextLine();
        System.out.println("prénom ");
        String prenom = sc.nextLine();
        System.out.println("nationalité");
        String nat = sc.nextLine();
        Auteur a = new Auteur(nom, prenom, nat);
        laut.add(a);
        System.out.println("Écrivain créé");

        //TODO attribuer ouvrages , les ouvrages sont triés par ordre de titre

        System.out.println("Création d'un ouvrage ");
        List<Ouvrage> listeOuvrage = new ArrayList<>();
        for (Ouvrage ouvrage : louv) {
            gestOuvrages();
            ouvrage.addAuteur(a);
        }
        Collections.sort(louv, new Comparator<Ouvrage>() {
            @Override
            public int compare(Ouvrage ouvrage1, Ouvrage ouvrage2) {
                return ouvrage1.getTitre().compareTo(ouvrage2.getTitre());
            }
        });

        for (int i = 0; i < louv.size(); i++) {
            System.out.println((i + 1 + "." + louv.get(i)));
        }
    }

    public static void main(String[] args) {
        Gestion g = new Gestion();
        g.populate();
        g.menu();
    }


}
