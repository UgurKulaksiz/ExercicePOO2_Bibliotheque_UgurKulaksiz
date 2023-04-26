package bibliotheque.mvp.model;

import bibliotheque.metier.Ouvrage;

import java.util.List;

public interface DAOOuvrage extends DAO<Ouvrage>{
    List<Ouvrage> getOuvrages();
}
