package bibliotheque.mvp;

import bibliotheque.mvp.model.*;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.mvp.presenter.LecteurPresenter;
import bibliotheque.mvp.presenter.RayonPresenter;
import bibliotheque.mvp.view.*;
import bibliotheque.utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestBiblio {
    /* LECTEUR */
    private DAOLecteur lm;
    private LecteurViewInterface lv;
    private LecteurPresenter lp;

    /* AUTEUR */
    private DAOAuteur mA;
    private AuteurViewInterface vA;
    private AuteurPresenter pA;

    /* RAYONS */
    private DAORayon mR;
    private RayonViewInterface vR;
    private RayonPresenter pR;



    public void gestion(){
        /* LECTEUR */
        lm = new LecteurModel();
        lv = new LecteurViewConsole();
        lp = new LecteurPresenter(lm, lv);//création et injection de dépendance

        List<String> loptions = Arrays.asList("lecteurs","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: lp.start();
                    break;
                case 2 : System.exit(0);
            }
        }while(true);

        /* AUTEUR */
        mA = new AuteurModel();
        vA = new AuteurViewConsole();
        pA = new AuteurPresenter(mA, vA);//création et injection de dépendance

        List<String> loptionsAuteur = Arrays.asList("Auteurs","fin");
        do {
            int ch = Utilitaire.choixListe(loptionsAuteur);
            switch (ch){
                case 1: pA.start();
                    break;
                case 2 : System.exit(0);
            }
        }while(true);

        /* RAYONS */
        mR = new RayonModel();
        vR = new RayonViewConsole();
        pR = new RayonPresenter(mR, vR);//création et injection de dépendance

        List<String> loptionsRayon = Arrays.asList("Rayons","fin");
        do {
            int ch = Utilitaire.choixListe(loptionsRayon);
            switch (ch){
                case 1: pR.start();
                    break;
                case 2 : System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        GestBiblio gb = new GestBiblio();
        gb.gestion();
    }
}
