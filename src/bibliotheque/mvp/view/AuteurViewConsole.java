package bibliotheque.mvp.view;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.SpecialAuteurPresenter;
import bibliotheque.utilitaires.comparators.AuteurComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static bibliotheque.utilitaires.Utilitaire.*;

public class AuteurViewConsole extends AbstractViewConsole<Auteur> implements SpecialAuteurViewConsole {
    /*
    @Override
    public void setListDatas(List<Auteur> ldatas) {
        //Comparator<Auteur> cmp = new AuteurComparator();

        //  ldatas.sort(cmp);

        /* Enoncé V6 :
        Remplacez les codes de tri faisant appel à des Classes Comparator "concrètes"
        par des classes anonymes.

        ldatas.sort((o1, o2) -> {
            if(o1.getNom().compareTo(o2.getNom())!=0) return (o1.getNom().compareTo(o2.getNom()));
            return o1.getPrenom().compareTo(o2.getPrenom())  ;
        });

        //Lambda expressions
        /* Enoncé V7 : Remplacez les instructions de tri utilisant une classe anonyme
        par une lambda expression.

        Comparator<Auteur> cmp = (a1,a2)-> a1.getNom().compareTo(a2.getNom());
        cmp=cmp.thenComparing((a1,a2)-> a1.getPrenom().compareTo(a2.getPrenom()));
        ldatas.sort(cmp);


        super.setListDatas(ldatas);
    }
    */

    @Override
    protected void rechercher() {
        try {
            System.out.println("nom ");
            String nom = sc.nextLine();
            System.out.println("prénom ");
            String prenom = sc.nextLine();
            System.out.println("nationalité");
            String nat = sc.nextLine();
            Auteur rech = new Auteur(nom, prenom, nat);
            presenter.search(rech);
        } catch (Exception e) {
            System.out.println("erreur : " + e);
        }

    }

    @Override
    protected void modifier() {
        int choix = choixElt(ldatas);
        Auteur a = ldatas.get(choix - 1);
        do {
            try {
                String nom = modifyIfNotBlank("nom", a.getNom());
                String prenom = modifyIfNotBlank("prénom", a.getPrenom());
                String nat = modifyIfNotBlank("nationalité", a.getNationalite());
                a.setNom(nom);
                a.setPrenom(prenom);
                a.setNationalite(nat);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        } while (true);
        presenter.update(a);
        ldatas = presenter.getAll();//rafraichissement
        affListe(ldatas);

    }

    @Override
    protected void ajouter() {
        Auteur a = null;
        do {
            try {
                System.out.println("nom ");
                String nom = sc.nextLine();
                System.out.println("prénom ");
                String prenom = sc.nextLine();
                System.out.println("nationalité");
                String nat = sc.nextLine();
                a = new Auteur(nom, prenom, nat);
                presenter.add(a);
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : " + e.getMessage());
            }
        } while (true);


        //TODO attribuer ouvrages , les ouvrages sont triés par ordre de titre
    }

    @Override
    protected void special() {
        int choix = choixElt(ldatas);
        Auteur a = ldatas.get(choix - 1);

        List options = new ArrayList<>(Arrays.asList("lister ouvrages", "lister livres", "lister par genre", "fin"));
        do {
            int ch = choixListe(options);

            switch (ch) {

                case 1:
                    listerOuvrages(a);
                    break;
                case 2:
                    listerLivres(a);
                    break;
                case 3:
                    listerGenre(a);
                    break;
                case 4:
                    return;
            }
        } while (true);
    }

    @Override
    public void listerGenre(Auteur a) {
        System.out.println("genre :");
        String genre = sc.nextLine();
        ((SpecialAuteurPresenter) presenter).listerOuvrages(a, genre);
    }

    @Override
    public void listerOuvrages(Auteur a) {
        ((SpecialAuteurPresenter) presenter).listerOuvrages(a);

        List<Ouvrage> lOuvrage = new ArrayList<Ouvrage>();
        /* Enoncé V7 :
        2.Lors de l'affichage des livres et des ouvrages d'un auteur utilisez un stream
        et un filter pour sélectionner les éléments à retourner.
         */
        // Filtrage des ouvrages par auteur
        String auteurRecherche = a.getNom();
        List<Ouvrage> ouvragesDeAuteur = lOuvrage.stream().filter(ouvrage -> ouvrage.getLauteurs().equals(auteurRecherche)).toList();

        // Affichage des ouvrages filtrés
        ouvragesDeAuteur.forEach(ouvrage -> System.out.println(ouvrage.toString()));
    }

    @Override
    public void listerLivres(Auteur a) {
        TypeLivre[] tlv = TypeLivre.values();
        int ch2 = choixListe(List.of(tlv));
        TypeLivre tl = tlv[ch2 - 1];
        ((SpecialAuteurPresenter) presenter).listerLivre(a, tl);

        List<Livre> lLivre = new ArrayList<Livre>();
         /* Enoncé V7 :
        2.Lors de l'affichage des livres et des ouvrages d'un auteur utilisez un stream
        et un filter pour sélectionner les éléments à retourner.
         */
        // Filtrage des livres par auteur
        String auteurRecherche = a.getNom();
        List<Livre> livresDeAuteur = lLivre.stream().filter(livre -> livre.getLauteurs().equals(auteurRecherche)).toList();

        // Affichage des ouvrages filtrés
        livresDeAuteur.forEach(livre -> System.out.println(livre.toString()));
    }

}
