package bibliotheque.mvp.model;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.TypeLivre;
import bibliotheque.metier.TypeOuvrage;

import java.util.List;

public interface SpecialAuteur {
    List<Ouvrage> listerOuvrages();
    List<Ouvrage> listerOuvrages(TypeOuvrage to, TypeLivre tl);
    List<Ouvrage> listerOuvrage(String genre);
}
