package bibliotheque.mvp.view;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.Rayon;
import bibliotheque.metier.TypeOuvrage;
import bibliotheque.mvp.presenter.OuvragePresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.*;

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

    @Override
    public void affList(List<Exemplaire> ouvEx) {
        affListe(ouvEx);
    }

    @Override
    public void affList(Double njours) {
        affListe(Collections.singletonList(njours));
    }


    public void menu() {
        List options = new ArrayList<>(Arrays.asList("Ajouter", "Rechercher", "Retirer", "Modifier", "Spécial", "Fin"));
        do {
            int ch = Utilitaire.choixListe(options);

            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    rechercher();
                    break;
                case 3:
                    retirer();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    special();
                    break;
                case 6:
                    System.exit(0);
            }
        } while (true);
    }

    private void rechercher(){
        System.out.println("Titre ouvrage : ");
        String titre = sc.nextLine();
        presenter.search(titre);
    }

    private void modifier() {

        int choix = choixElt(lOuvrage);
        Ouvrage ouv = lOuvrage.get(choix-1);
        String titre = modifyIfNotBlank("Titre",ouv.getTitre());
        int ageMin = Integer.parseInt(modifyIfNotBlank("Âge min", String.valueOf(ouv.getAgeMin())));
        LocalDate dateParution = LocalDate.parse(modifyIfNotBlank("Date parution", String.valueOf(ouv.getDateParution())));
        TypeOuvrage to = TypeOuvrage.valueOf(modifyIfNotBlank("Type Ouvrage", String.valueOf(ouv.getTo())));
        Double prixLocation = Double.valueOf(modifyIfNotBlank("Prix location", String.valueOf(ouv.getPrixLocation())));
        String langue = modifyIfNotBlank("Langue",ouv.getLangue());
        String genre = modifyIfNotBlank("Genre",ouv.getGenre());

        Ouvrage ouvrage = new Ouvrage(titre, ageMin, dateParution, to, prixLocation, langue, genre) {
            @Override
            public double amendeRetard(int njours) {
                return 0;
            }

            @Override
            public int njlocmax() {
                return 0;
            }
        };
        presenter.updateOuvrage(ouv);
        lOuvrage=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lOuvrage);
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

    private void special() {
        Boolean enLocation = null;
        int njours = 0;

        int choix =  choixElt(lOuvrage);
        Ouvrage ouv = lOuvrage.get(choix-1);
        do {
            System.out.println("1.Liste exemplaires \n2.Liste exemplaires Boolean \n3.Amende en retard \n4.menu principal");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    presenter.listerExemplaires();
                    break;
                case 2:
                    presenter.listerExemplaires(enLocation);
                    break;
                case 3:
                    presenter.amendeRetard(njours);
                    break;
                case 4: return;
                default:
                    System.out.println("Choix invalide recommencez ");
            }
        } while (true);
    }
}
