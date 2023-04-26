package bibliotheque.mvp.model;

import bibliotheque.metier.Auteur;

import java.util.List;

public interface DAOAuteur extends DAO<Auteur> {
    List<Auteur> getAuteurs();
}
