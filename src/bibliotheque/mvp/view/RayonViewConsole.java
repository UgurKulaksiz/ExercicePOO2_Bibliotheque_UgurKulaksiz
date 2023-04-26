package bibliotheque.mvp.view;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.RayonPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;
import static bibliotheque.utilitaires.Utilitaire.modifyIfNotBlank;

public class RayonViewConsole implements RayonViewInterface {
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
    public void setListDatas(List<Rayon> rayons) throws Exception {
        this.lRayon = rayons;
        Utilitaire.affListe(lRayon);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : " + msg);
    }

    @Override
    public void affList(List<Exemplaire> rayEx) {
        affListe(rayEx);
    }

    public void menu() throws Exception {
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

    private void rechercher() {
        try {
            System.out.println("Code rayon : ");
            String code = sc.nextLine();
            presenter.search(code);
        } catch (Exception e) {
            System.out.println("Erreur de recherche du rayon : " + e);
        }

    }

    private void modifier() throws Exception {

        int choix = choixElt(lRayon);
        Rayon ray = lRayon.get(choix - 1);
        String codeRayon = modifyIfNotBlank("Code rayon", ray.getCodeRayon());
        String genre = modifyIfNotBlank("Genre", ray.getGenre());

        Rayon rayon = new Rayon(codeRayon, genre);
        presenter.updateRayon(rayon);
        lRayon = presenter.getAll();//rafraichissement
        Utilitaire.affListe(lRayon);
    }

    private void retirer() throws Exception {
        int choix = Utilitaire.choixElt(lRayon);
        Rayon rayon = lRayon.get(choix - 1);
        presenter.removeRayon(rayon);
    }


    private void ajouter() {
        try {
            System.out.println("Code rayon ");
            String code = sc.nextLine();
            System.out.println("Genre ");
            String genre = sc.nextLine();

            Rayon ray = new Rayon(code, genre);
            presenter.addRayon(ray);
        } catch (Exception e) {
            System.out.println("Erreur d'ajout du rayon : " + e);
        }

    }

    //Méthode special()
    private void special() {
        int choix = choixElt(lRayon);
        Rayon ray = lRayon.get(choix - 1);
        do {
            System.out.println("1.Liste exemplaires \n2.menu principal");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    presenter.listerExemplaires();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Choix invalide recommencez ");
            }
        } while (true);
    }

}
