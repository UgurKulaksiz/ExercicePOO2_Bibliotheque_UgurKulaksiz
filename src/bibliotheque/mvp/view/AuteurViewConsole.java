package bibliotheque.mvp.view;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

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
    public void setListDatas(List<Auteur> auteurs) throws Exception {
        this.lAuteur = auteurs;
        Utilitaire.affListe(lAuteur);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information:" + msg);
    }

    @Override
    public void affList(List<Ouvrage> lOuvrage) {
        affListe(lOuvrage);
    }

    public void menu() throws Exception {
        List options = new ArrayList<>(Arrays.asList("Ajouter", "Retirer", "Rechercher", "Modifier", "Spécial", "Fin"));
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
                    rechercher();
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
        try{
            System.out.println("Nom : ");
            String nom = sc.nextLine();
            System.out.println("Prénom : ");
            String prenom = sc.next();
            System.out.println("Nationalité : ");
            String nationalite = sc.nextLine();
            presenter.search(nom, prenom, nationalite);
        }catch (Exception e){
            System.out.println("Erreur de recherche d'auteur : "+e.getMessage());
        }

    }

    private void modifier() throws Exception {
        int choix = choixElt(lAuteur);
        Auteur auteur = lAuteur.get(choix-1);
        String nom = modifyIfNotBlank("Nom",lAuteur.get(lAuteur.indexOf(auteur)).getNom());
        String prenom = modifyIfNotBlank("Prénom",lAuteur.get(lAuteur.indexOf(auteur)).getPrenom());
        String nationalite = modifyIfNotBlank("Nationalité",lAuteur.get(lAuteur.indexOf(auteur)).getNationalite());

        Auteur aut = new Auteur(nom, prenom, nationalite);
        presenter.updateAuteur(aut);
        lAuteur=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lAuteur);
    }

    private void retirer() throws Exception {
        int choix = choixElt(lAuteur);
        Auteur auteur = lAuteur.get(choix-1);
        presenter.removeAuteur(auteur);
        lAuteur=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lAuteur);
    }


    private void ajouter() {
        try{
            System.out.println("Nom ");
            String nom = sc.nextLine();
            System.out.println("Prénom ");
            String prenom = sc.nextLine();
            System.out.println("Nationalité ");
            String nationalite = sc.nextLine();

            Auteur aut = new Auteur(nom, prenom, nationalite);
            presenter.addAuteur(aut);
        }catch (Exception e){
            System.out.println("Erreur d'ajout d'auteur : "+e.getMessage());
        }

    }

    private void special() {
        TypeOuvrage to = null;
        TypeLivre tl = null;
        String genre = null;

        int choix =  choixElt(lAuteur);
        Auteur aut = lAuteur.get(choix-1);
        do {
            System.out.println("1.Liste ouvrage sans paramètre\n2.Lister ouvrage par type d'ouvrage et type de livre" +
                    "\n3.Lister ouvrage par genre\n4.menu principal");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    presenter.listerOuvrages();
                    break;
                case 2:
                    presenter.listerOuvrages(to, tl);
                    break;
                case 3:
                    presenter.listerOuvrage(genre);
                    break;
                case 4: return;
                default:
                    System.out.println("Choix invalide recommencez ");
            }
        } while (true);


    }
}
