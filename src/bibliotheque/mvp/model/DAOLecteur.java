package bibliotheque.mvp.model;

import bibliotheque.metier.Lecteur;

import java.util.List;

public interface DAOLecteur extends DAO<Lecteur> {
    List<Lecteur> getLecteurs();
}
