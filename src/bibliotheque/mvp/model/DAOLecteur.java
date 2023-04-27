package bibliotheque.mvp.model;

import bibliotheque.metier.Lecteur;

import java.util.List;

public interface DAOLecteur {
    Lecteur addLecteur(Lecteur lec);

    Lecteur readLecteur(int idLecteur);

    Lecteur updateLecteur(Lecteur lec);

    boolean removeLecteur(Lecteur lec);

    List<Lecteur> getLecteurs();
}
