package bibliotheque.mvp;

import bibliotheque.mvp.model.*;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.mvp.presenter.LecteurPresenter;
import bibliotheque.mvp.presenter.RayonPresenter;
import bibliotheque.mvp.view.*;

public class GestBiblio {
    public static void main(String[] args) {
        DAOLecteur lectM = new LecteurModel();

        LecteurViewInterface lectV = new LecteurViewConsole();

        LecteurPresenter lectP = new LecteurPresenter(lectM, lectV); //Création et injection de dépendance
        lectP.start();



        /* AUTEUR */
        DAOAuteur lectA = new AuteurModel();

        AuteurViewInterface lectVA = new AuteurViewConsole();

        AuteurPresenter lectPA = new AuteurPresenter(lectA, lectVA);
        lectPA.start();

        /* RAYON */
        DAORayon lectRay = new RayonModel();

        RayonViewInterface lectVRay = new RayonViewConsole();

        RayonPresenter leectPRay = new RayonPresenter(lectRay, lectVRay);
        leectPRay.start();
    }
}
