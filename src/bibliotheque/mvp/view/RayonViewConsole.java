package bibliotheque.mvp.view;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.SpecialRayonPresenter;
import bibliotheque.utilitaires.comparators.RayonComparator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static bibliotheque.utilitaires.Utilitaire.*;

public class RayonViewConsole extends AbstractViewConsole<Rayon> implements SpecialRayonViewConsole {
   /*
    @Override
    public void setListDatas(List<Rayon> ldatas) {
        /*Comparator<Rayon> cmp = new RayonComparator();
        ldatas.sort(cmp);
        super.setListDatas(ldatas);
         */

        //Lambda expressions
        /* Enoncé V7 : Remplacez les instructions de tri utilisant une classe anonyme
        par une lambda expression.

        Comparator<Rayon> cmp = (ray1,ray2)-> ray1.getGenre().compareTo(ray2.getGenre());
        ldatas.sort(cmp);
    }
    */


    @Override
    protected void rechercher() {
        System.out.println("code du rayon : ");
        String code = sc.nextLine();
        Rayon rech = new Rayon(code);
        presenter.search(rech);
    }

    @Override
    protected void modifier() {
        int choix = choixElt(ldatas);
        Rayon r = ldatas.get(choix - 1);
        do {
            try {
                String genre = modifyIfNotBlank("nom", r.getGenre());
                r.setGenre(genre);
                break;
            } catch (Exception e) {
                System.out.printf("erreur : " + e);
            }
        } while (true);
        presenter.update(r);
        ldatas = presenter.getAll();
        affListe(ldatas);

    }

    @Override
    protected void ajouter() {
        Rayon r = null;
        do {
            try {
                System.out.println("code rayon ");
                String code = sc.nextLine();
                System.out.println("genre ");
                String genre = sc.nextLine();
                r = new Rayon(code, genre);
                presenter.add(r);
                ldatas = presenter.getAll();//rafraichissement
                affListe(ldatas);
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : " + e);
            }
        } while (true);

    }

    @Override
    protected void special() {
        int choix = choixElt(ldatas);
        Rayon r = ldatas.get(choix - 1);

        List options = new ArrayList<>(Arrays.asList("lister exemplaires", "fin"));
        do {
            int choix2 = choixListe(options);
            switch (choix2) {
                case 1:
                    exemplaires(r);
                    break;
                case 2:
                    return;
            }
        } while (true);
    }

    @Override
    public void exemplaires(Rayon r) {
        ((SpecialRayonPresenter) presenter).listerExemplaires(r);
    }

    //Trie : ordre alphabétique du genre
    public static Comparator<Rayon> trieRayon() {
        return new Comparator<Rayon>() {
            @Override
            public int compare(Rayon r1, Rayon r2) {
                int resultat = r1.getGenre().compareTo(r2.getGenre());
                return resultat;
            }
        };
    }
}
