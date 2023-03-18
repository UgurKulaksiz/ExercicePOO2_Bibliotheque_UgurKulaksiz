package bibliotheque.mvp;

import bibliotheque.mvp.model.AuteurModel;
import bibliotheque.mvp.model.DAOAuteur;
import bibliotheque.mvp.model.DAOLecteur;
import bibliotheque.mvp.model.LecteurModel;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.mvp.presenter.LecteurPresenter;
import bibliotheque.mvp.view.AuteurViewConsole;
import bibliotheque.mvp.view.AuteurViewInterface;
import bibliotheque.mvp.view.LecteurViewConsole;
import bibliotheque.mvp.view.LecteurViewInterface;

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
    }
}
