package bibliotheque.mvp.view;

import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Rayon;
import bibliotheque.mvp.presenter.LecteurPresenter;
import bibliotheque.mvp.presenter.RayonPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RayonViewConsole implements RayonViewInterface{
    private RayonPresenter presenter;
    private List<Rayon> lRayon;
    private Scanner sc = new Scanner(System.in);

    public RayonViewConsole() {
    }

    @Override
    public void setPresenter(RayonPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Rayon> rayons) {
        this.lRayon = rayons;
        Utilitaire.affListe(lRayon);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : " + msg);
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
        //TODO choisir elt et demander les nouvelles valeurs puis appeler méthode maj(rayon) (à développer) du presenter

        System.out.println(lRayon);
        Utilitaire.affListe(lRayon);
        int choix = Utilitaire.choixElt(lRayon);
        Rayon rayon = lRayon.get(choix - 1);
        presenter.updateRayon(rayon);
        choixModif(rayon);
    }

    private void choixModif(Rayon ray) {
        List options = new ArrayList<>(Arrays.asList("Code rayon", "Genre"));

        do {
            int choix = Utilitaire.choixListe(options);
            switch (choix) {
                case 1:
                    System.out.println("Code : ");
                    String codeR = sc.nextLine();
                    ray.setCodeRayon(codeR);
                    break;
                case 2:
                    System.out.println("Genre : ");
                    String genre = sc.nextLine();
                    ray.setGenre(genre);
                    break;
                case 3:
                    System.exit(0);
            }
        } while (true);

    }

    private void retirer() {
        int choix = Utilitaire.choixElt(lRayon);
        Rayon rayon = lRayon.get(choix - 1);
        presenter.removeRayon(rayon);
    }


    private void ajouter() {
        System.out.println("Code rayon ");
        String code = sc.nextLine();
        System.out.println("Genre ");
        String genre = sc.nextLine();

        Rayon ray = new Rayon(code, genre);
        presenter.addRayon(ray);
    }
}
