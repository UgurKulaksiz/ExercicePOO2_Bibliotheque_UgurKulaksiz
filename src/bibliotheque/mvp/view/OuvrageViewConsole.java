package bibliotheque.mvp.view;

import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.TypeOuvrage;
import bibliotheque.mvp.presenter.SpecialOuvragePresenter;
import bibliotheque.utilitaires.*;
import bibliotheque.utilitaires.comparators.OuvrageComparator;

import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.*;

public class OuvrageViewConsole extends AbstractViewConsole<Ouvrage> implements SpecialOuvrageViewConsole {
   /*
    @Override
    public void setListDatas(List<Ouvrage> ldatas) {
        /*Comparator<Ouvrage> cmp = new OuvrageComparator();
        ldatas.sort(cmp);
        super.setListDatas(ldatas);
         */

        //Lambda expressions
        /* Enoncé V7 : Remplacez les instructions de tri utilisant une classe anonyme
        par une lambda expression.

        Comparator<Ouvrage> cmp = (ouv1, ouv2)-> ouv1.getTitre().compareTo(ouv2.getTitre());
        ldatas.sort(cmp);
    }
    */

    @Override
    protected void rechercher() {
        //TODO rechercher ouvrage
    }

    @Override
    protected void modifier() {
        int choix = choixElt(ldatas);
        Ouvrage o = ldatas.get(choix-1);
        do {
            try {
                double ploc =Double.parseDouble(modifyIfNotBlank("prix location",""+o.getPrixLocation()));
                o.setPrixLocation(ploc);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        presenter.update(o);
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);
    }

    @Override
    protected void ajouter() {
        TypeOuvrage[] tto = TypeOuvrage.values();
        List<TypeOuvrage> lto = new ArrayList<>(Arrays.asList(tto));
        int choix = Utilitaire.choixListe(lto);
        Ouvrage o = null;
        List<OuvrageFactory> lof = new ArrayList<>(Arrays.asList(new LivreFactory(),new CDFactory(),new DVDFactory()));
        o = lof.get(choix-1).create();
        presenter.add(o);
        //TODO attribuer auteurs, les auteur sont triés par odre de nom et prénom, empêcher doublons
    }

    @Override
    protected void special() {
        int choix =  choixElt(ldatas);
        Ouvrage o = ldatas.get(choix-1);

        List options = new ArrayList<>(Arrays.asList("lister exemplaires", "lister exemplaires en location", "lister exemplaires libres","fin"));
        do {
            int ch = choixListe(options);

            switch (ch) {

                case 1:
                    exemplaires(o);
                    break;
                case 2:
                    enLocation(o, true);
                    break;
                case 3:
                    enLocation(o, false);
                    break;

                case 4 :return;
            }
        } while (true);

    }

    @Override
    public void enLocation(Ouvrage o, boolean enLocation) {
        ((SpecialOuvragePresenter) presenter).listerExemplaire(o, enLocation);
    }

    @Override
    public void exemplaires(Ouvrage o) {
        ((SpecialOuvragePresenter)presenter).listerExemplaire(o);
    }

    //Trie : ordre alphabétique du titre
    public static Comparator<Ouvrage> trieOuvrage() {
        return new Comparator<Ouvrage>() {
            @Override
            public int compare(Ouvrage ouv1, Ouvrage ouv2) {
                int resultat = ouv1.getTitre().compareTo(ouv2.getTitre());
                return resultat;
            }
        };
    }
}
