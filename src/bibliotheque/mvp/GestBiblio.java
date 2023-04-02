package bibliotheque.mvp;

import bibliotheque.mvp.model.*;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.mvp.presenter.LecteurPresenter;
import bibliotheque.mvp.presenter.OuvragePresenter;
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
    private AuteurModel mA;
    private AuteurViewInterface vA;
    private AuteurPresenter pA;

    /* RAYONS */
    private DAORayon mR;
    private RayonViewInterface vR;
    private RayonPresenter pR;

    /* OUVRAGES */
    private DAOOuvrage mO;
    private OuvrageViewInterface vO;
    private OuvragePresenter pO;


    public void gestion() throws Exception {
        /* LECTEUR */
        lm = new LecteurModel();
        lv = new LecteurViewConsole();
        lp = new LecteurPresenter(lm, lv);//création et injection de dépendance

        /* AUTEUR */
        mA = new AuteurModel();
        vA = new AuteurViewConsole();
        pA = new AuteurPresenter(mA, vA);//création et injection de dépendance

        /* RAYONS */
        mR = new RayonModel();
        vR = new RayonViewConsole();
        pR = new RayonPresenter(mR, vR);//création et injection de dépendance

        /* OUVRAGES */
        mO = new OuvrageModel();
        vO = new OuvrageViewConsole();
        pO = new OuvragePresenter(mO, vO);//création et injection de dépendance

        List<String> loptions = Arrays.asList("Lecteurs", "Auteurs", "Rayons", "Ouvrages", "Fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1:
                    lp.start();
                    break;
                case 2:
                    pA.start();
                    break;
                case 3:
                    pR.start();
                    break;
                case 4:
                    pO.start();
                    break;
                case 5:
                    System.exit(0);
            }

        } while (true);

    }

    public static void main(String[] args) throws Exception {
        GestBiblio gb = new GestBiblio();
        gb.gestion();
    }
}
