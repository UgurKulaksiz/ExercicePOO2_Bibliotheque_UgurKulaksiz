package bibliotheque.mvp.view;

import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.TypeOuvrage;
import bibliotheque.mvp.presenter.OuvragePresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OuvrageViewConsole implements OuvrageViewInterface{
    private OuvragePresenter presenter;
    private List<Ouvrage> lOuvrage;
    private Scanner sc = new Scanner(System.in);

    public OuvrageViewConsole() {

    }

    @Override
    public void setPresenter(OuvragePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Ouvrage> ouvrages) {
        this.lOuvrage = ouvrages;
        Utilitaire.affListe(lOuvrage);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information:" + msg);
    }

    public void menu() {
        List options = new ArrayList<>(Arrays.asList("Ajouter", "Retirer", "Modifier", "Fin"));
        do {
            int ch = Utilitaire.choixListe(options);

            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    modifier();
                    break;
                case 4:
                    System.exit(0);
            }
        } while (true);
    }

    private void modifier() {
        //TODO choisir elt et demander les nouvelles valeurs puis appeler méthode maj(lecteur) (à développer) du presenter

        System.out.println(lOuvrage);
        Utilitaire.affListe(lOuvrage);
        int choix = Utilitaire.choixElt(lOuvrage);
        Ouvrage ouvrage = lOuvrage.get(choix - 1);
        presenter.updateOuvrage(ouvrage);
        choixModif(ouvrage);
    }

    private void choixModif(Ouvrage ouvrage) {
        List options = new ArrayList<>(Arrays.asList("Titre", "Âge minimum", "Date de parution", "Type Ouvrage",
                "Prix de location", "Langue", "Genre"));

        do {
            int choix = Utilitaire.choixListe(options);
            switch (choix) {
                case 1:
                    System.out.println("Titre : ");
                    String titre = sc.nextLine();
                    ouvrage.setTitre(titre);
                    break;
                case 2:
                    System.out.println("Âge min : ");
                    int ageMin = sc.nextInt();
                    ouvrage.setAgeMin(ageMin);
                    break;
                case 3:
                    System.out.println("Date de parution : ");
                    String[] jma = sc.nextLine().split(" ");
                    int j = Integer.parseInt(jma[0]);
                    int m = Integer.parseInt(jma[1]);
                    int a = Integer.parseInt(jma[2]);
                    LocalDate dn = LocalDate.of(a, m, j);
                    ouvrage.setDateParution(dn);
                    break;
                case 4:
                    System.out.println("Type ouvrage :  ");
                    TypeOuvrage to = TypeOuvrage.valueOf(sc.nextLine());
                    ouvrage.setTo(to);
                    break;
                case 5:
                    System.out.println("Prix de location : ");
                    Double prixLoc = sc.nextDouble();
                    ouvrage.setPrixLocation(prixLoc);
                    break;
                case 6:
                    System.out.println("Langue : ");
                    String lang = sc.nextLine();
                    ouvrage.setLangue(lang);
                    break;
                case 7:
                    System.out.println("Genre : ");
                    String genre = sc.nextLine();
                    ouvrage.setGenre(genre);
                    break;
                case 8:
                    System.exit(0);
            }
        } while (true);

    }

    private void retirer() {
        int choix = Utilitaire.choixElt(lOuvrage);
        Ouvrage ouvrage = lOuvrage.get(choix - 1);
        presenter.removeOuvrage(ouvrage);
    }


    private void ajouter() {
        System.out.println("Titre ");
        String titre = sc.nextLine();
        System.out.println("Âge min ");
        int ageMin = sc.nextInt();
        System.out.println("Date de parution");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dn = LocalDate.of(a, m, j);
        System.out.println("Type Ouvrage");
        TypeOuvrage to = TypeOuvrage.valueOf(sc.nextLine());
        System.out.println("Prix de location");
        Double prixLoc = sc.nextDouble();
        System.out.println("Langue ");
        String langue = sc.nextLine();
        System.out.println("Genre ");
        String genre = sc.nextLine();

        Ouvrage ouvrage = new Ouvrage(titre, ageMin, dn, to, prixLoc, langue, genre) {
            @Override
            public double amendeRetard(int njours) {
                return 0;
            }

            @Override
            public int njlocmax() {
                return 0;
            }
        };
        presenter.addOuvrage(ouvrage);
    }
}
