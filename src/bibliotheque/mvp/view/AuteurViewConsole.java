package bibliotheque.mvp.view;

import bibliotheque.metier.Auteur;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AuteurViewConsole implements AuteurViewInterface {
    private AuteurPresenter presenter;
    private List<Auteur> lAuteur;
    private Scanner sc = new Scanner(System.in);

    public AuteurViewConsole() {

    }

    @Override
    public void setPresenter(AuteurPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Auteur> auteurs) {
        this.lAuteur = auteurs;
        Utilitaire.affListe(lAuteur);
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
        //TODO choisir elt et demander les nouvelles valeurs puis appeler méthode maj(auteur) (à développer) du presenter

        System.out.println(lAuteur);
        Utilitaire.affListe(lAuteur);
        int choix = Utilitaire.choixElt(lAuteur);
        Auteur auteur = lAuteur.get(choix - 1);
        presenter.updateAuteur(auteur);
        choixModif(auteur);
    }

    private void choixModif(Auteur auteur) {
        List options = new ArrayList<>(Arrays.asList("Nom", "Prénom", "Nationalité"));

        do {
            int choix = Utilitaire.choixListe(options);
            switch (choix) {
                case 1:
                    System.out.println("Nom : ");
                    String nom = sc.nextLine();
                    auteur.setNom(nom);
                    break;
                case 2:
                    System.out.println("Prénom : ");
                    String prenom = sc.nextLine();
                    auteur.setPrenom(prenom);
                    break;
                case 3:
                    System.out.println("Nationalité : ");
                    String nationalite = sc.nextLine();
                    auteur.setNationalite(nationalite);
                    break;
                case 4:
                    System.exit(0);
            }
        } while (true);

    }

    private void retirer() {
        int choix = Utilitaire.choixElt(lAuteur);
        Auteur auteur = lAuteur.get(choix - 1);
        presenter.removeAuteur(auteur);
    }


    private void ajouter() {
        System.out.println("Nom ");
        String nom = sc.nextLine();
        System.out.println("Prénom ");
        String prenom = sc.nextLine();
        System.out.println("Nationalité ");
        String nationalite = sc.nextLine();

        Auteur aut = new Auteur(nom, prenom, nationalite);
        presenter.addAuteur(aut);
    }
}
