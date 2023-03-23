package bibliotheque.mvp.model;

import bibliotheque.metier.Auteur;

import java.util.List;

public interface DAOAuteur {

    Auteur addAuteur(Auteur aut);

    boolean removeAuteur(Auteur aut);

    Auteur updateAuteur(Auteur aut);

    Auteur readAuteur(String nomAuteur);

    List<Auteur> getAuteurs();
}
