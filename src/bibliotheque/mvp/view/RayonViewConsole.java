package bibliotheque.mvp.view;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Rayon;
import bibliotheque.mvp.presenter.RayonPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;
import static bibliotheque.utilitaires.Utilitaire.modifyIfNotBlank;

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

    @Override
    public void affList(List<Rayon> ray) {
        affListe(ray);
    }

    public void menu() {
        List options = new ArrayList<>(Arrays.asList("Ajouter", "Retirer", "Modifier", "Spécial", "Fin"));
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
                    special();
                    break;
                case 5:
                    System.exit(0);
            }
        } while (true);
    }

    private void rechercher(){
        System.out.println("Code rayon : ");
        String code = sc.nextLine();
        presenter.search(code);
    }

    private void modifier() {

        int choix = choixElt(lRayon);
        Rayon ray = lRayon.get(choix-1);
        String codeRayon = modifyIfNotBlank("Code rayon",ray.getCodeRayon());
        String genre = modifyIfNotBlank("Genre",ray.getGenre());

        Rayon rayon = new Rayon(codeRayon, genre);
        presenter.updateRayon(rayon);
        lRayon=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lRayon);
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

    //Méthode special()

}
