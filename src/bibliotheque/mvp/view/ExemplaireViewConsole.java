package bibliotheque.mvp.view;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.ExemplairePresenter;
import bibliotheque.mvp.presenter.SpecialExemplairePresenter;
import bibliotheque.utilitaires.comparators.ExemplaireComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static bibliotheque.utilitaires.Utilitaire.*;

public class ExemplaireViewConsole extends AbstractViewConsole<Exemplaire> implements SpecialExemplaireViewConsole {
    @Override
    public void setListDatas(List<Exemplaire> ldatas) {
        /*Comparator<Exemplaire> cmp = new ExemplaireComparator();
        ldatas.sort(cmp);
        super.setListDatas(ldatas);
         */

        //Lambda expressions
        /* Enoncé V7 : Remplacez les instructions de tri utilisant une classe anonyme
        par une lambda expression.
         */
        Comparator<Exemplaire> cmp = (a1,a2)-> a1.getRayon().getCodeRayon().compareTo(a2.getRayon().getCodeRayon());
        ldatas.sort(cmp);

        super.setListDatas(ldatas);
    }

    @Override
    protected void rechercher() {
        try{
            System.out.println("matricule : ");
            String mat= sc.next();
            Ouvrage obidon = new Livre("zzz",0,null,1,"","","",1, TypeLivre.ROMAN,"");
            Exemplaire rech = new Exemplaire(mat,"",obidon);
            presenter.search(rech);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
    }

    @Override
    protected void modifier() {
        int choix = choixElt(ldatas);
        Exemplaire ex = ldatas.get(choix-1);
        do {
            try {
                String etat = modifyIfNotBlank("etat", ex.getDescriptionEtat());
                ex.setDescriptionEtat(etat);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        presenter.update(ex);
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);
    }

    @Override
    protected void ajouter() {
        System.out.println("matricule ");
        String mat=sc.next();
        System.out.println("etat  ");
        String etat=sc.next();
        System.out.println("ouvrage ");
        Ouvrage o = ((ExemplairePresenter)presenter).choixOuvrage();
        Exemplaire ex = null;
        try {
            ex = new Exemplaire(mat,etat,o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        presenter.add(ex);
        Rayon r = ((ExemplairePresenter)presenter).choixRayon();
        ex.setRayon(r);
    }

    @Override
    protected void special() {
        int choix =  choixElt(ldatas);
        Exemplaire ex = ldatas.get(choix-1);

        List options = new ArrayList<>(Arrays.asList(
                "changer état",
                "lecteur actuel",
                "tous les lecteurs",
                "envoi mail lecteur actuel",
                "envoi mail tous les lecteurs",
                "en retard ?",
                "jours de retard",
                "en location ?",
                "fin"));
        do {
            int ch = choixListe(options);

            switch (ch) {
                case 1:
                    changeEtat(ex);
                    break;

                case 2 :

                    lecteurActuel(ex);
                    break;

                case 3 :
                    lecteurs(ex);
                    break;

                case 4 :
                    mailLecteurActuel(ex);
                    break;

                case 5:
                    mailLecteurs(ex);
                    break;

                case 6 :
                    enRetard(ex);
                    break;

                case 7 :
                    joursRetard(ex);
                    break;

                case 8 :
                    enLocation(ex);
                    break;

                case 9 : return;
            }
        } while (true);

    }

    @Override
    public void enLocation(Exemplaire ex) {
        ((SpecialExemplairePresenter)presenter).enLocation(ex);
    }

    @Override
    public void joursRetard(Exemplaire ex) {
        ((SpecialExemplairePresenter)presenter).joursRetard(ex);
    }

    @Override
    public void enRetard(Exemplaire ex) {
        ((SpecialExemplairePresenter)presenter).enRetard(ex);
    }

    @Override
    public void mailLecteurs(Exemplaire ex) {
        ((SpecialExemplairePresenter)presenter).envoiMailLecteurs(ex);
    }

    @Override
    public void mailLecteurActuel(Exemplaire ex) {
        ((SpecialExemplairePresenter)presenter).envoiMailLecteurActuel(ex);
    }

    @Override
    public void lecteurs(Exemplaire ex) {
        ((SpecialExemplairePresenter)presenter).lecteurs(ex);
    }

    @Override
    public void lecteurActuel(Exemplaire ex) {
        ((SpecialExemplairePresenter)presenter).LecteurActuel(ex);
    }

    @Override
    public void changeEtat(Exemplaire ex) {
        System.out.println("nouvel état :");
        String etat = sc.nextLine();
        ((ExemplairePresenter)presenter).modifierEtat(ex,etat) ;
    }

    //Trie : ordre alphanumérique du code
    public static Comparator<Exemplaire> trieExemplaire() {
        return new Comparator<Exemplaire>() {
            @Override
            public int compare(Exemplaire ex1, Exemplaire ex2) {
                int resultat = ex1.getRayon().getCodeRayon().compareTo(ex2.getRayon().getCodeRayon());
                return resultat;
            }
        };
    }
}
